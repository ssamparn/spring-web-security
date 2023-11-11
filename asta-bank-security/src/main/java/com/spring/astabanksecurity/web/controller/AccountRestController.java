package com.spring.astabanksecurity.web.controller;

import com.spring.astabanksecurity.entity.Accounts;
import com.spring.astabanksecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountRestController {

    private final AccountService accountService;

    @GetMapping("/accounts")
    public ResponseEntity<Accounts> getCustomerAccountDetails(@RequestParam int customerId) {
        Accounts accounts = accountService.getCustomerAccounts(customerId);
        if (accounts != null )
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        else
            return null;
    }
}
