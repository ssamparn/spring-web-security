package com.spring.astabanksecurity.web.controller;

import com.spring.astabanksecurity.entity.AccountTransactions;
import com.spring.astabanksecurity.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceRestController {

    private final BalanceService balanceService;

    @GetMapping("/balance")
    public ResponseEntity<List<AccountTransactions>> getBalanceDetails(@RequestParam int id) {
        List<AccountTransactions> accountTransactions = balanceService.getCustomerTransactions(id);

        if (accountTransactions != null ) {
            return new ResponseEntity<>(accountTransactions, HttpStatus.OK);
        } else {
            return null;
        }
    }
}
