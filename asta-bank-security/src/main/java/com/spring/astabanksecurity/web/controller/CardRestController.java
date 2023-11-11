package com.spring.astabanksecurity.web.controller;

import com.spring.astabanksecurity.entity.Cards;
import com.spring.astabanksecurity.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardRestController {

    private final CardService cardService;
    @GetMapping("/card")
    public ResponseEntity<List<Cards>> getCardDetails(@RequestParam int customerId) {

        List<Cards> cards = cardService.getCardDetails(customerId);

        if (cards != null) {
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } else {
            return null;
        }
    }
}
