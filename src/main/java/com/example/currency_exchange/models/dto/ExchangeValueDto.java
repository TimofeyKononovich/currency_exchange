package com.example.currency_exchange.models.dto;

public class ExchangeValueDto {
    private double buyRubles;

    private double sellRubles;

    private double buyDollars;

    private double sellDollars;

    private double buyEuoros;

    private double sellEuoros;

    public ExchangeValueDto(double buyRubles, double sellRubles, double buyDollars, double sellDollars, double buyEuoros, double sellEuoros) {
        this.buyRubles = buyRubles;
        this.sellRubles = sellRubles;
        this.buyDollars = buyDollars;
        this.sellDollars = sellDollars;
        this.buyEuoros = buyEuoros;
        this.sellEuoros = sellEuoros;
    }

    public double getBuyRubles() {
        return buyRubles;
    }

    public double getSellRubles() {
        return sellRubles;
    }

    public double getBuyDollars() {
        return buyDollars;
    }

    public double getSellDollars() {
        return sellDollars;
    }

    public double getBuyEuoros() {
        return buyEuoros;
    }

    public double getSellEuoros() {
        return sellEuoros;
    }
}
