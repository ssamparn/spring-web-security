package com.spring.astabanksecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansRestController {

    @GetMapping("/loan")
    public String getLoanDetails() {
        return "Here are the loan details from the DB";
    }

}
