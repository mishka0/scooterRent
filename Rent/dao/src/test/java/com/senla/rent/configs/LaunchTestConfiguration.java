package com.senla.rent.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.rent.api.service.springdata.RentPointSpringDataService;
import com.senla.rent.dao.HistoryRepositoryImpl;
import com.senla.rent.dao.SubscriptionRepositoryImpl;
import com.senla.rent.dao.UserRepositoryImpl;
import com.senla.rent.api.dao.AdditionRepository;
import com.senla.rent.api.dao.HistoryRepository;
import com.senla.rent.api.dao.SubscriptionRepository;
import com.senla.rent.api.dao.UserRepository;
import com.senla.rent.dao.AdditionRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;

public class LaunchTestConfiguration extends AnnotationConfigApplicationContext {

    @Bean
    public AdditionRepository additionRepository(){
        return new AdditionRepositoryImpl();
    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public UserRepository userRepository(){
        return new UserRepositoryImpl();
    }

    @Bean
    public SubscriptionRepository createSubsRepo(){
        return new SubscriptionRepositoryImpl();
    }

    @Bean
    public HistoryRepository historyRepository(){
        return new HistoryRepositoryImpl();
    }

    @Bean
    public ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
