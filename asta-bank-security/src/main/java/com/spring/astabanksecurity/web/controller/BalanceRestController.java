package com.spring.astabanksecurity.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceRestController {

    @GetMapping("/balance")
    public String getBalanceDetails() {
        return "Here are the balance details from the DB";
    }
}
