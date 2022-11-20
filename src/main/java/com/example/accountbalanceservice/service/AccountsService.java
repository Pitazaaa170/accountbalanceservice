package com.example.accountbalanceservice.service;

import com.example.accountbalanceservice.dto.AccountDto;
import com.example.accountbalanceservice.dto.CreateAccountDto;
import com.example.accountbalanceservice.dto.ReplenishAccountDto;
import com.example.accountbalanceservice.dto.WithdrawAccountDto;

import java.util.List;

public interface AccountsService {

    long createAccount(CreateAccountDto createAccountDto);

    void replenishAccount(ReplenishAccountDto replenishAccountDto);

    void withdrawAccount(WithdrawAccountDto withdrawAccountDto);

    List<AccountDto> getAllAccountsByUserId(long userId);


}
