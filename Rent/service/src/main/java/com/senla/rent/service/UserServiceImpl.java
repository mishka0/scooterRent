package com.senla.rent.service;

import com.senla.rent.api.dao.UserRepository;
import com.senla.rent.api.dto.security.RegistrationRequestDTO;
import com.senla.rent.api.dto.subscription.SubscriptionInfoDTO;
import com.senla.rent.api.dto.user.UserFullInfoDTO;
import com.senla.rent.api.security.JwtTokenProvider;
import com.senla.rent.api.service.RoleService;
import com.senla.rent.api.service.SubscriptionInfoService;
import com.senla.rent.api.service.UserService;
import com.senla.rent.entity.Addition;
import com.senla.rent.entity.Subscription;
import com.senla.rent.entity.SubscriptionInfo;
import com.senla.rent.entity.User;
import com.senla.rent.service.exceptions.NotEnoughMoney;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.HashSet;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final SubscriptionInfoService subscriptionInfoService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleService roleService, SubscriptionInfoService subscriptionInfoService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.subscriptionInfoService = subscriptionInfoService;
    }

    @Override
    public UserFullInfoDTO getUserInfo(Integer id) {
        return modelMapper.map(userRepository.getUserWithAllInfo(id), UserFullInfoDTO.class);
    }

    @Override
    public void buySubscription(Integer id, SubscriptionInfoDTO subscriptionInfoDTO) {
        User user = userRepository.getUserWithAllInfo(id);
        if (user.getAddition().getBalance() >= subscriptionInfoDTO.getCost()) {
            user.getSubscriptions().add(new Subscription(Duration.parse(subscriptionInfoDTO.getTime()), subscriptionInfoService.getSubsInfo(subscriptionInfoDTO.getId()), user));
            user.getAddition().setBalance(user.getAddition().getBalance() - subscriptionInfoDTO.getCost());
            userRepository.update(user);
        } else {
            throw new NotEnoughMoney(
                    "User balance is : "
                            + user.getAddition().getBalance()
                            + " but cost of subs is : "
                            + subscriptionInfoDTO.getCost());
        }
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.getUserWithAllInfo(id);
    }

    @Override
    public void registerUser(RegistrationRequestDTO userReg, boolean isModer) {
        User user = modelMapper.map(userReg, User.class);
        if (user.getRoles() == null) {
            user.setPassword(passwordEncoder.encode(userReg.getPassword()));
            user.setRoles(new HashSet<>());
            user.getRoles().add(roleService.findByName("USER"));
        }
        if (isModer) {
            user.getRoles().add(roleService.findByName("MODER"));
        }
        user.setAddition(new Addition());
        userRepository.insert(user);
    }

    @Override
    public boolean existUser(String login) {
        return userRepository.existUser(login);
    }
}
