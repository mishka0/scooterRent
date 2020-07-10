package com.senla.rent.service;

import com.senla.rent.api.dao.SubscriptionRepository;
import com.senla.rent.api.dto.subscription.SubscriptionDTO;
import com.senla.rent.api.dto.subscription.SubscriptionInfoDTO;
import com.senla.rent.api.dto.user.UserDTO;
import com.senla.rent.api.dto.user.UserFullInfoDTO;
import com.senla.rent.api.security.JwtTokenProvider;
import com.senla.rent.api.service.SubscriptionService;
import com.senla.rent.api.service.UserService;
import com.senla.rent.entity.Subscription;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, ModelMapper modelMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SubscriptionDTO> getUserSubs(Integer id) {
        return subscriptionRepository
                .getUsersSubs(id)
                .stream()
                .map(subscription -> modelMapper.map(subscription, SubscriptionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Subscription getSubscription(Integer id) {
        return subscriptionRepository.findById(id);
    }

}
