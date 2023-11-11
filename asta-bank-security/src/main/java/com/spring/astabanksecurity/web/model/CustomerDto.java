package com.spring.astabanksecurity.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "create")
public class CustomerDto {
    private int customerId;
    private String email;
    private String password;
    private String role;
}
