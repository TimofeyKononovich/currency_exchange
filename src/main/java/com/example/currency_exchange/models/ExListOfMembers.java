package com.example.currency_exchange.models;

public class ExListOfMembers {
    private String date;
    private String vIn;
    private String vOut;
    private String login;
    private double curFrom;
    private double curIn;

    public ExListOfMembers(String date, String vIn, String vOut, String login, double curFrom, double curIn) {
        this.date = date;
        this.vIn = vIn;
        this.vOut = vOut;
        this.login = login;
        this.curFrom = curFrom;
        this.curIn = curIn;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getvIn() {
        return vIn;
    }

    public void setvIn(String vIn) {
        this.vIn = vIn;
    }

    public String getvOut() {
        return vOut;
    }

    public void setvOut(String vOut) {
        this.vOut = vOut;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public double getCurFrom() {
        return curFrom;
    }

    public void setCurFrom(double curFrom) {
        this.curFrom = curFrom;
    }

    public double getCurIn() {
        return curIn;
    }

    public void setCurIn(double curIn) {
        this.curIn = curIn;
    }
}
