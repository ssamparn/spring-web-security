package com.spring.basicwebsecurity.repository;

import com.spring.basicwebsecurity.entity.AuthorisationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorisationRepository extends JpaRepository<AuthorisationEntity, Long> {

}
