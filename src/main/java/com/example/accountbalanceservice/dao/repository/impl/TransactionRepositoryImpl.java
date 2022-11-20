package com.example.accountbalanceservice.dao.repository.impl;

import com.example.accountbalanceservice.dao.entity.TransactionEntity;
import com.example.accountbalanceservice.dao.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TransactionRepositoryImpl implements TransactionRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String INSERT_TRANSACTION = "INSERT INTO transactions(account_id,amount,currency,operation,transaction_time) " +
            "VALUES (:accountId,:amount,:currency,:operation,:transactionTime)";
    private static final String SELECT_TRANSACTIONS_BY_USER_ID = "SELECT * from transactions " +
            "WHERE account_id = :accountId";

    @Override
    public int saveTransactionData(long accountId, double amount, String currency, String operation) {
        return namedParameterJdbcTemplate.update(INSERT_TRANSACTION, Map.of("accountId",accountId,
                "amount",amount,
                "currency",currency,
                "operation",operation,
                "transactionTime", LocalDateTime.now()));
    }

    @Override
    public List<TransactionEntity> getTransactionsByAccountId(long accountId) {
        try {
            return namedParameterJdbcTemplate.query(SELECT_TRANSACTIONS_BY_USER_ID,Map.of("accountId",accountId),(rs,rn) ->
                    new TransactionEntity(rs.getLong("account_id"),
                            rs.getDouble("amount"),
                            rs.getString("currency"),
                            rs.getString("operation"),
                            rs.getTimestamp("transaction_time").toLocalDateTime()));
        } catch (EmptyResultDataAccessException e) {
            return List.of();
        }
    }
}
