package com.example.currency_exchange;


import com.example.currency_exchange.models.Manager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class MenuBoot {

    @FXML
    private Label RUB_rem;

    @FXML
    private Label EU_rem;

    @FXML
    private Label USD_rem;

    @FXML
    private Label Date;

    @FXML
    private MenuItem Reload_But;

    @FXML
    private Menu Reload_Menu;

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
    private Label USD;

    @FXML
    private Label RUB;

    @FXML
    private Label EU;

    @FXML
    private MenuBar MenuBar;

    @FXML
    private Label Person;

    @FXML
    private MenuItem Man_Log;

    @FXML
    private ImageView icon;

    @FXML
    private MenuItem Exchange_list;

    @FXML
    private MenuItem Exit;

    @FXML
    private MenuItem ChangeRem;

    @FXML
    private Menu Client_int;

    @FXML
    void Change(ActionEvent event) {
        view();
        if(DateHandler.manager !=null){
            Person.setText(DateHandler.manager.getStatus()+" "+DateHandler.manager.getLogin());
            Stage RemStage = new Stage();
            RemStage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader RemFXMLLoader = new FXMLLoader(getClass().getResource("Rem.fxml"));
            Scene RemScene= null;
            try {
                RemScene = new Scene(RemFXMLLoader.load(), 600,406);
            } catch (IOException e) {
                e.printStackTrace();
            }
            RemStage.setTitle("Change packet");
            RemStage.setScene( RemScene);
            RemStage.show();
        }
    }

    @FXML
    void Log_Manager(ActionEvent event) {
        view();
        if(DateHandler.client==null && DateHandler.manager==null){
            DateHandler.isManager=true;
        Stage logManagerStage = new Stage();
        logManagerStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader logManagerFXMLLoader = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
        Scene logManagerScene= null;
        try {
            logManagerScene = new Scene(logManagerFXMLLoader.load(), 500.0,406.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logManagerStage.setTitle("Manager logging");
        logManagerStage.setScene(logManagerScene);
        logManagerStage.show();
        }
    }

    @FXML
    void OpenList(ActionEvent event) {
        view();
        if(DateHandler.manager !=null){
            Person.setText(DateHandler.manager.getStatus()+" "+DateHandler.manager.getLogin());
        Stage openListStage = new Stage();
        openListStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader openListFXMLLoader = new FXMLLoader(getClass().getResource("ExList.fxml"));
        Scene openListScene= null;
        try {
            openListScene = new Scene(openListFXMLLoader.load(), 1000,700);
        } catch (IOException e) {
            e.printStackTrace();
        }
        openListStage.setTitle("journal");
        openListStage.setScene( openListScene);
        openListStage.show();
    }
    }

    @FXML
    void OpenSetting(ActionEvent event) {
        view();
        if(DateHandler.manager !=null){
            Person.setText(DateHandler.manager.getStatus()+" "+DateHandler.manager.getLogin());
        Stage setExchangeValueStage = new Stage();
        setExchangeValueStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader setExchangeValueFXMLLoader = new FXMLLoader(getClass().getResource("SetExchangeValue.fxml"));
        Scene setExchangeValueScene= null;
        try {
            setExchangeValueScene = new Scene(setExchangeValueFXMLLoader.load(), 500,500.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setExchangeValueStage.setTitle("Exchange configuration");
        setExchangeValueStage.setScene(setExchangeValueScene);
        setExchangeValueStage.show();
    }
    }

    @FXML
    void OpenExchanger(ActionEvent event) {
        view();
        if(DateHandler.client !=null ){
            USD.setText("USD: "+String.format("%.2f",DateHandler.client.getDollars()));
            RUB.setText("RUB: "+String.format("%.2f",DateHandler.client.getRubles()));
            EU.setText("EU: "+String.format("%.2f",DateHandler.client.getEuros()));
            Person.setText(DateHandler.client.getLogin());
        Stage exchangeBarStage = new Stage();
        exchangeBarStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader exchangeBarValueFXMLLoader = new FXMLLoader(getClass().getResource("exchangeBar.fxml"));
        Scene exchangeBarScene= null;
        try {
            exchangeBarScene = new Scene(exchangeBarValueFXMLLoader.load(), 550.0,406.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        exchangeBarStage.setTitle("Where is my money!?");
        exchangeBarStage.setScene(exchangeBarScene);
        exchangeBarStage.show();

    }
    }

    @FXML
    void Client_log(ActionEvent event) {
        view();
        if(DateHandler.client ==null && DateHandler.manager ==null){
            DateHandler.isManager=false;
        Stage loginClientStage = new Stage();
        loginClientStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loginClientFXMLLoader = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
        Scene loginClientScene= null;
        try {
            loginClientScene = new Scene(loginClientFXMLLoader.load(), 500.0,406.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loginClientStage.setTitle("Client logging");
        loginClientStage.setScene(loginClientScene);
        loginClientStage.show();
    }
    }

    @FXML
    void Registrate(ActionEvent event) {
        view();
        if(DateHandler.client ==null && DateHandler.manager ==null){
        Stage regMenuStage = new Stage();
        regMenuStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader regMenuFXMLLoader = new FXMLLoader(getClass().getResource("Reg_Menu.fxml"));
        Scene regMenuScene= null;
        try {
            regMenuScene = new Scene(regMenuFXMLLoader.load(), 406.0,406.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        regMenuStage.setTitle("Registration for client's");
        regMenuStage.setScene(regMenuScene);
        regMenuStage.show();
        }
    }

    @FXML
    void ExitManager(ActionEvent event) {
        view();
        if(DateHandler.manager!=null || DateHandler.client==null){
            DateHandler.manager=null;
            Person.setText("");
        }
    }

    @FXML
    void ExitClient(ActionEvent event) {
        view();
        if(DateHandler.manager==null || DateHandler.client!=null){
            DateHandler.client=null;
            EU.setText("");
            USD.setText("");
            RUB.setText("");
            EU_rem.setText("");
            USD_rem.setText("");
            RUB_rem.setText("");
            Person.setText("");
        }
    }
    @FXML
    void Reload(ActionEvent event) {
        view();
        if(DateHandler.manager !=null){
            Person.setText(DateHandler.manager.getStatus()+" "+DateHandler.manager.getLogin());
        }
        if(DateHandler.client !=null){
            Person.setText(DateHandler.client.getLogin());
                USD.setText("USD: "+String.format("%.2f",DateHandler.client.getDollars()));
                RUB.setText("RUB: "+String.format("%.2f",DateHandler.client.getRubles()));
                EU.setText("EU: "+String.format("%.2f",DateHandler.client.getEuros()));
                USD_rem.setText("USD remains: "+String.format("%.2f",DateHandler.client.getDollars_rem()));
                RUB_rem.setText("RUB remains: "+String.format("%.2f",DateHandler.client.getRubles_rem()));
                EU_rem.setText("EU remains: "+String.format("%.2f",DateHandler.client.getEuros_rem()));
        }
    }

    void view(){
        Image image= null;
        try {
            image = new Image(new FileInputStream("D:\\technologic of programming\\currency_exchange\\src\\main\\resources\\com\\example\\currency_exchange\\photo\\cur.jpeg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        icon.setImage(image);

        Date.setText("To day is: "+ LocalDate.now());
    }
}