package com.senla.rent.dao;


import com.senla.rent.api.dao.SubscriptionRepository;
import com.senla.rent.dao.configs.HibernateTestConfig;
import com.senla.rent.dao.configs.LaunchTestConfiguration;
import com.senla.rent.entity.Subscription;
import com.senla.rent.entity.SubscriptionInfo;
import com.senla.rent.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.time.Duration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LaunchTestConfiguration.class, HibernateTestConfig.class})
@Transactional
public class SubscriptionRepositoryImplTest {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    public void insert() {
        subscriptionRepository.insert(new Subscription(Duration.parse("PT1H"), new SubscriptionInfo(), new User()));
    }

    @Test
    public void findById() {
        Subscription subscription = subscriptionRepository.findById(1);
        Assert.assertEquals(Duration.parse("PT8M20S"), subscription.getTimeLeft());
    }

    @Test
    public void delete() {

    }

    @Test
    public void existById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getUsersSubs() {
    }

    @Test
    public void findAll() {
    }
}