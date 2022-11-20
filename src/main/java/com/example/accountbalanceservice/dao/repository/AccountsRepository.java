package com.example.accountbalanceservice.dao.repository;

import com.example.accountbalanceservice.dao.entity.AccountEntity;

import java.util.List;

public interface AccountsRepository {

    AccountEntity createAccount(long userId,String currency);

    boolean replenishBalanceByAccountId(double amount,long accountId);

    boolean withdrawBalanceByAccountId(double amount,long accountId);

    List<AccountEntity> getAllAccountsByUserId(long userId);

    List<Long> getAllAccountNumbersByUserId(long userId);
}
