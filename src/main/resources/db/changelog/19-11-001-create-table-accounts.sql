--liquibase formatted sql

--changeset asvladimirov:1
CREATE TABLE accounts(
    account_id BIGSERIAL PRIMARY KEY ,
    user_id BIGINT NOT NULL,
    currency VARCHAR(32) NOT NULL,
    balance NUMERIC(18,2) NOT NULL DEFAULT 0.00
)


--changeset asvladimirov:2
CREATE TABLE transactions(
    account_id BIGINT NOT NULL,
    amount NUMERIC(18,2) NOT NULL,
    currency VARCHAR(32) NOT NULL,
    operation VARCHAR(32) NOT NULL,
    transaction_time TIMESTAMP NOT NULL
)