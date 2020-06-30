package com.senla.rent.api.service;

import com.senla.rent.api.dto.security.RegistrationRequestDTO;
import com.senla.rent.api.dto.user.UserInfoDTO;
import com.senla.rent.api.dto.user.UserJWT;

public interface UserService {

    UserJWT findByLogin(String login);

    UserJWT register(RegistrationRequestDTO userReg, boolean isModer);

    boolean existUser(String login);

    Integer getUserId(String username);

    void updateUser(String token, UserInfoDTO userInfoDTO);

    UserInfoDTO getUserInfo(String token);
}
