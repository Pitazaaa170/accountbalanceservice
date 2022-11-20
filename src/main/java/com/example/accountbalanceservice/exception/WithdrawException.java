package com.example.accountbalanceservice.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WithdrawException extends RuntimeException{
    private long accountId;
    private static final String ERROR_MESSAGE = "Не удалось снять деньги со счёта %d ";

    @Override
    public String getMessage() {
        return String.format(ERROR_MESSAGE,accountId);
    }
}
