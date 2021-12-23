package com.example.currency_exchange;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuBoot {

    @FXML
    private MenuItem Exchange;

    @FXML
    private AnchorPane First_Window;

    @FXML
    private Menu As_Manager;

    @FXML
    private MenuItem Log;

    @FXML
    private MenuItem Reg;

    @FXML
    private MenuItem Value_set;

    @FXML
    private MenuItem Man_Ex;

    @FXML
    private MenuBar MenuBar;

    @FXML
    private Label Person;

    @FXML
    private MenuItem Man_Log;

    @FXML
    private MenuItem Exchange_list;

    @FXML
    private MenuItem Exit;

    @FXML
    private Menu Client_int;

    @FXML
    void Log_Manager(ActionEvent event) {
        Stage logManagerStage = new Stage();
        logManagerStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader logManagerFXMLLoader = new FXMLLoader(getClass().getResource("LoginManager.fxml"));
        Scene logManagerScene= null;
        try {
            logManagerScene = new Scene(logManagerFXMLLoader.load(), 406.0,406.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logManagerStage.setScene(logManagerScene);
        logManagerStage.show();
    }

    @FXML
    void OpenList(ActionEvent event) {
        Stage openListStage = new Stage();
        openListStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader openListFXMLLoader = new FXMLLoader(getClass().getResource("ExList.fxml"));
        Scene openListScene= null;
        try {
            openListScene = new Scene(openListFXMLLoader.load(), 880,406.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        openListStage.setScene( openListScene);
        openListStage.show();
    }

    @FXML
    void OpenSetting(ActionEvent event) {
        Stage setExchangeValueStage = new Stage();
        setExchangeValueStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader setExchangeValueFXMLLoader = new FXMLLoader(getClass().getResource("SetExchangeValue.fxml"));
        Scene setExchangeValueScene= null;
        try {
            setExchangeValueScene = new Scene(setExchangeValueFXMLLoader.load(), 500,406.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setExchangeValueStage.setScene(setExchangeValueScene);
        setExchangeValueStage.show();
    }

    @FXML
    void OpenExchanger(ActionEvent event) {
        Stage exchangeBarStage = new Stage();
        exchangeBarStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader exchangeBarValueFXMLLoader = new FXMLLoader(getClass().getResource("exchangeBar.fxml"));
        Scene exchangeBarScene= null;
        try {
            exchangeBarScene = new Scene(exchangeBarValueFXMLLoader.load(), 550.0,406.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        exchangeBarStage.setScene(exchangeBarScene);
        exchangeBarStage.show();
    }

    @FXML
    void Client_log(ActionEvent event) {
        Stage loginClientStage = new Stage();
        loginClientStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loginClientFXMLLoader = new FXMLLoader(getClass().getResource("Reg_Menu.fxml"));
        Scene loginClientScene= null;
        try {
            loginClientScene = new Scene(loginClientFXMLLoader.load(), 406.0,406.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginClientStage.setScene(loginClientScene);
        loginClientStage.show();
    }

    @FXML
    void Registrate(ActionEvent event) {
        Stage regMenuStage = new Stage();
        regMenuStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader regMenuFXMLLoader = new FXMLLoader(getClass().getResource("Reg_Menu.fxml"));
        Scene regMenuScene= null;
        try {
            regMenuScene = new Scene(regMenuFXMLLoader.load(), 406.0,406.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        regMenuStage.setScene(regMenuScene);
        regMenuStage.show();
    }

}