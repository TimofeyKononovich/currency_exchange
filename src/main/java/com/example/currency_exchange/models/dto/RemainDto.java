package com.example.currency_exchange.models.dto;

public class RemainDto {
    double rem_RUB;
    double rem_USD;
    double rem_EU;

    public RemainDto(double rem_RUB, double rem_USD, double rem_EU) {
        this.rem_RUB = rem_RUB;
        this.rem_USD = rem_USD;
        this.rem_EU = rem_EU;
    }

    public double getRem_RUB() {
        return rem_RUB;
    }

    public void setRem_RUB(double rem_RUB) {
        this.rem_RUB = rem_RUB;
    }

    public double getRem_USD() {
        return rem_USD;
    }

    public void setRem_USD(double rem_USD) {
        this.rem_USD = rem_USD;
    }

    public double getRem_EU() {
        return rem_EU;
    }

    public void setRem_EU(double rem_EU) {
        this.rem_EU = rem_EU;
    }
}
