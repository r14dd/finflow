package com.finflow.wallet.dto;

import java.math.BigDecimal;

public class CreateUserRequest {

    private String name;
    private String currency;
    private BigDecimal initialBalance;

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }
}
