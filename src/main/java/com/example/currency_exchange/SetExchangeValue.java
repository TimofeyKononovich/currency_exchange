package com.example.currency_exchange;



import com.example.currency_exchange.models.RemainValue;
import com.example.currency_exchange.models.SetExchangeBar;
import com.example.currency_exchange.models.dto.ExchangeValueDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
        try{
        if(DateHandler.setExchangeBar!=null){
            if(Double.valueOf(purchase_RUB.getText())<=0 || Double.valueOf(sale_RUB.getText())<=0 || Double.valueOf(purchase_USD.getText())<=0 || Double.valueOf(sale_USD.getText())<=0 || Double.valueOf(EU_purchase.getText())<=0 || Double.valueOf(sale_EU.getText())<=0){
                throw new NumberFormatException();
            }
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
        }else{
            NewValue(event);
        }
        }catch (NumberFormatException e){
            EU_purchase.setText("");
            purchase_USD.setText("");
            purchase_RUB.setText("");
            sale_EU.setText("");
            sale_USD.setText("");
            sale_RUB.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect data");
            alert.setHeaderText("Not number/ Empty Field/Zero/ Less than zero");
            alert.setContentText("Enter right statements");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });
        }
    }

    @FXML
    void NewValue(ActionEvent event) {
        try(Connection connection=JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT buyUSD, buyRUB, buyEU, sellUSD, sellRUB, sellEU  FROM exchangevalue ORDER BY id DESC LIMIT 1");
            if (resultSet.next() == false) {
                statement.executeUpdate("INSERT INTO exchangevalue (buyUSD, buyRUB, buyEU, sellUSD, sellRUB, sellEU) VALUES (2.6,0.05,3,2.1,0.03,2.8)");
            }
            DateHandler.setExchangeBar=new SetExchangeBar(resultSet.getDouble("buyRUB"),resultSet.getDouble("sellRUB"),resultSet.getDouble("buyUSD"),resultSet.getDouble("sellUSD"),resultSet.getDouble("buyEU"),resultSet.getDouble("sellEU"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            EU_purchase.setText(Double.toString(DateHandler.setExchangeBar.getBuyEuoros()));
            sale_EU.setText(Double.toString(DateHandler.setExchangeBar.getSellEuoros()));
            purchase_USD.setText(Double.toString(DateHandler.setExchangeBar.getBuyDollars()));
            sale_USD.setText(Double.toString(DateHandler.setExchangeBar.getSellDollars()));
            purchase_RUB.setText(Double.toString(DateHandler.setExchangeBar.getBuyRubles()));
            sale_RUB.setText(Double.toString(DateHandler.setExchangeBar.getSellRubles()));
        }
    }


