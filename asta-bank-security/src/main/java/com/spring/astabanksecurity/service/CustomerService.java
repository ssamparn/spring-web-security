package com.spring.astabanksecurity.service;

import com.spring.astabanksecurity.entity.Customer;
import com.spring.astabanksecurity.mapper.CustomerMapper;
import com.spring.astabanksecurity.repository.CustomerRepository;
import com.spring.astabanksecurity.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerDto createCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toCustomerDto(savedCustomer);
    }
}
