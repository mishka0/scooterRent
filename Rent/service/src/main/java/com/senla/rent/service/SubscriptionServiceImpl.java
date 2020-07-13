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
import com.senla.rent.service.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final ModelMapper modelMapper;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, ModelMapper modelMapper) {
        this.subscriptionRepository = subscriptionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SubscriptionDTO> getUserSubs(Integer id) {
        try {
            return subscriptionRepository
                    .getUsersSubs(id)
                    .stream()
                    .map(subscription -> modelMapper.map(subscription, SubscriptionDTO.class))
                    .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get user subscriptions! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get user subscriptions!");
        }
    }

    @Override
    public Subscription getSubscription(Integer id) {
        try {
            return subscriptionRepository.findById(id);
        } catch (RuntimeException exception) {
            log.error("Can't get subscription! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get subscription!");
        }
    }

}
