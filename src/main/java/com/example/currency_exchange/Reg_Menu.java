package com.example.currency_exchange;

import com.example.currency_exchange.models.Client;
import com.example.currency_exchange.models.dto.AccountDto;
import com.example.currency_exchange.models.dto.ClientDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;

public class Reg_Menu {

    @FXML
    private AnchorPane Reg_Menu;

    @FXML
    private PasswordField pass;

    @FXML
    private Button Reg_But;

    @FXML
    private Label Log_Label;

    @FXML
    private Label Log_Pas;

    @FXML
    private TextField Name;

    @FXML
    void Registrate(ActionEvent event) {
        AccountDto accountDto=new AccountDto(Name.getText(),pass.getText());
        try(Connection connection=JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT LoginDate, Perpassword FROM members WHERE LoginDate='"+accountDto.getLogin()+"'");
            if(resultSet.next()==true){
                System.out.println("Client is already registered");
            }
            else {
                Random random=new Random();
                double maxValue=10000;
                double rub=random.nextDouble()*maxValue+1;
                double usd=random.nextDouble()*maxValue+1;
                double eu=random.nextDouble()*maxValue+1;
                statement.executeUpdate("INSERT INTO members (LoginDate, Perpassword, USD, RUB, EU, RUB_rem, USD_rem, EU_rem, DateLogOn) VALUES ('"+accountDto.getLogin()+"', '"+accountDto.getPassword()+"', "+usd+", "+rub+", "+eu+","+1000+","+1000+","+1000+",'"+LocalDate.now().toString()+"')");
                DateHandler.client =new Client(accountDto.getPassword(),accountDto.getLogin(),rub,usd,eu,1000,1000,1000, LocalDate.now().toString());
                connection.close();
                Stage stage=(Stage) Reg_But.getScene().getWindow();
                stage.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
