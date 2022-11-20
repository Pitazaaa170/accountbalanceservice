package com.example.accountbalanceservice.web.controller;

import com.example.accountbalanceservice.dto.CreateAccountDto;
import com.example.accountbalanceservice.dto.ReplenishAccountDto;
import com.example.accountbalanceservice.dto.WithdrawAccountDto;
import com.example.accountbalanceservice.service.AccountsService;
import com.example.accountbalanceservice.service.TransactionService;
import com.example.accountbalanceservice.web.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.name}/v1")
public class AccountController {
    private final AccountsService accountsService;
    private final TransactionService transactionService;

    @PostMapping("/account")
    public ResponseEntity<OpenAccountResponse> openAccount(
            @RequestBody OpenAccountRequest openAccountRequest
    ) {
        return ResponseEntity.ok(new OpenAccountResponse(accountsService.createAccount(
                new CreateAccountDto(openAccountRequest.getUserId(), openAccountRequest.getCurrency()))));
    }

    @PutMapping("/balance/replenish")
    public ResponseEntity<ReplenishBalanceResponse> replenishBalance(
            @RequestBody ReplenishBalanceRequest replenishBalanceRequest
    ) {
        accountsService.replenishAccount(new ReplenishAccountDto(replenishBalanceRequest.getAccountId(),
                replenishBalanceRequest.getAmount(), replenishBalanceRequest.getCurrency()));
        return ResponseEntity.ok(new ReplenishBalanceResponse(true));
    }

    @PutMapping("/balance/withdraw")
    public ResponseEntity<WithdrawBalanceResponse> withdrawBalance(
            @RequestBody WithdrawBalanceRequest withdrawBalanceRequest
    ) {
        accountsService.withdrawAccount(new WithdrawAccountDto(withdrawBalanceRequest.getAccountId(),
                withdrawBalanceRequest.getAmount(), withdrawBalanceRequest.getCurrency()));
        return ResponseEntity.ok(new WithdrawBalanceResponse(true));
    }

    @GetMapping("/accounts/{userId}")
    public ResponseEntity<GetAccountsResponse> getAccountsByUserId(
            @PathVariable long userId
    ) {
        return ResponseEntity.ok(new GetAccountsResponse(accountsService.getAllAccountsByUserId(userId)));
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<GetTransactionsResponse> getTransactionsByUserId(
            @PathVariable long userId
    ) {
        return ResponseEntity.ok(new GetTransactionsResponse(transactionService.getAllTransactionsByUserId(userId)));
    }

}
