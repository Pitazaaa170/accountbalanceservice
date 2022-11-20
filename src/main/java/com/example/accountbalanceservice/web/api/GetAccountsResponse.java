package com.example.accountbalanceservice.web.api;

import com.example.accountbalanceservice.dto.AccountDto;
import lombok.Value;

import java.util.List;

@Value
public class GetAccountsResponse {
    List<AccountDto> userAccounts;
}
