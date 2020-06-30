package com.senla.rent.api.service;

import com.senla.rent.api.dto.subscription.SubscriptionDTO;
import com.senla.rent.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<SubscriptionDTO> getUserSubs(String username);
}
