package com.senla.rent.configs.spring;


import com.senla.rent.configs.hibernate.HibernateConfig;
import com.senla.rent.configs.mapper.MapperConfig;
import com.senla.rent.configs.security.SecurityWebConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@EnableWebMvc
@ComponentScan("com.senla.rent")
@Configuration
@EnableJpaRepositories(basePackages = "com.senla.rent")
public class SpringConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { SpringConfig.class, HibernateConfig.class, MapperConfig.class, SecurityWebConfigurer.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}