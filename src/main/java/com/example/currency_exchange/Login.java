package com.example.currency_exchange;

import com.example.currency_exchange.models.Client;
import com.example.currency_exchange.models.Manager;
import com.example.currency_exchange.models.dto.AccountDto;
import com.example.currency_exchange.models.dto.ClientDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login {

    @FXML
    private Label log_Label;

    @FXML
    private TextField log;

    @FXML
    private PasswordField pass;

    @FXML
    private AnchorPane LoginView;

    @FXML
    private Label pass_Label;

    @FXML
    private Button LogIn;

    @FXML
    void LoggingMan(ActionEvent event) {
        AccountDto accountDto= new AccountDto(log.getText(), pass.getText());
        if(!DateHandler.isManager){
        ClientDto clientDto;
        try(Connection connection=JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT LoginDate, Perpassword FROM members WHERE LoginDate='"+accountDto.getLogin()+"' AND Perpassword='"+accountDto.getPassword()+"'");
            if(resultSet.next()==false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Incorrect data");
                alert.setHeaderText("This account is not exist");
                alert.setContentText("Change login/password");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                    }
                });
            }
            else {
            resultSet=statement.executeQuery("SELECT LoginDate, Perpassword, USD, RUB, EU, RUB_rem, USD_rem,EU_rem,DateLogOn FROM members WHERE LoginDate='"+accountDto.getLogin()+"'");
            resultSet.next();
            clientDto=new ClientDto(resultSet.getString("Perpassword"), resultSet.getString("LoginDate"),resultSet.getDouble("RUB"),resultSet.getDouble("USD"),resultSet.getDouble("EU"),resultSet.getDouble("RUB_rem"),resultSet.getDouble("USD_rem"),resultSet.getDouble("EU_rem"),resultSet.getString("DateLogOn"));
            DateHandler.client=new Client(clientDto.getPassword(),clientDto.getLogin(),clientDto.getRubles(),clientDto.getDollars(),clientDto.getEuros(), clientDto.getRubles_rem(), clientDto.getDollars_rem(),clientDto.getEuros_rem(),clientDto.getDateLogOn());
            connection.close();
            Stage stage= (Stage) LogIn.getScene().getWindow();
            stage.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        }
        else {
            Manager manager=new Manager();
            //System.out.println(manager.getLogin()+" "+manager.getPassword()+" "+accountDto.getLogin()+" "+accountDto.getPassword());
            if(manager.getLogin().equalsIgnoreCase(accountDto.getLogin()) && manager.getPassword().equalsIgnoreCase(accountDto.getPassword())){
            DateHandler.manager = new Manager();
                Stage stage=(Stage) LogIn.getScene().getWindow();
                stage.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Incorrect data");
                alert.setHeaderText("This account is not exist");
                alert.setContentText("Change login/password");
                alert.showAndWait().ifPresent(rs -> {
                    if (rs == ButtonType.OK) {
                    }
                });
            }
        }
    }

}
