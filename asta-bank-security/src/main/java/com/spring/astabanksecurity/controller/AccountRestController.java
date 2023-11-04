package com.spring.astabanksecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {

    @GetMapping("/account")
    public String getAccountDetails() {
        return "Account Details from Database";
    }
}
