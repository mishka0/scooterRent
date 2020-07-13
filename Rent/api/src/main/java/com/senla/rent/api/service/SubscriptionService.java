package com.senla.rent.api.service;

import com.senla.rent.api.dto.subscription.SubscriptionDTO;
import com.senla.rent.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<SubscriptionDTO> getUserSubs(Integer id);

    Subscription getSubscription(Integer id);
}
