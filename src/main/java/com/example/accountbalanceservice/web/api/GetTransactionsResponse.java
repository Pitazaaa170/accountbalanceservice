package com.example.accountbalanceservice.web.api;

import com.example.accountbalanceservice.dao.entity.TransactionEntity;
import lombok.Value;

import java.util.List;

@Value
public class GetTransactionsResponse {
    List<TransactionEntity> userTransactions;
}
