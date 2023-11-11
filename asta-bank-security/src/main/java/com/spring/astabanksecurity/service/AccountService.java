package com.spring.astabanksecurity.service;

import com.spring.astabanksecurity.entity.Accounts;
import com.spring.astabanksecurity.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountsRepository accountsRepository;

    public Accounts getCustomerAccounts(int id) {
        return accountsRepository.findByCustomerId(id);
    }
}
