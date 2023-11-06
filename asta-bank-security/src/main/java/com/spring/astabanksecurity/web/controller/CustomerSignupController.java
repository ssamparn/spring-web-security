package com.spring.astabanksecurity.web.controller;

import com.spring.astabanksecurity.entity.Customer;
import com.spring.astabanksecurity.mapper.CustomerMapper;
import com.spring.astabanksecurity.service.CustomerService;
import com.spring.astabanksecurity.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerSignupController {

    private final CustomerMapper customerMapper;
    private final CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<CustomerDto> signUp(@RequestBody CustomerDto customerDto) {
        Customer customerEntity = customerMapper.toCustomerEntity(customerDto);
        CustomerDto newCustomer = customerService.createCustomer(customerEntity);

        if (newCustomer.getId() > 0)
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
