package com.example.currency_exchange;

import com.example.currency_exchange.models.SetExchangeBar;
import javafx.beans.binding.When;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
        ValidationInput(event);
    }

    @FXML
    void RublesIn(ActionEvent event) {
        Value_In.setText(RUB_In.getText());
        ValidationInput(event);
    }

    @FXML
    void EourosIn(ActionEvent event) {
        Value_In.setText(EU_In.getText());
        ValidationInput(event);

    }

    @FXML
    void DollarsOut(ActionEvent event) {
        Value_Out.setText(USD_Out.getText());
        ValidationInput(event);

    }

    @FXML
    void RubblesOut(ActionEvent event) {
        Value_Out.setText(RUB_Out.getText());
        ValidationInput(event);
    }

    @FXML
    void EourosOut(ActionEvent event) {
        Value_Out.setText(EU_Out.getText());
        ValidationInput(event);
    }

    @FXML
    void Exchanging(ActionEvent event) {
        if(InterValue.getText()!="" && OuterValue.getText()!=""){
        ValidationInput(event);
        String textOut = Value_Out.getText();
        String textIn = Value_In.getText();
        switch (textIn) {
            case "RUB":
                DateHandler.client.setRubles_rem(DateHandler.client.getRubles_rem()-Double.valueOf(InterValue.getText()));
                DateHandler.client.setRubles(DateHandler.client.getRubles()-Double.valueOf(InterValue.getText()));
                break;
            case "USD":
                DateHandler.client.setDollars_rem(DateHandler.client.getDollars_rem()-Double.valueOf(InterValue.getText()));
                DateHandler.client.setDollars(DateHandler.client.getDollars()-Double.valueOf(InterValue.getText()));
                break;
            case "EU":
                DateHandler.client.setEuros_rem(DateHandler.client.getEuros_rem()-Double.valueOf(InterValue.getText()));
                DateHandler.client.setEuros(DateHandler.client.getEuros()-Double.valueOf(InterValue.getText()));
                break;
            default:{
                Stage stage =(Stage) Ex_But.getScene().getWindow();
                stage.close();
            }
        }
        switch (textOut) {
            case "RUB":
                DateHandler.client.setRubles(DateHandler.client.getRubles()+Double.valueOf(OuterValue.getText()));
                break;
            case "USD":
                DateHandler.client.setDollars(DateHandler.client.getDollars()+Double.valueOf(OuterValue.getText()));
                break;
            case "EU":
                DateHandler.client.setEuros(DateHandler.client.getEuros()+Double.valueOf(OuterValue.getText()));
                break;
            default:{
                Stage stage =(Stage) Ex_But.getScene().getWindow();
                stage.close();
            }
        }
        try(Connection connection=JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            statement.executeUpdate("UPDATE members SET RUB_rem="+DateHandler.client.getRubles_rem()+", USD_rem="+DateHandler.client.getDollars_rem()+", EU_rem="+DateHandler.client.getDollars_rem()+", RUB="+DateHandler.client.getRubles()+",USD="+DateHandler.client.getDollars()+",EU="+DateHandler.client.getEuros()+" WHERE LoginDate='"+DateHandler.client.getLogin()+"'");
            statement.executeUpdate("INSERT INTO exhistory (LoginDate, ValueIn, CurIn, CurOut, ValueOut, DayEx) VALUES ('"+DateHandler.client.getLogin()+"', "+Double.valueOf(InterValue.getText())+", '"+textIn+"', '"+textOut+"', "+Double.valueOf(OuterValue.getText())+", '"+LocalDate.now().toString()+"')");
            ResultSet resultSet=statement.executeQuery("SELECT id FROM exhistory ORDER BY id DESC LIMIT 1");
            resultSet.next();
            PrintIntoFile(textIn,textOut,LocalDate.now(),resultSet.getInt("id"),InterValue.getText(), OuterValue.getText());
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        }
    }

    private void PrintIntoFile(String textIn, String textOut, LocalDate localDate, Integer id, String in, String out) {
        String pathName="C:\\Users\\User\\Desktop\\tp\\"+DateHandler.client.getLogin();
        String fileName= pathName+"\\"+textIn+" into "+textOut+" "+localDate.toString()+" "+id.toString()+".txt";
        try {
            if(!Files.isDirectory(Path.of(pathName))){
            Files.createDirectory(Path.of(pathName));
            }
            Files.createFile(Path.of(fileName));
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            Double usd_above_zero;
            Double rub_above_zero;
            Double eu_above_zero;
            if(DateHandler.client.getDollars_rem()<0){
            usd_above_zero=usd_above_zero=DateHandler.client.getDollars_rem();
            }else {
                usd_above_zero=DateHandler.client.getDollars_rem();
            }
            if(DateHandler.client.getRubles_rem()<0){
                DateHandler.client.getRubles_rem();
            }else {
                rub_above_zero=DateHandler.client.getRubles_rem();
            }if(DateHandler.client.getEuros_rem()<0){
                eu_above_zero=DateHandler.client.getEuros_rem();
            }else {
                eu_above_zero=DateHandler.client.getEuros_rem();
            }
            String cheque = "Имя клиента: "+DateHandler.client.getLogin()+".       Время: "+localDate.toString()+"."+"\n"+
                    "---------------------------------------------------------------------------------------------------"+"\n"+
                    "Валюта, купленная банком: "+textIn+" "+String.format("%.2f",Double.valueOf(in))+"     "+"Валюта, проданная банком: "+textOut+" "+String.format("%.2f",Double.valueOf(out))+"\n"+
                    "Остаток на счете: USD "+String.format("%.2f",DateHandler.client.getDollars())+"    RUB "+String.format("%.2f",DateHandler.client.getRubles())+"   EU "+String.format("%.2f",DateHandler.client.getEuros())+"\n"+
                    "Остаток ежедневных обменов: USD "+String.format("%.2f",usd_above_zero)+"    RUB "+String.format("%.2f",rub_above_zero)+"     EU "+String.format("%.2f",eu_above_zero)+"\n"+
                    "Спасибо, что выбрали услуги нашего пункта обмена валюты!";

            fileOutputStream.write(cheque.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void ValidationInput(ActionEvent event) {
        try {
            String textIn = Value_In.getText();
            double textFieldMoney=Double.valueOf(InterValue.getText());
            double balance;
            double currency;
            double money;
            switch (textIn) {
                case "RUB":
                    money = DateHandler.client.getRubles();
                    currency = DateHandler.setExchangeBar.getSellRubles();
                    balance=DateHandler.client.getRubles_rem();
                    break;
                case "USD":
                    money = DateHandler.client.getDollars();
                    currency = DateHandler.setExchangeBar.getSellDollars();
                    balance=DateHandler.client.getDollars_rem();
                    break;
                case "EU":
                    money = DateHandler.client.getEuros();
                    currency = DateHandler.setExchangeBar.getSellEuoros();
                    balance=DateHandler.client.getEuros_rem();
                    break;
                default:
                    InterValue.setText("");
                    throw new NumberFormatException();
            }
            if (textFieldMoney > 0 && money >= textFieldMoney && balance>=textFieldMoney) {
                String textOut = Value_Out.getText();
                if (Value_Out.getText().equalsIgnoreCase(Value_In.getText())) {
                    OuterValue.setText(String.valueOf(InterValue.getText()));
                } else {
                    switch (textOut) {
                        case "RUB":
                            OuterValue.setText(Double.toString(Double.valueOf(InterValue.getText()) * currency / DateHandler.setExchangeBar.getBuyRubles()));
                            break;
                        case "USD":
                            money = DateHandler.client.getDollars();
                            OuterValue.setText(Double.toString(Double.valueOf(InterValue.getText()) * currency / DateHandler.setExchangeBar.getBuyDollars()));
                            break;
                        case "EU":
                            money = DateHandler.client.getEuros();
                            OuterValue.setText(Double.toString(Double.valueOf(InterValue.getText()) * currency / DateHandler.setExchangeBar.getBuyEuoros()));
                            break;
                        default:
                            OuterValue.setText("");
                            throw new NumberFormatException();
                    }
                }
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            InterValue.setText("");
            OuterValue.setText("");
        }

    }


}
