package com.example.currency_exchange;

import com.example.currency_exchange.models.dto.AccountDto;
import com.example.currency_exchange.models.dto.ClientDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



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

        ClientDto clientDto;
      //  DateHandler.client=new ();
    }

}
