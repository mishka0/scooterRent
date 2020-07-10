package com.senla.rent.service;

import com.senla.rent.api.dto.history.HistoryRentClosedDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.service.*;
import com.senla.rent.entity.History;
import com.senla.rent.entity.Subscription;
import com.senla.rent.entity.Tariff;
import com.senla.rent.entity.User;
import com.senla.rent.service.exceptions.NotEnoughMoney;
import com.senla.rent.service.exceptions.NotUserSubscriptionException;
import com.senla.rent.service.exceptions.RideException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;


@Service
@Transactional
public class RideServiceImpl implements RideService {

    private final UserService userService;
    private final SubscriptionService subscriptionService;
    private final ScooterService scooterService;
    private final HistoryService historyService;
    private final TariffService tariffService;
    private final ModelMapper modelMapper;

    public RideServiceImpl(UserService userService, SubscriptionService subscriptionService, ScooterService scooterService, HistoryService historyService, TariffService tariffService, ModelMapper modelMapper) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
        this.scooterService = scooterService;
        this.historyService = historyService;
        this.tariffService = tariffService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void goRideWithSubs(UserJWT userJWT, Integer scooterId, Integer subscriptionId) {
        User currentUser = userService.getUser(userJWT.getUser().getId());
        Subscription subscription = subscriptionService.getSubscription(subscriptionId);

        if (!subscription.getUser().getId().equals(currentUser.getId())) {
            throw new NotUserSubscriptionException("Not user subscription!");
        }

        if (currentUser.getAddition().getBalance() < -1.00) {
            throw new NotEnoughMoney("User balance is: " + currentUser.getAddition().getBalance() + " Minimum need balance is -1.00 ");
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
        historyService.addHistory(history);
    }

    @Override
    public void goRide(UserJWT userJWT, Integer scooterId, Integer tariffId) {
        User currentUser = userService.getUser(userJWT.getUser().getId());

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
        historyService.addHistory(history);
    }

    @Override
    public HistoryRentClosedDTO endRide(UserJWT userJWT, Integer scooterDTO) {
        User currentUser = userService.getUser(userJWT.getUser().getId());
        History history = historyService.getNonClosedRent(currentUser.getId());
        history.setTimeEnd(LocalTime.now());
        Duration rideTime = Duration.between(history.getTimeStart(), history.getTimeEnd());
        Subscription subscription = history.getSubscription();

        if (subscription != null) {
            if (!subscription.getUser().getId().equals(currentUser.getId())) {
                throw new NotUserSubscriptionException("Not user subscription!");
            }
            history.setCost(
                    calculatePayWithSubscription(rideTime, subscription));
        } else {
            history.setCost(
                    calculatePay(rideTime, history.getTariff().getDurationTariff(), history.getTariff().getCost()));
        }
        currentUser.getAddition().setBalance(
                currentUser.getAddition().getBalance() - history.getCost());
        history.setClosed(true);
        return modelMapper.map(history, HistoryRentClosedDTO.class);
    }

    private Double calculatePayWithSubscription(Duration rideTime , Subscription subscription) {
        Duration timeLeft = subscription.getTimeLeft();
        if (timeLeft.getSeconds() > rideTime.getSeconds()) {
            subscription.setTimeLeft(timeLeft.minus(rideTime));
            return 0.0;
        } else {
            Duration leftTimeToPay = rideTime.minus(timeLeft);
            subscription.setTimeLeft(Duration.ZERO);
            Tariff tariff = tariffService.getTariffByName("Minutes");
            return calculatePay(leftTimeToPay, tariff.getDurationTariff(), tariff.getCost() );
        }
    }

    private Double calculatePay(Duration rideTime, Duration tariffDuration, Double cost) {
        if (tariffDuration.getSeconds() > rideTime.getSeconds()) {
            return cost;
        } else {
            Long countOfTimeRide = (rideTime.getSeconds() / tariffDuration.getSeconds() + 1);
            return countOfTimeRide * cost;
        }
    }
}
