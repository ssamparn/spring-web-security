package com.spring.astabanksecurity.mapper;

import com.spring.astabanksecurity.entity.Customer;
import com.spring.astabanksecurity.web.model.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.create()
                .id(customer.getId())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .role(customer.getRole())
                .build();
    }

    public Customer toCustomerEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        customer.setRole(customerDto.getRole());
        return customer;
    }
}
