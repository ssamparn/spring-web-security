package com.spring.astabanksecurity.service;

import com.spring.astabanksecurity.entity.Cards;
import com.spring.astabanksecurity.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardsRepository cardsRepository;

    public List<Cards> getCardDetails(int customerId) {
        return cardsRepository.findByCustomerId(customerId);
    }
}
