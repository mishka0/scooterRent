package com.senla.rent.controller.handlers;

import com.senla.rent.dao.exceptions.NullDaoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NullDaoException.class)
    public ResponseEntity<Object> handleNullDaoException(NullDaoException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("exception", exception.getClass());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleInvalidJwtAuthentication(AuthenticationException exception) {
//        log.debug("handling InvalidJwtAuthenticationException...");
        Map<String, Object> body = new HashMap<>();
        body.put("exception", exception.getClass());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
//        log.debug("handling InvalidJwtAuthenticationException...");
        Map<String, Object> body = new HashMap<>();
        body.put("exception", exception.getClass());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherException(Exception exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("exception", exception.getClass());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
