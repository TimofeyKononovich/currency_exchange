package com.example.currency_exchange.models;

public class Client {
    private int id;

    private boolean isManager=false;

    private String password;

    private String login;

    private double rubles;

    private double dollars;

    private double euros;

    public Client(String password, String login, double rubles, double dollars, double euros) {
        this.password = password;
        this.login = login;
        this.rubles = rubles;
        this.dollars = dollars;
        this.euros = euros;
    }

    public double getRubles() {
        return rubles;
    }

    public void setRubles(double rubles) {
        this.rubles = rubles;
    }

    public double getDollars() {
        return dollars;
    }

    public void setDollars(double dollars) {
        this.dollars = dollars;
    }

    public double getEuros() {
        return euros;
    }

    public void setEuros(double euros) {
        this.euros = euros;
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
}
