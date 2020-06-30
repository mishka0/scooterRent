package com.senla.rent.service;


import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        UserJWT user = userService.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + login + " not found");
        }
       return user;
    }
}
