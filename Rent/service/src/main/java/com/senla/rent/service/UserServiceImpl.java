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
import com.senla.rent.service.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.HashSet;

@Service
@Transactional
@Slf4j
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
        try {
            return modelMapper.map(userRepository.getUserWithAllInfo(id), UserFullInfoDTO.class);
        } catch (RuntimeException exception) {
            log.error("Can't get user info! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get user info!");
        }
    }

    @Override
    public void buySubscription(Integer id, SubscriptionInfoDTO subscriptionInfoDTO) {
        try {
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
        } catch (NotEnoughMoney notEnoughMoney) {
            log.error("not enough money exception!" + notEnoughMoney.getMessage());
            throw notEnoughMoney;
        } catch (RuntimeException exception) {
            log.error("Can't buy subscription! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't buy subscription!");
        }
    }

    @Override
    public User getUser(Integer id) {
        try {
            return userRepository.getUserWithAllInfo(id);
        } catch (RuntimeException exception) {
            log.error("Can't get user by ID! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't get user by ID!");
        }
    }

    @Override
    public void registerUser(RegistrationRequestDTO userReg, boolean isModer) {
        try {
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
        } catch (RuntimeException exception) {
            log.error("Can't register user! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't register user!");
        }
    }

    @Override
    public boolean existUser(String login) {
        try {
            return userRepository.existUser(login);
        } catch (RuntimeException exception) {
            log.error("Can't check user by ID! Message exception: " + exception.getMessage());
            throw new ServiceException("Can't check user by ID!");
        }
    }
}
