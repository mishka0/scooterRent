package com.senla.rent.service;

import com.senla.rent.api.dao.SubscriptionInfoRepository;
import com.senla.rent.api.dto.subscription.SubscriptionInfoDTO;
import com.senla.rent.api.service.SubscriptionInfoService;
import com.senla.rent.entity.SubscriptionInfo;
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
public class SubscriptionInfoServiceImpl implements SubscriptionInfoService {

    private final SubscriptionInfoRepository subscriptionInfoRepository;
    private final ModelMapper modelMapper;

    public SubscriptionInfoServiceImpl(SubscriptionInfoRepository subscriptionInfoRepository, ModelMapper modelMapper) {
        this.subscriptionInfoRepository = subscriptionInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SubscriptionInfo getSubsInfo(Integer id) {
        try {
            return subscriptionInfoRepository.findById(id);
        } catch (RuntimeException exception) {
            log.error("Can't get subscription info! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get subscription info!");
        }
    }

    @Override
    public List<SubscriptionInfoDTO> getAllSUbsInfo(Integer page, Integer limit) {
        try {
            return subscriptionInfoRepository.findAll(page, limit)
                     .stream()
                     .map(subscriptionInfo -> modelMapper.map(subscriptionInfo, SubscriptionInfoDTO.class))
                     .collect(Collectors.toList());
        } catch (RuntimeException exception) {
            log.error("Can't get list of subscription info! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get list of subscription info!");
        }
    }
}
