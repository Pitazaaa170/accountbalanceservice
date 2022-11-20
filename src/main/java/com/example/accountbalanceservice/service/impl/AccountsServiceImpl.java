package com.example.accountbalanceservice.service.impl;

import com.example.accountbalanceservice.dao.entity.AccountEntity;
import com.example.accountbalanceservice.dao.repository.AccountsRepository;
import com.example.accountbalanceservice.dto.*;
import com.example.accountbalanceservice.exception.ReplenishmentException;
import com.example.accountbalanceservice.exception.WithdrawException;
import com.example.accountbalanceservice.service.AccountsService;
import com.example.accountbalanceservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository accountsRepository;
    private final TransactionService transactionService;

    @Override
    @Transactional
    public long createAccount(CreateAccountDto createAccountDto) {
        log.debug("Вызван метод createAccount AccountsServiceImpl с аргументами {}",createAccountDto);
        var createdAccount = accountsRepository.createAccount(createAccountDto.userId(), createAccountDto.currency());
        return createdAccount.getAccountId();
    }

    @Override
    @Transactional
    public void replenishAccount(ReplenishAccountDto replenishAccountDto) {
        if (accountsRepository.replenishBalanceByAccountId(replenishAccountDto.amount(),replenishAccountDto.accountId())) {
            transactionService.saveTransactionData(new TransactionDataDto(replenishAccountDto.accountId(),
                    replenishAccountDto.amount(),
                    replenishAccountDto.currency(),
                    Operation.REPLENISH.name()));
        } else {
            throw new ReplenishmentException(replenishAccountDto.accountId());
        }
    }

    @Override
    public void withdrawAccount(WithdrawAccountDto withdrawAccountDto) {
        if (accountsRepository.withdrawBalanceByAccountId(withdrawAccountDto.amount(), withdrawAccountDto.accountId())) {
            transactionService.saveTransactionData(new TransactionDataDto(withdrawAccountDto.accountId(),
                    withdrawAccountDto.amount(), withdrawAccountDto.currency(),Operation.WITHDRAW.name()));
        } else {
            throw new WithdrawException(withdrawAccountDto.accountId());
        }
    }

    @Override
    public List<AccountDto> getAllAccountsByUserId(long userId) {
        var accountsEntities = accountsRepository.getAllAccountsByUserId(userId);
        return accountsEntities.stream()
                .map(entity -> new AccountDto(entity.getAccountId(), entity.getUserId(), entity.getCurrency(), entity.getBalance()))
                .collect(Collectors.toList());
    }
}
