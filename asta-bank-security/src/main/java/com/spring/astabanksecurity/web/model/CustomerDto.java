package com.spring.astabanksecurity.web.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "create")
public class CustomerDto {
    private int customerId;
    private String name;
    private String email;
    private String password;
    private String mobileNumber;
    private String role;
    private String creationDate;
    private String message;
}
