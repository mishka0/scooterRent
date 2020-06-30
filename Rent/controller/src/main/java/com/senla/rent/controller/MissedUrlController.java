package com.senla.rent.controller;


import com.senla.rent.controller.exceptions.MissedUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MissedUrlController {
    @RequestMapping("/")
    public ResponseEntity<Object> hello(){
        Map<String, Object> body = new HashMap<>();
        String message = "Hello!";
        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @RequestMapping("/**")
    public void error() throws MissedUrlException {
        throw new MissedUrlException("Missed url!");
    }
}
