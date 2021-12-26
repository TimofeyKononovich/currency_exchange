package com.example.currency_exchange.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

public class ExListOfMembers {
    private StringProperty date;
    private StringProperty vIn;
    private StringProperty vOut;
    private StringProperty login;
    private StringProperty curFrom;
    private StringProperty curIn;

    public ExListOfMembers(String date, String vIn, String vOut, String login, String curFrom, String curIn) {
        this.date = new SimpleStringProperty(date);
        this.vIn = new SimpleStringProperty(vIn);
        this.vOut = new SimpleStringProperty(vOut);
        this.login = new SimpleStringProperty(login);
        this.curFrom = new SimpleStringProperty(curFrom);
        this.curIn = new SimpleStringProperty(curIn);
    }

    public String getDate() {
        return this.date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public StringProperty dateProperty(){
        return this.date;
    }

    public String getvIn() {
        return this.vIn.get();
    }

    public void setvIn(String vIn) {
        this.vIn.set(vIn);
    }


    public StringProperty vInProperty(){
        return this.vIn;
    }

    public String getvOut() {
        return this.vOut.get();
    }

    public void setvOut(String vOut) {
        this.vOut.set(vOut);
    }


    public StringProperty vOutProperty(){
        return this.vOut;
    }

    public String getLogin() {
        return this.login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public StringProperty loginProperty(){
        return this.login;
    }

    public String getCurFrom() {
        return this.curFrom.get();
    }

    public void setCurFrom(String curFrom) {
        this.curFrom.set(curFrom);
    }


    public StringProperty curFromProperty(){
        return this.curFrom;
    }

    public String getCurIn() {
        return this.curIn.get();
    }

    public void setCurIn(String curIn) {
        this.curIn.set(curIn);
    }

    public StringProperty curInProperty(){
        return this.curIn;
    }
}
