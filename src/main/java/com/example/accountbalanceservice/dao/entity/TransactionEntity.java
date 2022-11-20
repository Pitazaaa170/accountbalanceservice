package com.example.accountbalanceservice.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class TransactionEntity {
    private long accountId;
    private double amount;
    private String currency;
    private String operation;
    private LocalDateTime transactionTime;
}
