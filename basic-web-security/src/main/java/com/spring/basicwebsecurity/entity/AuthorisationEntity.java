package com.spring.basicwebsecurity.entity;

import com.spring.basicwebsecurity.model.AuthorityType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "AUTHORITIES")
public class AuthorisationEntity implements Serializable {

    private static final long serialVersionUID = 243467045L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long authorisationId;


    @Column(name = "username", length = 50)
    private String username;


    @Column(name = "authority", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorityType authorityType;


}
