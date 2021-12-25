package com.example.currency_exchange;

import com.example.currency_exchange.models.SetExchangeBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExchangeBar {

    @FXML
    private MenuItem EU_In;

    @FXML
    private Label Label_In;

    @FXML
    private MenuItem USD_In;

    @FXML
    private MenuItem EU_Out;

    @FXML
    private MenuItem USD_Out;

    @FXML
    private Label Label_ex;

    @FXML
    private MenuItem RUB_In;

    @FXML
    private Button Ex_But;

    @FXML
    private MenuButton Value_Out;

    @FXML
    private MenuButton Value_In;


    @FXML
    private TextField OuterValue;

    @FXML
    private AnchorPane ExchangeMenu;

    @FXML
    private TextField InterValue;

    @FXML
    private Label Label_out;

    @FXML
    private MenuItem RUB_Out;

    @FXML
    void DollarsIn(ActionEvent event) {
        Value_In.setText(USD_In.getText());
        InterValue.setText("");
    }

    @FXML
    void RublesIn(ActionEvent event) {
        Value_In.setText(RUB_In.getText());
        InterValue.setText("");
    }

    @FXML
    void EourosIn(ActionEvent event) {
        Value_In.setText(EU_In.getText());
        InterValue.setText("");
    }

    @FXML
    void DollarsOut(ActionEvent event) {
        Value_Out.setText(USD_Out.getText());
        OuterValue.setText("");
    }

    @FXML
    void RubblesOut(ActionEvent event) {
        Value_Out.setText(RUB_Out.getText());
        OuterValue.setText("");
    }

    @FXML
    void EourosOut(ActionEvent event) {
        Value_Out.setText(EU_Out.getText());
        OuterValue.setText("");
    }

    @FXML
    void Exchanging(ActionEvent event) {
        try (Connection connection=JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT  buyUSD, buyRUB, buyEU, sellUSD, sellRUB, sellEU FROM exchangevalue ORDER BY id DESC LIMIT 1");
            resultSet.next();
            System.out.println(resultSet.getDouble("buyRUB")+" "+resultSet.getDouble("sellRUB")+" "+resultSet.getDouble("buyUSD")+" "+resultSet.getDouble("sellUSD")+" "+resultSet.getDouble("buyEU")+" "+resultSet.getDouble("sellEU"));
            DateHandler.setExchangeBar=new SetExchangeBar(resultSet.getDouble("buyRUB"),resultSet.getDouble("sellRUB"),resultSet.getDouble("buyUSD"),resultSet.getDouble("sellUSD"),resultSet.getDouble("buyEU"),resultSet.getDouble("sellEU"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        switch (Value_In.getText()){
            case "RUB":

                break;
            case "USD":

                break;
            case "EU":

                break;
            default:
                break;
        }
        switch (Value_Out.getText()){
            case "RUB":
                DateHandler.client.setRubles(DateHandler.client.getRubles()-Double.valueOf(InterValue.getText()));
                break;
            case "USD":

                break;
            case "EU":

                break;
            default:
                break;
        }

    }


    @FXML
    void ValidationInput(ActionEvent event) {
        if(DateHandler.setExchangeBar==null){
        try (Connection connection=JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT  buyUSD, buyRUB, buyEU, sellUSD, sellRUB, sellEU FROM exchangevalue ORDER BY id DESC LIMIT 1");
            resultSet.next();
            System.out.println(resultSet.getDouble("buyRUB")+" "+resultSet.getDouble("sellRUB")+" "+resultSet.getDouble("buyUSD")+" "+resultSet.getDouble("sellUSD")+" "+resultSet.getDouble("buyEU")+" "+resultSet.getDouble("sellEU"));
            DateHandler.setExchangeBar=new SetExchangeBar(resultSet.getDouble("buyRUB"),resultSet.getDouble("sellRUB"),resultSet.getDouble("buyUSD"),resultSet.getDouble("sellUSD"),resultSet.getDouble("buyEU"),resultSet.getDouble("sellEU"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        }
        String textIn=Value_In.getText();
        double currency;
        double money;
        switch (textIn){
            case "RUB":
                money=DateHandler.client.getRubles();
                currency=DateHandler.setExchangeBar.getSellRubles();
                break;
            case "USD":
                money=DateHandler.client.getDollars();
                currency=DateHandler.setExchangeBar.getSellDollars();
                break;
            case "EU":
                money=DateHandler.client.getEuros();
                currency=DateHandler.setExchangeBar.getSellEuoros();
                break;
            default:
                InterValue.setText("Choose currency");
                currency=0;
                money=0;
                break;
        }
        System.out.println(money+" "+textIn);
        if(Double.valueOf(InterValue.getText())>0 && money>Double.valueOf(InterValue.getText())){
            String textOut=Value_Out.getText();
            if(Value_Out.getText().equalsIgnoreCase(Value_In.getText())){
                System.out.println("Work");
                OuterValue.setText(String.valueOf(InterValue.getText()));
            }else{
            switch (textOut){
                case "RUB":
                    OuterValue.setText(Double.toString(Double.valueOf(InterValue.getText())*currency/DateHandler.setExchangeBar.getBuyRubles()));
                    break;
                case "USD":
                    money=DateHandler.client.getDollars();
                    OuterValue.setText(Double.toString(Double.valueOf(InterValue.getText())*currency/DateHandler.setExchangeBar.getBuyDollars()));
                    break;
                case "EU":
                    money=DateHandler.client.getEuros();
                    OuterValue.setText(Double.toString(Double.valueOf(InterValue.getText())*currency/DateHandler.setExchangeBar.getBuyEuoros()));
                    break;
                default:
                    OuterValue.setText("Choose currency");
                    break;
            }
            }
        }else{
            OuterValue.setText("Incorrect Number");
        }
    }


}
