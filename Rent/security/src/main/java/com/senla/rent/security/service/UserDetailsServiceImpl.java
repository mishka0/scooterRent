package com.senla.rent.security.service;

import com.senla.rent.api.dao.UserRepository;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            log.error("Username with login: " + login + " not found!");
            throw new UsernameNotFoundException("User with username: " + login + " not found");
        }
        return new UserJWT(user);
    }
}
