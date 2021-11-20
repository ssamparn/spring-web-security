package com.spring.jwtissuer.controller;

import com.spring.jwtissuer.model.RequestData;
import com.spring.jwtissuer.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Slf4j
@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/api/jwe")
    public ResponseEntity<String> getToken(@RequestBody final RequestData request, Errors errors) {

        if (errors.hasErrors()) {
            String errorMessage = errors.getAllErrors().stream()
                    .map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
            log.error("Error = {}" , errorMessage);
            return ResponseEntity.badRequest().body(errorMessage);
        }

        String subject = request.getSubject();
        String jwe = tokenService.getJsonWebToken(request);
        String json = ("{\"subject\":\"" + subject
                + "\",\"token\":\"" + jwe + "\"}");

        log.info("Token generated for " + subject);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwe);

        log.info("Authorization header set with token");

        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }
}
