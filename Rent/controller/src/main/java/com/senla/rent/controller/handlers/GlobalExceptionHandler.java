package com.senla.rent.controller.handlers;

import com.senla.rent.controller.exceptions.BadFormatDateException;
import com.senla.rent.dao.exceptions.NullDaoException;
import com.senla.rent.security.exception.JwtAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Deprecated
    @ExceptionHandler(BadFormatDateException.class)
    public ResponseEntity<Object> handleBadFormatDateException(BadFormatDateException exception){
        Map<String, Object> body = new HashMap<>();
        body.put("exception", exception.getClass());
        body.put("message", exception.getMessage());
        logger.error(body.toString());
        return new ResponseEntity<>(body, BAD_REQUEST);
    }

    @ExceptionHandler(NullDaoException.class)
    public ResponseEntity<Object> handleNullDaoException(NullDaoException exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("exception", exception.getClass());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleInvalidJwtAuthentication(AuthenticationException exception) {
//        log.debug("handling InvalidJwtAuthenticationException...");
        Map<String, Object> body = new HashMap<>();
        body.put("exception", exception.getClass());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, UNAUTHORIZED);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException exception) {
//        log.debug("handling InvalidJwtAuthenticationException...");
        Map<String, Object> body = new HashMap<>();
        body.put("exception", exception.getClass());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherException(Exception exception) {
        Map<String, Object> body = new HashMap<>();
        body.put("exception", exception.getClass());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, NOT_FOUND);
    }
}
