package com.finflow.wallet.dto;

import java.util.List;

public class UserResponse {

    private final Long userId;
    private final List<AccountResponse> accounts;

    public UserResponse(Long userId, List<AccountResponse> accounts) {
        this.userId = userId;
        this.accounts = accounts;
    }

    public Long getUserId() {
        return userId;
    }

    public List<AccountResponse> getAccounts() {
        return accounts;
    }
}
