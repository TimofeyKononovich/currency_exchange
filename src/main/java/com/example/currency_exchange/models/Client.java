package com.example.currency_exchange.models;

public class Client {
    private int id;

    private boolean isManager=false;

    private String password;

    private String login;

    private double rubles;

    private double dollars;

    private double euros;

    private double rubles_rem;

    private double dollars_rem;

    private double euros_rem;

    private String dateLogOn;

    public Client( String password, String login, double rubles, double dollars, double euros, double rubles_rem, double dollars_rem, double euros_rem, String dateLogOn) {
        this.dateLogOn=dateLogOn;
        this.password = password;
        this.login = login;
        this.rubles = rubles;
        this.dollars = dollars;
        this.euros = euros;
        this.rubles_rem = rubles_rem;
        this.dollars_rem = dollars_rem;
        this.euros_rem = euros_rem;
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

    public double getRubles_rem() {
        return rubles_rem;
    }

    public void setRubles_rem(double rubles_rem) {
        this.rubles_rem = rubles_rem;
    }

    public double getDollars_rem() {
        return dollars_rem;
    }

    public void setDollars_rem(double dollars_rem) {
        this.dollars_rem = dollars_rem;
    }

    public double getEuros_rem() {
        return euros_rem;
    }

    public void setEuros_rem(double euros_rem) {
        this.euros_rem = euros_rem;
    }
}
