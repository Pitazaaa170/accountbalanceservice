package com.example.accountbalanceservice.dao.repository.impl;

import com.example.accountbalanceservice.dao.entity.AccountEntity;
import com.example.accountbalanceservice.dao.repository.AccountsRepository;
import liquibase.pro.packaged.O;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AccountsRepositoryImpl implements AccountsRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String INSERT_INTO_ACCOUNTS = "INSERT INTO accounts (user_id,currency) " +
            "VALUES (:userId,:currency)";
    private static final String SELECT_FROM_ACCOUNT_BY_USER_ID = "SELECT * " +
            "FROM accounts " +
            "WHERE user_id = :userId";
    private static final String SELECT_CURRENT_ACCOUNT_ID = "SELECT currval(pg_get_serial_sequence('accounts', 'account_id'))";
    private static final String UPDATE_ACCOUNT_BALANCE_REPLENISH = "UPDATE accounts " +
            "SET balance = balance + :amount " +
            "WHERE account_id = :accountId";
    private static final String UPDATE_ACCOUNT_BALANCE_WITHDRAW = "UPDATE accounts " +
            "SET balance = balance - :amount " +
            "WHERE account_id = :accountId";
    private static final String SELECT_ACCOUNT_NUMBERS_BY_USER_ID = "SELECT account_id FROM accounts " +
            "WHERE user_id = :userId";

    @Override
    public AccountEntity createAccount(long userId,String currency) {
        log.debug("Вызван метод createAccount AccountsRepositoryImpl с аргументами {}, {}",userId,currency);
        namedParameterJdbcTemplate.update(INSERT_INTO_ACCOUNTS, Map.of("userId",userId,
                "currency",currency));
        var accountId = namedParameterJdbcTemplate.queryForObject(SELECT_CURRENT_ACCOUNT_ID,Map.of(),(rs,rn) -> rs.getLong(1));
        return new AccountEntity(accountId,userId,currency,0);
    }

    @Override
    public boolean replenishBalanceByAccountId(double amount, long accountId) {
        try {
            return namedParameterJdbcTemplate.update(UPDATE_ACCOUNT_BALANCE_REPLENISH,Map.of("amount",amount,"accountId",accountId)) >= 1 ;
        } catch (Exception e) {
            log.warn("Ошибка при попытке пополнения счёта {}",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean withdrawBalanceByAccountId(double amount, long accountId) {
        try {
            return namedParameterJdbcTemplate.update(UPDATE_ACCOUNT_BALANCE_WITHDRAW,Map.of("amount",amount,"accountId",accountId)) >= 1 ;
        } catch (Exception e) {
            log.warn("Ошибка при попытке пополнения счёта {}",e.getMessage());
            return false;
        }
    }

    @Override
    public List<AccountEntity> getAllAccountsByUserId(long userId) {
        try {
            return namedParameterJdbcTemplate.query(SELECT_FROM_ACCOUNT_BY_USER_ID,Map.of("userId",userId),(rs,rn) ->
                    new AccountEntity(rs.getLong("account_id"),
                            userId,rs.getString("currency"),
                            rs.getDouble("balance")));
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }

    @Override
    public List<Long> getAllAccountNumbersByUserId(long userId) {
        try {
            return namedParameterJdbcTemplate.query(SELECT_ACCOUNT_NUMBERS_BY_USER_ID,Map.of("userId",userId),(rs,rn) ->
                    rs.getLong("account_id"));
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }
}
