package com.senla.rent.controller;

import com.senla.rent.api.dto.addition.AdditionDTO;
import com.senla.rent.api.dto.security.AuthenticationRequestDTO;
import com.senla.rent.api.dto.security.RegistrationRequestDTO;
import com.senla.rent.api.security.JwtTokenProvider;
import com.senla.rent.api.service.AdditionService;
import com.senla.rent.api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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

    public AuthenticateController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }


    @PostMapping("/registration")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody RegistrationRequestDTO userReg,
            @RequestParam(required = false, value = "isModer", defaultValue = "false") boolean isModer)
    {
        if (userService.existUser(userReg.getLogin()))
        {
            Map<String, Object> response = new HashMap<>();
            response.put("exception","Username is already taken!" );
            return  ResponseEntity.ok(response);
        }
        userService.registerUser(userReg, isModer);
        Map<String, Object> response = new HashMap<>();
        response.put("username", userReg.getLogin());
        response.put("pass", userReg.getPassword());
        response.put("login page", "/login");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthenticationRequestDTO requestDto) {
        Authentication authentication;
        try {
            String username = requestDto.getLogin();
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            String token = tokenProvider.createToken((UserDetails) authentication.getPrincipal());
            Map<String, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}

