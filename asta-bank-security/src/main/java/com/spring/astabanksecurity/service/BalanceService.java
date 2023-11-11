package com.spring.astabanksecurity.service;

import com.spring.astabanksecurity.entity.AccountTransactions;
import com.spring.astabanksecurity.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final AccountTransactionsRepository accountTransactionsRepository;

    public List<AccountTransactions> getCustomerTransactions(int customerId) {
        return accountTransactionsRepository.findByCustomerIdOrderByTransactionDateDesc(customerId);
    }
}
