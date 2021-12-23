package com.example.currency_exchange.models;

public class SetExchangeBar {

    private double buyRubles;

    private double sellRubles;

    private double buyDollars;

    private double sellDollars;

    private double buyEuoros;

    private double sellEuoros;

    public SetExchangeBar(double buyRubles, double sellRubles, double buyDollars, double sellDollars, double buyEuoros, double sellEuoros) {
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

    public void setBuyRubles(double buyRubles) {
        this.buyRubles = buyRubles;
    }

    public double getSellRubles() {
        return sellRubles;
    }

    public void setSellRubles(double sellRubles) {
        this.sellRubles = sellRubles;
    }

    public double getBuyDollars() {
        return buyDollars;
    }

    public void setBuyDollars(double buyDollars) {
        this.buyDollars = buyDollars;
    }

    public double getSellDollars() {
        return sellDollars;
    }

    public void setSellDollars(double sellDollars) {
        this.sellDollars = sellDollars;
    }

    public double getBuyEuoros() {
        return buyEuoros;
    }

    public void setBuyEuoros(double buyEuoros) {
        this.buyEuoros = buyEuoros;
    }

    public double getSellEuoros() {
        return sellEuoros;
    }

    public void setSellEuoros(double sellEuoros) {
        this.sellEuoros = sellEuoros;
    }
}
