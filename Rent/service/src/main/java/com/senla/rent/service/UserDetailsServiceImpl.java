package com.senla.rent.service;


import com.senla.rent.api.dao.UserRepository;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.service.UserService;
import com.senla.rent.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + login + " not found");
        }
        return new UserJWT(user);
    }
}
