package com.senla.rent.controller;

import com.senla.rent.api.dto.subscription.SubscriptionDTO;
import com.senla.rent.api.service.SubscriptionService;
import com.senla.rent.security.jwt.JwtTokenProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionInfoController {



}
