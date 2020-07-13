package com.senla.rent;

import com.senla.rent.api.dao.AdditionRepository;
import com.senla.rent.configs.HibernateTestConfig;
import com.senla.rent.configs.LaunchTestConfiguration;
import com.senla.rent.entity.Addition;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LaunchTestConfiguration.class, HibernateTestConfig.class})
@Transactional
public class AdditionRepositoryImplTest {

    @Autowired
    private AdditionRepository additionRepository;

    @Test
    public void findById() {
        Addition addition = additionRepository.findById(1);
        Assert.assertEquals("Admin", addition.getFirstName());
        Assert.assertEquals("Adminovich", addition.getLastName());
        Assert.assertEquals((Integer) 100, addition.getDiscount());
    }

    @Test
    public void existById() {
        Assert.assertTrue(additionRepository.existById(1));
    }

}