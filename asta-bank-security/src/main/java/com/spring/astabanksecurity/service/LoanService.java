package com.spring.astabanksecurity.service;

import com.spring.astabanksecurity.entity.Loans;
import com.spring.astabanksecurity.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    public List<Loans> customerLoanDetails(int customerId) {
        return loanRepository.findByCustomerIdOrderByStartDateDesc(customerId);
    }
}
