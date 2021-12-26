package com.example.currency_exchange;

import com.example.currency_exchange.models.RemainValue;
import com.example.currency_exchange.models.SetExchangeBar;
import com.example.currency_exchange.models.dto.ExchangeValueDto;
import com.example.currency_exchange.models.dto.RemainDto;
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
        try{
        if(DateHandler.remainValue!=null){
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
        }else

        {
            Reloading(event);
        }}catch (NumberFormatException e){
            USD_field.setText("");
            RUB_field.setText("");
            EU_field.setText("");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect data");
            alert.setHeaderText("Not number/ Empty Fields/ Less than zero");
            alert.setContentText("Enter right statements");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                }
            });
        }
        }



    @FXML
    void Reloading(ActionEvent event) {
        try(Connection connection=JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  RUB_rem, USD_rem, EU_rem FROM remvalue ORDER BY id DESC LIMIT 1");
            if (resultSet.next() == false) {
                statement.executeUpdate("INSERT INTO remvalue (RUB_rem, USD_rem, EU_rem) VALUES (1000,1000,1000)");
                statement.executeUpdate("UPDATE members SET RUB_rem=1000, USD_rem=1000, EU_rem=1000, DateLogOn='" + LocalDate.now().toString() + "' WHERE NOT DateLogOn='" + LocalDate.now().toString() + "'");
            }
            DateHandler.remainValue = new RemainValue(resultSet.getDouble("RUB_rem"), resultSet.getDouble("USD_rem"), resultSet.getDouble("EU_rem"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        RUB_field.setText(Double.toString(DateHandler.remainValue.getRem_RUB()));
        USD_field.setText(Double.toString(DateHandler.remainValue.getRem_USD()));
        EU_field.setText(Double.toString(DateHandler.remainValue.getRem_EU()));
    }

}
