package com.senla.rent.configs.security;


import com.senla.rent.controller.handlers.GlobalExceptionHandler;
import com.senla.rent.security.filter.ExceptionHandlerFilter;
import com.senla.rent.security.filter.JwtFilter;
import com.senla.rent.security.jwt.JwtAccessDeniedHandlerImpl;
import com.senla.rent.security.jwt.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;


@EnableWebSecurity
@PropertySource("classpath:jwt.properties")
public class SecurityWebConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    public ExceptionHandlerFilter exceptionHandlerFilter;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtAccessDeniedHandlerImpl jwtAccessDeniedHandler;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/me").hasRole("USER")
                .anyRequest().authenticated();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionHandlerFilter, WebAsyncManagerIntegrationFilter.class);
    }
}
