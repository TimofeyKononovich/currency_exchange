package com.example.currency_exchange;



import com.example.currency_exchange.models.SetExchangeBar;
import com.example.currency_exchange.models.dto.ExchangeValueDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetExchangeValue {

    @FXML
    private AnchorPane SetterValue;

    @FXML
    private Button ReloadBut;

    @FXML
    public TextField EU_purchase;

    @FXML
    private TextField sale_USD;

    @FXML
    private Label USD;

    @FXML
    private Button Setting;

    @FXML
    private Label purchase;

    @FXML
    private TextField sale_RUB;

    @FXML
    private Label EU;

    @FXML
    private TextField purchase_USD;

    @FXML
    private Label sale;

    @FXML
    private TextField purchase_RUB;

    @FXML
    private TextField sale_EU;

    @FXML
    private Label RUB;

    @FXML
    void Save(ActionEvent event) {
        NewValue(event);
        ExchangeValueDto exchangeValueDto=new ExchangeValueDto(Double.valueOf(purchase_RUB.getText()),Double.valueOf(sale_RUB.getText()),Double.valueOf(purchase_USD.getText()),Double.valueOf(sale_USD.getText()),Double.valueOf(EU_purchase.getText()),Double.valueOf(sale_EU.getText()));
            try(Connection connection=JDBCSource.getConnection()) {
                Statement statement=connection.createStatement();
                statement.executeUpdate("INSERT INTO exchangevalue (buyUSD, buyRUB, buyEU, sellUSD, sellRUB, sellEU) VALUES ("+exchangeValueDto.getBuyDollars()+", "+exchangeValueDto.getBuyRubles()+", "+exchangeValueDto.getBuyEuoros()+", "+exchangeValueDto.getSellDollars()+", "+exchangeValueDto.getSellRubles()+", "+exchangeValueDto.getSellEuoros()+")");
                connection.close();
                DateHandler.setExchangeBar=null;
                DateHandler.setExchangeBar=new SetExchangeBar(exchangeValueDto.getBuyRubles(),exchangeValueDto.getSellRubles(),exchangeValueDto.getBuyDollars(),exchangeValueDto.getSellDollars(),exchangeValueDto.getBuyEuoros(),exchangeValueDto.getSellEuoros());
                Stage stage =(Stage) Setting.getScene().getWindow();
                stage.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    @FXML
    void NewValue(ActionEvent event) {
            EU_purchase.setText(Double.toString(DateHandler.setExchangeBar.getBuyEuoros()));
            sale_EU.setText(Double.toString(DateHandler.setExchangeBar.getSellEuoros()));
            purchase_USD.setText(Double.toString(DateHandler.setExchangeBar.getBuyDollars()));
            sale_USD.setText(Double.toString(DateHandler.setExchangeBar.getSellDollars()));
            purchase_RUB.setText(Double.toString(DateHandler.setExchangeBar.getBuyRubles()));
            sale_RUB.setText(Double.toString(DateHandler.setExchangeBar.getSellRubles()));
        }
    }


