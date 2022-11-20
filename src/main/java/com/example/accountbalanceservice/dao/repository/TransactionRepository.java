package com.example.accountbalanceservice.dao.repository;

import com.example.accountbalanceservice.dao.entity.TransactionEntity;

import java.util.List;

public interface TransactionRepository {

    int saveTransactionData(long accountId,double amount,String currency,String operation);

    List<TransactionEntity> getTransactionsByAccountId(long accountId);
}
