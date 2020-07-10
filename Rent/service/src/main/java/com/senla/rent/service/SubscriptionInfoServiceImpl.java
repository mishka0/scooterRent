package com.senla.rent.service;

import com.senla.rent.api.dao.SubscriptionInfoRepository;
import com.senla.rent.api.dto.subscription.SubscriptionInfoDTO;
import com.senla.rent.api.service.SubscriptionInfoService;
import com.senla.rent.entity.SubscriptionInfo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionInfoServiceImpl implements SubscriptionInfoService {

    private final SubscriptionInfoRepository subscriptionInfoRepository;
    private final ModelMapper modelMapper;

    public SubscriptionInfoServiceImpl(SubscriptionInfoRepository subscriptionInfoRepository, ModelMapper modelMapper) {
        this.subscriptionInfoRepository = subscriptionInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SubscriptionInfo getSubsInfo(Integer id) {
        return subscriptionInfoRepository.findById(id);
    }

    @Override
    public List<SubscriptionInfoDTO> getAllSUbsInfo(Integer page, Integer limit) {
       return subscriptionInfoRepository.findAll(page, limit)
                .stream()
                .map(subscriptionInfo -> modelMapper.map(subscriptionInfo, SubscriptionInfoDTO.class))
                .collect(Collectors.toList());
    }
}
