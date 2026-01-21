package com.finflow.wallet.dto;

public class LoginResponse {

    private String token;

    // REQUIRED by Jackson
    public LoginResponse() {
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
