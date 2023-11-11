package com.spring.astabanksecurity.repository;

import com.spring.astabanksecurity.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
