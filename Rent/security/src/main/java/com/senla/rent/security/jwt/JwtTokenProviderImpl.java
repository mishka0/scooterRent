package com.senla.rent.security.jwt;

import com.senla.rent.api.dto.user.UserJWT;
import com.senla.rent.api.security.JwtTokenProvider;
import com.senla.rent.security.exception.JwtAuthenticationException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProviderImpl.class);

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired.hours}")
    private long validityInHours;


    @Autowired
    private UserDetailsService userDetailsService;

    private String secretKey;

    @PostConstruct
    public void init() {
        secretKey = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    @Override
    public String createToken(UserJWT user) {

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", user.getAuthorities());
        Date now = new Date();
        Date validity = new Date(now.getTime() + (validityInHours * 60 * 60 * 1000));

        return Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, secretKey)//
                .compact();
    }

    @Override
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    @Override
    public String getUsername(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token.substring(7, token.length())).getBody().getSubject();
        }
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    @Override
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
            throw new JwtAuthenticationException(ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
            throw new JwtAuthenticationException(ex.getMessage());
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
            throw new JwtAuthenticationException(ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
            throw new JwtAuthenticationException(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
            throw new JwtAuthenticationException(ex.getMessage());
        }
    }
}
