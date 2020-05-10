package com.spring.httpswebsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @RequestMapping("/home")
    public String get() {
        return "Welcome to Https";
    }
}
