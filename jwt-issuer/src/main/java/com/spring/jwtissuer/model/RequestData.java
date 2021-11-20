package com.spring.jwtissuer.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestData {

    private String subject;
    private String user;
    private String account;

}
