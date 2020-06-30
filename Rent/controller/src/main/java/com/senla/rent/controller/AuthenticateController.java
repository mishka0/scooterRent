package com.senla.rent.controller;

import com.senla.rent.api.dto.addition.AdditionDTO;
import com.senla.rent.api.dto.security.AuthenticationRequestDTO;
import com.senla.rent.api.dto.security.RegistrationRequestDTO;
import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.security.JwtTokenProvider;
import com.senla.rent.api.service.AdditionService;
import com.senla.rent.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticateController {

    private final UserService userService;

    private final JwtTokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    private final AdditionService additionService;

    public AuthenticateController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserService userService, AdditionService additionService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.additionService = additionService;
    }


    @PostMapping("/registration")
    public ResponseEntity register(
            @RequestBody RegistrationRequestDTO userReg,
            @RequestParam(required = false, value = "isModer", defaultValue = "false") boolean isModer)
    {
        if (userService.existUser(userReg.getLogin()))
        {
            Map<Object, Object> response = new HashMap<>();
            response.put("exception","Username is already taken!" );
            return  ResponseEntity.ok(response);
        }
        UserJWT user = userService.register(userReg, isModer);
        additionService.insertAddition(new AdditionDTO());
        String token = tokenProvider.createToken(user);
        Map<Object, Object> response = new HashMap<>();
        response.put("username", user.getUsername());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDTO requestDto) {
        try {
            String username = requestDto.getLogin();
            UserJWT user = userService.findByLogin(requestDto.getLogin());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            String token = tokenProvider.createToken(user);
            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}

