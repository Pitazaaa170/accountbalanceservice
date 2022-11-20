package com.example.accountbalanceservice.exception;

import com.example.accountbalanceservice.web.api.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(ReplenishmentException exception) {
        log.error("Ошибка пополнения: {}", exception.getMessage());
        var body = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(body);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(WithdrawException exception) {
        log.error("Ошибка снятия денег со счёта: {}", exception.getMessage());
        var body = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(body);
    }
}