package com.senla.rent.dao;

import com.senla.rent.api.dao.AdditionRepository;
import com.senla.rent.api.dao.SubscriptionInfoRepository;
import com.senla.rent.api.dto.addition.AdditionDTO;
import com.senla.rent.configs.hibernate.HibernateConfig;
import com.senla.rent.dao.configs.HibernateTestConfig;
import com.senla.rent.dao.configs.LaunchTestConfiguration;
import com.senla.rent.entity.Addition;
import com.senla.rent.entity.SubscriptionInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { LaunchTestConfiguration.class, HibernateTestConfig.class})
public class AdditionRepositoryImplTest {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AdditionRepository additionRepository;

    @Autowired
    private SubscriptionInfoRepository subscriptionInfoRepository;


    @Test
    @Ignore
    @Transactional
    public void setSubsInfoTime(){
        List<SubscriptionInfo> subscriptionInfos = subscriptionInfoRepository.findAll(0,100);
        for (SubscriptionInfo subscriptionInfo : subscriptionInfos) {
            subscriptionInfo.setTime(Duration.ZERO.plusMinutes(60));
            subscriptionInfoRepository.update(subscriptionInfo);
        }

    }

    @Test
    @Ignore
    public void getUserAddition() {
        Addition addition = additionRepository.getUserAddition(5);
        AdditionDTO additionDTO = mapper.map(addition, AdditionDTO.class);
        System.out.println("Good" + addition);
        Assert.notNull(addition, "Test Ok!");
    }
}