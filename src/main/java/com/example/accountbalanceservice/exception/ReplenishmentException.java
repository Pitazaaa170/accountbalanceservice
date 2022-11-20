package com.example.accountbalanceservice.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReplenishmentException extends RuntimeException{
    private long accountId;
    private static final String ERROR_MESSAGE = "Не удалось пополнить счёт %d ";

    @Override
    public String getMessage() {
        return String.format(ERROR_MESSAGE,accountId);
    }
}
