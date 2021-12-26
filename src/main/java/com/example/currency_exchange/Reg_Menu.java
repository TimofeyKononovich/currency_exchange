package com.example.currency_exchange;

import com.example.currency_exchange.models.Client;
import com.example.currency_exchange.models.RemainValue;
import com.example.currency_exchange.models.dto.AccountDto;
import com.example.currency_exchange.models.dto.ClientDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
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


    Double RandomDouble(double maxValue){
        Random random=new Random();
        double rand=random.nextDouble()*maxValue+1;
        BigDecimal result = new BigDecimal(rand);
        result = result.setScale(2, BigDecimal.ROUND_DOWN);
        return result.doubleValue();
    }

    @FXML
    void Registrate(ActionEvent event) {
        AccountDto accountDto=new AccountDto(Name.getText(),pass.getText());
        try(Connection connection=JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT LoginDate FROM members WHERE LoginDate='"+accountDto.getLogin()+"'");
            if(resultSet.next()==true){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Incorrect data");
                alert.setHeaderText("Account is already existed");
                alert.setContentText("Enter new login");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                    }
                });
            }
            else {
                resultSet=statement.executeQuery("SELECT  RUB_rem, USD_rem, EU_rem FROM remvalue ORDER BY id DESC LIMIT 1");
                if(resultSet.next()==false){
                    statement.executeUpdate("INSERT INTO remvalue (RUB_rem, USD_rem, EU_rem) VALUES (1000,1000,1000)");
                resultSet=statement.executeQuery("SELECT  RUB_rem, USD_rem, EU_rem FROM exchangevalue ORDER BY id DESC LIMIT 1");
                resultSet.next();
                }
                DateHandler.remainValue=new RemainValue(resultSet.getDouble("RUB_rem"),resultSet.getDouble("USD_rem"),resultSet.getDouble("EU_rem"));
                double maxValue=10000;
                double rub=RandomDouble(maxValue);
                double usd=RandomDouble(maxValue);
                double eu=RandomDouble(maxValue);
                statement.executeUpdate("INSERT INTO members (LoginDate, Perpassword, USD, RUB, EU, RUB_rem, USD_rem, EU_rem, DateLogOn) VALUES ('"+accountDto.getLogin()+"', '"+accountDto.getPassword()+"', "+usd+", "+rub+", "+eu+","+1000+","+1000+","+1000+",'"+LocalDate.now().toString()+"')");
                DateHandler.client =new Client(accountDto.getPassword(),accountDto.getLogin(),rub,usd,eu,DateHandler.remainValue.getRem_RUB(),DateHandler.remainValue.getRem_USD(),DateHandler.remainValue.getRem_EU(), LocalDate.now().toString());
                connection.close();
                Stage stage=(Stage) Reg_But.getScene().getWindow();
                stage.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
