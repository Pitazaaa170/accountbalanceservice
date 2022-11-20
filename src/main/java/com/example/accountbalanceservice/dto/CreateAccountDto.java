package com.example.accountbalanceservice.dto;

public record CreateAccountDto(
        long userId,
        String currency
) {
}
