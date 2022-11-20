package com.example.accountbalanceservice.service;

import com.example.accountbalanceservice.dao.entity.TransactionEntity;
import com.example.accountbalanceservice.dto.TransactionDataDto;

import java.util.List;

public interface TransactionService {

    void saveTransactionData(TransactionDataDto transactionDataDto);

    List<TransactionEntity> getAllTransactionsByUserId(long userId);
}
