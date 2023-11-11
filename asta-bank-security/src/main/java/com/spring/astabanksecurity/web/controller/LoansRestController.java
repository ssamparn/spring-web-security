package com.spring.astabanksecurity.web.controller;

import com.spring.astabanksecurity.entity.Loans;
import com.spring.astabanksecurity.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansRestController {

    private final LoanService loanService;

    @GetMapping("/loan")
    public ResponseEntity<List<Loans>> getLoanDetails(@RequestParam int customerId) {
        List<Loans> loans = loanService.customerLoanDetails(customerId);

        if (loans != null) {
            return new ResponseEntity<>(loans, HttpStatus.OK);
        } else return null;
    }

}
