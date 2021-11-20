package com.spring.jwtconsumer.controller;

import com.spring.jwtconsumer.service.TokenValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class HomePageController {

    @Autowired
    private TokenValidator tokenValidator;

    @GetMapping("/")
    public ResponseEntity<String> index(HttpServletRequest request) {

        String output = ("<html><head><title>Service Provider</title></head>"
                + "<body><h1>Error</h1><p>Invalid token</p></body></html>");

        if(tokenValidator.isValid(request)) {
            String greeting = ("Hello " + tokenValidator.getUser());
            String account = ("Account # " + tokenValidator.getAccount());
            output = output.replaceAll("Error", "Welcome")
                    .replaceAll("Invalid token", greeting + "</p><p>" + account);
        }

        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
