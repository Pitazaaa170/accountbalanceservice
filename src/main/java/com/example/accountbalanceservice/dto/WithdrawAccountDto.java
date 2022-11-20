package com.example.accountbalanceservice.dto;

public record WithdrawAccountDto(
        long accountId,double amount,String currency
) {
}
