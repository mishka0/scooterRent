package com.senla.rent.api.service;

import com.senla.rent.api.dto.security.RegistrationRequestDTO;
import com.senla.rent.api.dto.subscription.SubscriptionInfoDTO;
import com.senla.rent.api.dto.user.UserFullInfoDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.entity.User;

public interface UserService {

    void registerUser(RegistrationRequestDTO userReg, boolean isModer);

    boolean existUser(String login);

    UserFullInfoDTO getUserInfo(Integer id);

    void buySubscription(Integer id, SubscriptionInfoDTO subscriptionInfoDTO);

    User getUser(Integer id);
}
