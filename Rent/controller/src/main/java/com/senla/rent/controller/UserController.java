package com.senla.rent.controller;

import com.senla.rent.api.dto.user.UserInfoDTO;
import com.senla.rent.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserInfoDTO getFullUserInfo(@RequestHeader(value = "Authorization") String token){
        return userService.getUserInfo(token);
    }

}
