package com.example.currency_exchange.models.dto;

public class AccountDto {
    private String login;

    private String password;

    public AccountDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
