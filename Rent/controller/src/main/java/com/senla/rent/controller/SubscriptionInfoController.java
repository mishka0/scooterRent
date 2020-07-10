package com.senla.rent.controller;

import com.senla.rent.api.dto.subscription.SubscriptionInfoDTO;
import com.senla.rent.api.service.SubscriptionInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscription/info")
public class SubscriptionInfoController {

    private final SubscriptionInfoService subscriptionInfoService;

    public SubscriptionInfoController(SubscriptionInfoService subscriptionInfoService) {
        this.subscriptionInfoService = subscriptionInfoService;
    }

    @GetMapping
    public List<SubscriptionInfoDTO>  getAllSubsInfo(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "30") int limit){
        return subscriptionInfoService.getAllSUbsInfo(page, limit);
    }
}
