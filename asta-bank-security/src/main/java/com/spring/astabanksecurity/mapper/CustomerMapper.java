package com.spring.astabanksecurity.mapper;

import com.spring.astabanksecurity.entity.Customer;
import com.spring.astabanksecurity.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final PasswordEncoder passwordEncoder;

    public CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.create()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .mobileNumber(customer.getMobileNumber())
                .creationDate(customer.getCreationDate())
                .role(customer.getRole())
                .message("User registered successfully")
                .build();
    }

    public Customer toCustomerEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setCreationDate(String.valueOf(new Date(System.currentTimeMillis())));
        customer.setRole(customerDto.getRole());
        return customer;
    }
}
