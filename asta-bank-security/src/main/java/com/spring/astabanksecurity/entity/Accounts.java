package com.spring.astabanksecurity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
public class Accounts {

    @Id
    @Column(name="account_number")
    private long accountNumber;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name="account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "creation_date")
    private String creationDate;
}
