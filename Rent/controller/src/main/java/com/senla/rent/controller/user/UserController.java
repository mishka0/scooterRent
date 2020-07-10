package com.senla.rent.controller.user;

import com.senla.rent.api.dto.user.UserFullInfoDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserFullInfoDTO getFullUserInfo(@RequestHeader(value = "Authorization") String token, @AuthenticationPrincipal UserJWT userJWT) {
        return userService.getUserInfo(userJWT.getUser().getId());
    }
}
