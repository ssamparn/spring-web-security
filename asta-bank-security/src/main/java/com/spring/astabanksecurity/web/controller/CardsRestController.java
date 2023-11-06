package com.spring.astabanksecurity.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsRestController {

    @GetMapping("/card")
    public String getCardDetails() {
        return "Here are the card details from the DB";
    }

}
