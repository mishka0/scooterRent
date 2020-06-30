package com.senla.rent.service;

import com.senla.rent.api.dao.UserRepository;
import com.senla.rent.api.dto.security.RegistrationRequestDTO;
import com.senla.rent.api.dto.user.UserInfoDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.security.JwtTokenProvider;
import com.senla.rent.api.service.RoleService;
import com.senla.rent.api.service.UserService;
import com.senla.rent.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;


    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleService roleService, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public UserJWT findByLogin(String login) {
        return new UserJWT(userRepository.findByLogin(login));
    }

    @Override
    public Integer getUserId(String username) {
        return userRepository.getUserId(username);
    }

    @Override
    public void updateUser(String token, UserInfoDTO userInfoDTO) {
        User userOld = userRepository.findByLogin(jwtTokenProvider.getUsername(token));
        modelMapper.map(userOld, userInfoDTO);
        userRepository.update(userOld);
    }

    @Override
    public UserInfoDTO getUserInfo(String token) {
        User user = userRepository.getUserWithAllInfo(jwtTokenProvider.getUsername(token));
        return modelMapper.map(user, UserInfoDTO.class);
    }

    @Override
    public UserJWT register(RegistrationRequestDTO userReg, boolean isModer) {
        if (userReg.getRoles() == null) {
            userReg.setPassword(passwordEncoder.encode(userReg.getPassword()));
            userReg.setRoles(new ArrayList<>());
            userReg.getRoles().add(roleService.findByName("USER"));
        }
        if (isModer) {
            userReg.getRoles().add(roleService.findByName("MODER"));
        }
        User user = modelMapper.map(userReg, User.class);
        userRepository.insert(user);


        return new UserJWT(user);
    }

    @Override
    public boolean existUser(String login) {
        return userRepository.existUser(login);
    }
}
