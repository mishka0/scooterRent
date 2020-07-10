package com.senla.rent.api.service;

import com.senla.rent.api.dto.history.HistoryRentClosedDTO;
import com.senla.rent.api.dto.user.UserJWT;

public interface RideService {

    void goRideWithSubs(UserJWT user, Integer scooterId, Integer subscriptionId);

    void goRide(UserJWT user, Integer scooterId, Integer tariffId);

    HistoryRentClosedDTO endRide(UserJWT userJWT, Integer scooterDTO);
}
