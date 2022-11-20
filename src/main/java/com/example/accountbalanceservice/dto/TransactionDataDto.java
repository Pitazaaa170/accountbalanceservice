package com.example.accountbalanceservice.dto;

public record TransactionDataDto(
        long accountId,double amount,String currency,String operation
) {
}
