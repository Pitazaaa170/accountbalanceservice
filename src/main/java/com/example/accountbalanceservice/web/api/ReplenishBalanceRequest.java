package com.example.accountbalanceservice.web.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ReplenishBalanceRequest {
    private long accountId;
    private double amount;
    private String currency;
}
