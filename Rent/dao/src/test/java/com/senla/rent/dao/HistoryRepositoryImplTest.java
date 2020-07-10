package com.senla.rent.dao;

import com.senla.rent.api.dao.HistoryRepository;
import com.senla.rent.dao.configs.HibernateTestConfig;
import com.senla.rent.dao.configs.LaunchTestConfiguration;
import com.senla.rent.entity.History;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LaunchTestConfiguration.class, HibernateTestConfig.class})
public class HistoryRepositoryImplTest {

    @Autowired
    private HistoryRepository historyRepository;

    @Test
    public void insert() {
    }

    @Test
    public void findById() {
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
    public void getUserHistory() {
    }

    @Test
    public void getScooterHistory() {
    }

    @Test
    public void existNonClosed() {
      boolean t =  historyRepository.existNonClosed(1);
        System.out.println(t);
    }

    @Test
    public void findAll() {
    }
}