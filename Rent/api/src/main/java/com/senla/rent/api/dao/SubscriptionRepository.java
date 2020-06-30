package com.senla.rent.api.dao;


import com.senla.rent.api.dao.generic.GenericRepository;
import com.senla.rent.entity.Subscription;

import java.util.List;

public interface SubscriptionRepository extends GenericRepository<Subscription, Integer> {
    List<Subscription> getUsersSubs(Integer id);
}
