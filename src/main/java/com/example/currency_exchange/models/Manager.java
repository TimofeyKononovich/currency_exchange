package com.example.currency_exchange.models;

public class Manager {

    private static String Login="1";

    private static String Status="Manager";

    private static String Password="1";

    private boolean isManger = true;

    public Manager() {
    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }

    public String getStatus() {
        return Status;
    }
}
