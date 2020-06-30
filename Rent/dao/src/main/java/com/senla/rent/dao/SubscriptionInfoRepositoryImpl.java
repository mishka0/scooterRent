package com.senla.rent.dao;

import com.senla.rent.api.dao.SubscriptionInfoRepository;
import com.senla.rent.entity.Subscription;
import com.senla.rent.entity.SubscriptionInfo;
import org.springframework.stereotype.Repository;

@Repository
public class SubscriptionInfoRepositoryImpl extends AbstractRepository<SubscriptionInfo, Integer> implements SubscriptionInfoRepository {
    public SubscriptionInfoRepositoryImpl() {
        super(SubscriptionInfo.class);
    }

}
