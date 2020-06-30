package com.senla.rent.service;

import com.senla.rent.api.dao.SubscriptionRepository;
import com.senla.rent.api.dto.subscription.SubscriptionDTO;
import com.senla.rent.api.service.SubscriptionService;
import com.senla.rent.api.service.UserService;
import com.senla.rent.entity.Subscription;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SubscriptionDTO> getUserSubs(String username) {
        return subscriptionRepository
                .getUsersSubs(userService.getUserId(username))
                .stream()
                .map(subscription -> modelMapper.map(subscription, SubscriptionDTO.class))
                .collect(Collectors.toList());
    }
}
