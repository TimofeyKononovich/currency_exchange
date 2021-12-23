package com.example.currency_exchange.models.dto;

public class ClientDto {
    private String password;

    private String login;

    private double rubles;

    private double dollars;

    private double euros;

    public ClientDto(String password, String login, double rubles, double dollars, double euros) {
        this.password = password;
        this.login = login;
        this.rubles = rubles;
        this.dollars = dollars;
        this.euros = euros;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public double getRubles() {
        return rubles;
    }

    public double getDollars() {
        return dollars;
    }

    public double getEuros() {
        return euros;
    }
}
