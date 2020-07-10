package com.senla.rent.dao.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.rent.api.dao.HistoryRepository;
import com.senla.rent.api.dao.SubscriptionRepository;
import com.senla.rent.dao.HistoryRepositoryImpl;
import com.senla.rent.dao.SubscriptionRepositoryImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class LaunchTestConfiguration extends AnnotationConfigApplicationContext {

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
