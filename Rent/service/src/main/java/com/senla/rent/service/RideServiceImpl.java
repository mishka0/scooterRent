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

        if (!subscription.getUser().getId().equals(currentUser.getId()))
        {
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
                tariffService.getTariffById(null),
                LocalDate.now(),
                LocalTime.now(),
                currentUser,
                false,
                false
        );
        historyService.addHistory(history);
    }

    @Override
    public void goRide(UserJWT userJWT, Integer scooterId, Integer tariffId) {
        User currentUser = userService.getUser(userJWT.getUser().getId());

        if (currentUser.getAddition().getBalance() < 3.00) {
            throw new NotEnoughMoney("User balance is: " + currentUser.getAddition().getBalance() + " Minimum need balance is -1.00 ");
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
                false,
                false
        );
        historyService.addHistory(history);
    }

    @Override
    public HistoryRentClosedDTO endRide(UserJWT userJWT, Integer scooterDTO, Integer subscriptionId) {
        User currentUser = userService.getUser(userJWT.getUser().getId());
        History history = historyService.getNonClosedRent(currentUser.getId());
        history.setTimeEnd(LocalTime.now());
        Duration rideTimeInSeconds = Duration.between(history.getTimeStart(), history.getTimeEnd());
        Duration tariffDurationInSeconds = history.getTariff().getDurationTariff();
        Subscription subscription = subscriptionService.getSubscription(subscriptionId);

        if (history.isSubscription()) {
            if (!subscription.getUser().getId().equals(currentUser.getId()))
            {
                throw new NotUserSubscriptionException("Not user subscription!");
            }
            history.setCost(
                    calculatePayWithSubscription(rideTimeInSeconds, tariffDurationInSeconds, subscription));
        } else {
            history.setCost(
                    calculatePay(rideTimeInSeconds, tariffDurationInSeconds, history.getTariff()));
        }
        currentUser.getAddition().setBalance(
                currentUser.getAddition().getBalance() - history.getCost());
        history.setClosed(true);
        return modelMapper.map(history, HistoryRentClosedDTO.class);
    }

    private Double calculatePayWithSubscription(Duration rideTimeInSeconds, Duration tariffDurationInSeconds, Subscription subscription){
        Duration timeLeft = subscription.getTimeLeft();
        if (timeLeft.getSeconds() > rideTimeInSeconds.getSeconds())
        {
            subscription.setTimeLeft(timeLeft.minus(rideTimeInSeconds));
            return 0.0;
        }
        else
        {
            Duration leftTimeToPay = rideTimeInSeconds.minus(timeLeft);
            subscription.setTimeLeft(Duration.ZERO);
            return calculatePay(leftTimeToPay, tariffDurationInSeconds,tariffService.getTariffByName("Minutes"));
        }
    }

    private Double calculatePay(Duration rideTimeInSeconds, Duration tariffDurationInSeconds, Tariff tariff){
        if (tariffDurationInSeconds.getSeconds() > rideTimeInSeconds.getSeconds()) {
            return tariff.getCost();
        } else {
            Long countOfTimeRide = (rideTimeInSeconds.getSeconds() / tariffDurationInSeconds.getSeconds() + 1);
            return countOfTimeRide * tariff.getCost();
        }
    }
}
