package com.spring.astabanksecurity.service;

import com.spring.astabanksecurity.entity.Contact;
import com.spring.astabanksecurity.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public Contact saveContactInquiryDetails(Contact contact) {
        contact.setContactId(this.createServiceRequestNumber());
        contact.setCreationDate(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }

    public String createServiceRequestNumber() {
        Random random = new Random();
        int ranNum = random.nextInt(999999999 - 9999) + 9999;
        return "SR".concat(String.valueOf(ranNum));
    }
}
