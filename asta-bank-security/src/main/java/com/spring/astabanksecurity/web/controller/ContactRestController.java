package com.spring.astabanksecurity.web.controller;

import com.spring.astabanksecurity.entity.Contact;
import com.spring.astabanksecurity.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContactRestController {

    private final ContactService contactService;

    @GetMapping("/contact")
    public ResponseEntity<Contact> saveContactInquiryDetails(@RequestBody Contact contact) {
        Contact savedContactInquiryDetails = contactService.saveContactInquiryDetails(contact);
        return new ResponseEntity<>(savedContactInquiryDetails, HttpStatus.CREATED);
    }
}
