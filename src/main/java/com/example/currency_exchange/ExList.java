package com.example.currency_exchange;

import com.example.currency_exchange.models.ExListOfMembers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExList {

    private ObservableList<ExListOfMembers> usersData = FXCollections.observableArrayList();


    @FXML
    private ScrollPane Scroll;

    @FXML
    private TableColumn<ExListOfMembers, String> Col_Date;

    @FXML
    private Label Date_Label;

    @FXML
    private TableColumn<ExListOfMembers, String> Col_Name;

    @FXML
    private TableColumn<ExListOfMembers, String> Col_in;

    @FXML
    private Label Date_label;

    @FXML
    private AnchorPane ExList;

    @FXML
    private TableColumn<ExListOfMembers, Double> ValueIn;

    @FXML
    private TableColumn<ExListOfMembers, String> Col_from;

    @FXML
    private TableColumn<ExListOfMembers, Double> ValueOut;

    @FXML
    private TextField Date_field;

    @FXML
    private Button ShowHistory;

    @FXML
    private TableView<ExListOfMembers> table;

    @FXML
    private TextField Client_field;

    @FXML
    void DisplayInfo(ActionEvent event) {
        Col_Name.setCellValueFactory(new PropertyValueFactory<ExListOfMembers, String>("login"));
        Col_Date.setCellValueFactory(new PropertyValueFactory<ExListOfMembers, String>("date"));
        Col_from.setCellValueFactory(new PropertyValueFactory<ExListOfMembers, String>("vIn"));
        Col_in.setCellValueFactory(new PropertyValueFactory<ExListOfMembers, String>("vOut"));
        ValueOut.setCellValueFactory(new PropertyValueFactory<ExListOfMembers, Double>("CurIn"));
        ValueIn.setCellValueFactory(new PropertyValueFactory<ExListOfMembers, Double>("CurOut"));
        try(Connection connection =JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            if(Date_field.getText()=="" && Client_field.getText()==""){
                ResultSet resultSet=statement.executeQuery("SELECT * FROM exhistory");
                while (resultSet.next()){
                    usersData.add(new ExListOfMembers(resultSet.getString("DayEx"),resultSet.getString("CurIn"),resultSet.getString("CurOut"),resultSet.getString("LoginDate"),resultSet.getDouble("ValueIn"),resultSet.getDouble("ValueOut")));
                }
                connection.close();

            }
            if(Date_field.getText()=="" && Client_field.getText()!=""){
                ResultSet resultSet=statement.executeQuery("SELECT * FROM exhistory WHERE LoginDate='"+Client_field.getText()+"'");
                while (resultSet.next()){
                    usersData.add(new ExListOfMembers(resultSet.getString("DayEx"),resultSet.getString("CurIn"),resultSet.getString("CurOut"),resultSet.getString("LoginDate"),resultSet.getDouble("ValueIn"),resultSet.getDouble("ValueOut")));
                }
                connection.close();

            }
            if(Date_field.getText()!="" && Client_field.getText()==""){
                ResultSet resultSet=statement.executeQuery("SELECT * FROM exhistory WHERE DayEx='"+Date_field.getText()+"'");
                while (resultSet.next()){
                    usersData.add(new ExListOfMembers(resultSet.getString("DayEx"),resultSet.getString("CurIn"),resultSet.getString("CurOut"),resultSet.getString("LoginDate"),resultSet.getDouble("ValueIn"),resultSet.getDouble("ValueOut")));
                }
                connection.close();

            }if(Date_field.getText()!="" && Client_field.getText()!=""){
                ResultSet resultSet=statement.executeQuery("SELECT * FROM exhistory WHERE LoginDate='"+Client_field.getText()+"' DayEx='"+Date_field.getText()+"'");
                while (resultSet.next()){
                    usersData.add(new ExListOfMembers(resultSet.getString("DayEx"),resultSet.getString("CurIn"),resultSet.getString("CurOut"),resultSet.getString("LoginDate"),resultSet.getDouble("ValueIn"),resultSet.getDouble("ValueOut")));
                }
                connection.close();

            }
            table.setItems(usersData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
