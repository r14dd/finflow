package com.finflow.wallet.dto;

import java.math.BigDecimal;

public class AccountResponse {

    private final String accountNumber;
    private final BigDecimal balance;
    private final String currency;

    public AccountResponse
            (
            String accountNumber,
            BigDecimal balance,
            String currency
            )
            {
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.currency = currency;
            }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }
}
