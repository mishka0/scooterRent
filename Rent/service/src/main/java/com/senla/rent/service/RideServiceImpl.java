package com.senla.rent.service;

import com.senla.rent.api.dto.history.HistoryRentClosedDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.service.*;
import com.senla.rent.entity.*;
import com.senla.rent.service.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;


@Service
@Transactional
@Slf4j
public class RideServiceImpl implements RideService {

    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final ScooterService scooterService;
    private final StatusScooterService statusScooterService;
    private final HistoryService historyService;
    private final TariffService tariffService;
    private final ModelMapper modelMapper;

    public RideServiceImpl(UserService userService, SubscriptionService subscriptionService, ScooterService scooterService, StatusScooterService statusScooterService, HistoryService historyService, TariffService tariffService, ModelMapper modelMapper) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
        this.scooterService = scooterService;
        this.statusScooterService = statusScooterService;
        this.historyService = historyService;
        this.tariffService = tariffService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void goRideWithSubs(UserJWT userJWT, Integer scooterId, Integer subscriptionId) {
        try {
            User currentUser = userService.getUser(userJWT.getUser().getId());
            Subscription subscription = subscriptionService.getSubscription(subscriptionId);
            Scooter scooter = scooterService.getScooter(scooterId);
            if (!(scooter.getStatusScooter().getStatus().equals("Stay"))) {
                throw new ScooterBusy("Scooter status not stay!");
            }
            if (!subscription.getUser().getId().equals(currentUser.getId())) {
                throw new NotUserSubscriptionException("Not user subscription!");
            }

            if (currentUser.getAddition().getBalance() < -1.00) {
                throw new NotEnoughMoney("User balance is: " + currentUser.getAddition().getBalance() + " Minimum need balance is -1.00 ");
            }

            if (subscription.getTimeLeft().getSeconds() == 0) {
                throw new SubscriptionTimedOut("Time subscription is out");
            }
            if (historyService.existNonClosed(currentUser.getId())) {
                throw new RideException("You're already rent scooter!");
            }
            History history = new History(
                    scooterService.getScooterWithHistory(scooterId),
                    null,
                    LocalDate.now(),
                    LocalTime.now(),
                    currentUser,
                    subscription,
                    true,
                    false
            );
            scooter.setStatusScooter(statusScooterService.getByName("Ride"));
            historyService.addHistory(history);
        } catch (ScooterBusy scooterBusy) {
            log.error("Scooter exception! Message exception: " + scooterBusy.getMessage());
            throw scooterBusy;
        } catch (NotUserSubscriptionException notUserSubscriptionException) {
            log.error("Not user subscription exception! Message exception: " + notUserSubscriptionException.getMessage());
            throw notUserSubscriptionException;
        } catch (NotEnoughMoney notEnoughMoney) {
            log.error("Not enough money! Message exception: " + notEnoughMoney.getMessage());
            throw notEnoughMoney;
        } catch (SubscriptionTimedOut subscriptionTimedOut) {
            log.error("Subscription timed out exception! Message exception: " + subscriptionTimedOut.getMessage());
            throw subscriptionTimedOut;
        } catch (RideException rideException) {
            log.error("User already rent scooter! Message exception: " + rideException.getMessage());
            throw rideException;
        } catch (RuntimeException exception) {
            log.error("Can't go ride with subscription! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't go ride with subscription!");
        }
    }

    @Override
    public void goRide(UserJWT userJWT, Integer scooterId, Integer tariffId) {
        try {
            User currentUser = userService.getUser(userJWT.getUser().getId());
            Scooter scooter = scooterService.getScooter(scooterId);
            if (!(scooter.getStatusScooter().getStatus().equals("Stay"))) {
                throw new ScooterBusy("Scooter status not stay!");
            }
            if (currentUser.getAddition().getBalance() < 3.00) {
                throw new NotEnoughMoney("User balance is: " + currentUser.getAddition().getBalance() + " Minimum need balance is 3.00 ");
            }
            if (historyService.existNonClosed(currentUser.getId())) {
                throw new RideException("You're already rent scooter!");
            }
            History history = new History(
                    scooterService.getScooterWithHistory(scooterId),
                    tariffService.getTariffById(tariffId),
                    LocalDate.now(),
                    LocalTime.now(),
                    currentUser,
                    null,
                    false,
                    false
            );
            scooter.setStatusScooter(statusScooterService.getByName("Ride"));
            historyService.addHistory(history);
        } catch (ScooterBusy scooterBusy) {
            log.error("Scooter Exception! Message exception: " + scooterBusy.getMessage());
            throw scooterBusy;
        } catch (NotEnoughMoney notEnoughMoney) {
            log.error("Not enough money exception! Message exception: " + notEnoughMoney.getMessage());
            throw notEnoughMoney;
        } catch (RuntimeException exception) {
            log.error("Can't go ride! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't go ride!");
        }
    }

    @Override
    public HistoryRentClosedDTO endRide(UserJWT userJWT, Integer scooterId) {
        try {
            User currentUser = userService.getUser(userJWT.getUser().getId());
            History history = historyService.getNonClosedRent(currentUser.getId());
            Subscription subscription = history.getSubscription();
            Scooter scooter = scooterService.getScooter(scooterId);
            history.setTimeEnd(LocalTime.now());
            Duration rideTime = Duration.between(history.getTimeStart(), history.getTimeEnd());
            if (history.getSubscription() != null) {
                if (!(subscription.getUser().getId().equals(currentUser.getId()))) {
                    log.error("Not user subscription!");
                    throw new NotUserSubscriptionException("Not user subscription!");
                }
                history.setCost(
                        calculatePayWithSubscription(rideTime, subscription, currentUser.getAddition().getDiscount()));
            } else {
                history.setCost(
                        calculatePay(rideTime, history.getTariff().getDurationTariff(), history.getTariff().getCost(), currentUser.getAddition().getDiscount()));
            }
            currentUser.getAddition().setBalance(currentUser.getAddition().getBalance() - history.getCost());
            history.setClosed(true);
            scooter.setStatusScooter(statusScooterService.getByName("Stay"));
            return modelMapper.map(history, HistoryRentClosedDTO.class);
        } catch (RuntimeException exception) {
            log.error("Can't end ride! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't end ride!");
        }
    }

    private Double calculatePayWithSubscription(Duration rideTime, Subscription subscription, Integer discount) {
        try {
            Duration timeLeft = subscription.getTimeLeft();
            if (timeLeft.getSeconds() > rideTime.getSeconds()) {
                subscription.setTimeLeft(timeLeft.minus(rideTime));
                return 0.0;
            } else {
                Duration leftTimeToPay = rideTime.minus(timeLeft);
                subscription.setTimeLeft(Duration.ZERO);
                Tariff tariff = tariffService.getTariffByName("Minutes");
                return calculatePay(leftTimeToPay, tariff.getDurationTariff(), tariff.getCost(), discount);
            }
        } catch (RuntimeException exception) {
            log.error("Can't calculate pay for user with subscription! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't calculate pay for user with subscription");
        }
    }

    private Double calculatePay(Duration rideTime, Duration tariffDuration, Double cost, Integer discount) {
        try {
            if (tariffDuration.getSeconds() > rideTime.getSeconds()) {
                double toDiscount = cost * discount / 100;
                return cost - toDiscount;
            } else {
                Long countOfTimeRide = (rideTime.getSeconds() / tariffDuration.getSeconds() + 1);
                double toDiscount = (countOfTimeRide * cost) * discount / 100;
                return (countOfTimeRide * cost) - toDiscount;
            }
        } catch (RuntimeException exception) {
            log.error("Can't calculate pay for user! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't calculate pay for user!");
        }
    }
}
