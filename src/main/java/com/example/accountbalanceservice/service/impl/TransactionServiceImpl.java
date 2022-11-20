package com.example.accountbalanceservice.service.impl;

import com.example.accountbalanceservice.dao.entity.TransactionEntity;
import com.example.accountbalanceservice.dao.repository.AccountsRepository;
import com.example.accountbalanceservice.dao.repository.TransactionRepository;
import com.example.accountbalanceservice.dto.TransactionDataDto;
import com.example.accountbalanceservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountsRepository accountsRepository;

    @Override
    public void saveTransactionData(TransactionDataDto transactionDataDto) {
        transactionRepository.saveTransactionData(transactionDataDto.accountId(),
                transactionDataDto.amount(),transactionDataDto.currency(),transactionDataDto.operation());
    }

    @Override
    public List<TransactionEntity> getAllTransactionsByUserId(long userId) {
        var accountNumbers = accountsRepository.getAllAccountNumbersByUserId(userId);
        List<TransactionEntity> transactionEntities = new ArrayList<>();
        accountNumbers.forEach(accountNumber -> transactionEntities.addAll(transactionRepository.getTransactionsByAccountId(accountNumber)));
        return transactionEntities;
    }
}
