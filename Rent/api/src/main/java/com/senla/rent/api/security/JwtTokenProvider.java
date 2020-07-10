package com.senla.rent.api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import javax.servlet.http.HttpServletRequest;

public interface JwtTokenProvider {

    String createToken(UserDetails user);

    Authentication getAuthentication(String token);

    String getUsername(String token);

    String resolveToken(HttpServletRequest req);

    boolean validateToken(String token);
}
