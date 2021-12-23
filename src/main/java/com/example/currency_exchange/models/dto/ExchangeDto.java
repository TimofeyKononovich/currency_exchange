package com.example.currency_exchange.models.dto;

public class ExchangeDto {

    private String login;

    private double rubles;

    private double dollars;

    private double euros;

    public ExchangeDto(String login, double rubles, double dollars, double euros) {
        this.login = login;
        this.rubles = rubles;
        this.dollars = dollars;
        this.euros = euros;
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
