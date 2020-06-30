package com.senla.rent.dao.configs;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.senla.rent.configs.hibernate.HibernateConfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@ComponentScan({"com.senla.rent.dao"})
public class LaunchTestConfiguration extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{LaunchTestConfiguration.class, HibernateTestConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Bean
//    public MockMvc createMockMvc() {
//        return MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @Bean
    public ObjectMapper createObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ModelMapper createMapper() {
        return new ModelMapper();
    }

}
