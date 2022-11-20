package com.example.accountbalanceservice.dto;

public record ReplenishAccountDto(
        long accountId,double amount,String currency
) {
}
