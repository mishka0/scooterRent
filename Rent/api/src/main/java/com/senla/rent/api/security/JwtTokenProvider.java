package com.senla.rent.api.security;

import com.senla.rent.api.dto.user.UserJWT;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface JwtTokenProvider {

    String createToken(UserJWT user);

    Authentication getAuthentication(String token);

    String getUsername(String token);

    String resolveToken(HttpServletRequest req);

    boolean validateToken(String token);
}
