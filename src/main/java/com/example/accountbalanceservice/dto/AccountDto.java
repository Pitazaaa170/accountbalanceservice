package com.example.accountbalanceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private long accountId;
    private long userId;
    private String currency;
    private double balance;
}
