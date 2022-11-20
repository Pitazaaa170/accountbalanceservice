package com.example.accountbalanceservice.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
    private long accountId;
    private long userId;
    private String currency;
    private double balance;
}
