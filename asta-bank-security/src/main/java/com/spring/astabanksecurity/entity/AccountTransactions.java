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

import java.sql.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="account_transactions")
public class AccountTransactions {

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name="account_number")
    private long accountNumber;

    @Column(name = "customer_id")
    private int customerId;

    @Column(name="transaction_date")
    private Date transactionDate;

    @Column(name = "transaction_summary")
    private String transactionSummary;

    @Column(name="transaction_type")
    private String transactionType;

    @Column(name = "transaction_amount")
    private int transactionAmount;

    @Column(name = "closing_balance")
    private int closingBalance;

    @Column(name = "creation_date")
    private String creationDate;
}

