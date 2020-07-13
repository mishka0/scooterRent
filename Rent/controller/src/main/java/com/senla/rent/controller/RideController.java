package com.senla.rent.controller;

import com.senla.rent.api.dto.history.HistoryRentClosedDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.service.RideService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(value = "idTariff", required = false) Integer tariffId,
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
