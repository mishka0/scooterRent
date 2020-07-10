package com.senla.rent.controller;


import com.senla.rent.api.dto.history.HistoryRentClosedDTO;
import com.senla.rent.api.dto.scooter.ScooterDTO;
import com.senla.rent.api.dto.subscription.SubscriptionDTO;
import com.senla.rent.api.dto.tariff.TariffDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.service.RideService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/rent")
public class RideController {

    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @PostMapping("/start")
    public void startRide(
            @AuthenticationPrincipal UserJWT userJWT,
            @RequestParam(value = "idSubscription", required = false) Integer subscriptionId,
            @RequestParam(value= "idTariff" ,required = false) Integer tariffId,
            @RequestParam(value = "idScooter") Integer scooterId
    ) {
        if (subscriptionId != null) rideService.goRideWithSubs(userJWT, scooterId, subscriptionId);
        else rideService.goRide(userJWT, scooterId, tariffId);
    }

    @PostMapping("/end")
    public HistoryRentClosedDTO endRide(
            @AuthenticationPrincipal UserJWT userJWT,
            @RequestParam(value = "idScooter") Integer scooterId
    ) {
       return rideService.endRide(userJWT, scooterId);
    }

}
