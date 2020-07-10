package com.senla.rent.api.service;

import com.senla.rent.api.dto.subscription.SubscriptionInfoDTO;
import com.senla.rent.entity.SubscriptionInfo;

import java.util.List;

public interface SubscriptionInfoService {
    SubscriptionInfo getSubsInfo(Integer id);

    List<SubscriptionInfoDTO> getAllSUbsInfo(Integer page, Integer limit);
}
