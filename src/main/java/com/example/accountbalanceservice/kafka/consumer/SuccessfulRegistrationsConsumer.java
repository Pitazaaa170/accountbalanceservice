package com.example.accountbalanceservice.kafka.consumer;

import com.example.accountbalanceservice.config.CommonCurrencyProperties;
import com.example.accountbalanceservice.dto.CreateAccountDto;
import com.example.accountbalanceservice.kafka.message.SuccessfulRegistrationMessage;
import com.example.accountbalanceservice.service.AccountsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SuccessfulRegistrationsConsumer {
    private final AccountsService accountsService;
    private final CommonCurrencyProperties commonCurrencyProperties;

    @KafkaListener(topics = "successful_registrations")
    public void receive(SuccessfulRegistrationMessage successfulRegistrationMessage) {
        log.debug("Плучены данные из топика successful_registrations {}",successfulRegistrationMessage);
        accountsService.createAccount(new CreateAccountDto(successfulRegistrationMessage.getUserId(),commonCurrencyProperties.getCommonCurrency()));
    }
}
