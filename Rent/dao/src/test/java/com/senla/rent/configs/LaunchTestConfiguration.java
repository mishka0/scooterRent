package com.senla.rent.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.rent.HistoryRepositoryImpl;
import com.senla.rent.SubscriptionRepositoryImpl;
import com.senla.rent.UserRepositoryImpl;
import com.senla.rent.api.dao.AdditionRepository;
import com.senla.rent.api.dao.HistoryRepository;
import com.senla.rent.api.dao.SubscriptionRepository;
import com.senla.rent.api.dao.UserRepository;
import com.senla.rent.AdditionRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class LaunchTestConfiguration extends AnnotationConfigApplicationContext {

    @Bean
    public AdditionRepository additionRepository(){
        return new AdditionRepositoryImpl();
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
