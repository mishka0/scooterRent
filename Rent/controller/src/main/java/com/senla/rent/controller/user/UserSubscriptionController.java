package com.senla.rent.controller.user;

import com.senla.rent.api.dto.subscription.SubscriptionDTO;
import com.senla.rent.api.dto.subscription.SubscriptionInfoDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.service.SubscriptionService;
import com.senla.rent.api.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/profile/subscription")
public class UserSubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserService userService;

    public UserSubscriptionController(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @GetMapping
    public List<SubscriptionDTO> getUserSubs(@AuthenticationPrincipal UserJWT userJWT) {
        return subscriptionService.getUserSubs(userJWT.getUser().getId());
    }

    @PostMapping("/add")
    public void buySubscription(@AuthenticationPrincipal UserJWT userJWT, @RequestBody SubscriptionInfoDTO subscriptionInfoDTO) {
        userService.buySubscription(userJWT.getUser().getId(), subscriptionInfoDTO);
    }
}
