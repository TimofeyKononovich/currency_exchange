package com.example.currency_exchange;

import com.example.currency_exchange.models.RemainValue;
import com.example.currency_exchange.models.SetExchangeBar;
import com.example.currency_exchange.models.dto.ExchangeValueDto;
import com.example.currency_exchange.models.dto.RemainDto;
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

public class Rem {

    @FXML
    private Label RUB_label;

    @FXML
    private AnchorPane Change_Window;

    @FXML
    private Label USD_label;

    @FXML
    private Button Submit_But;

    @FXML
    private TextField EU_field;

    @FXML
    private Button Reload_But;

    @FXML
    private Label EU_label;

    @FXML
    private TextField USD_field;

    @FXML
    private TextField RUB_field;

    @FXML
    void Submition(ActionEvent event) {
        Reloading(event);
            try(Connection connection=JDBCSource.getConnection()) {
                Statement statement=connection.createStatement();
                double USD=Double.valueOf(USD_field.getText())-DateHandler.remainValue.getRem_USD();
                double RUB=Double.valueOf(RUB_field.getText())-DateHandler.remainValue.getRem_RUB();
                double EU=Double.valueOf(EU_field.getText())-DateHandler.remainValue.getRem_EU();
                statement.executeUpdate("INSERT INTO remvalue (RUB_rem, USD_rem, EU_rem) VALUES ("+Double.valueOf(RUB_field.getText())+", "+Double.valueOf(USD_field.getText())+", "+Double.valueOf(EU_field.getText())+")");
                statement.executeUpdate("UPDATE members SET RUB_rem=RUB_rem+"+RUB+", USD_rem=USD_rem+"+USD+", EU_rem=EU_rem+"+EU);
                connection.close();
                RemainDto remainDto=new RemainDto(Double.valueOf(RUB_field.getText()),Double.valueOf(USD_field.getText()),Double.valueOf(EU_field.getText()));
                DateHandler.remainValue=null;
                DateHandler.remainValue=new RemainValue(remainDto.getRem_RUB(),remainDto.getRem_USD(),remainDto.getRem_EU());
                Stage stage =(Stage) Submit_But.getScene().getWindow();
                stage.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }



    @FXML
    void Reloading(ActionEvent event) {
        RUB_field.setText(Double.toString(DateHandler.remainValue.getRem_RUB()));
        USD_field.setText(Double.toString(DateHandler.remainValue.getRem_USD()));
        EU_field.setText(Double.toString(DateHandler.remainValue.getRem_EU()));
    }

}
