package com.example.currency_exchange.models.dto;

public class ExchangeDto {

    private String login;

    private double rubles;

    private double dollars;

    private double euros;

    private String curIn;

    private String curOut;

    public ExchangeDto(String login, double rubles, double dollars, double euros, String curIn, String curOut) {
        this.login = login;
        this.rubles = rubles;
        this.dollars = dollars;
        this.euros = euros;
        this.curIn = curIn;
        this.curOut = curOut;
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

    public String getCurIn() {
        return curIn;
    }

    public String getCurOut() {
        return curOut;
    }

}
