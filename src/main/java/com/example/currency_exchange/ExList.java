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
    private TableColumn<ExListOfMembers, String> ValueIn;

    @FXML
    private TableColumn<ExListOfMembers, String> Col_from;;

    @FXML
    private TableColumn<ExListOfMembers, String> ValueOut;

    @FXML
    private TextField Date_field;

    @FXML
    private Button ShowHistory;

    @FXML
    private TableView<ExListOfMembers> table;

    @FXML
    private TextField Client_field;

    @FXML
    private Button Clear_But;

    @FXML
    void ClearTable(ActionEvent event) {
        table.getItems().clear();
    }

    @FXML
    void DisplayInfo(ActionEvent event) {
        table.getItems().clear();
        try(Connection connection =JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            String manyDate=Date_field.getText().replaceAll(", ","' OR DayEx='");
            String manyClient=Client_field.getText().replaceAll(", ","' OR LoginDate='");
            if(Date_field.getText()=="" && Client_field.getText()==""){
                ResultSet resultSet=statement.executeQuery("SELECT * FROM exhistory");
                while (resultSet.next()){
                    System.out.println(resultSet.getString("DayEx")+" "+resultSet.getString("CurIn")+" "+resultSet.getString("CurOut")+" "+resultSet.getString("LoginDate")+" "+resultSet.getDouble("ValueIn")+" "+resultSet.getDouble("ValueOut"));
                    usersData.add(new ExListOfMembers(resultSet.getString("DayEx"),resultSet.getString("CurIn"),resultSet.getString("CurOut"),resultSet.getString("LoginDate"),Double.toString(resultSet.getDouble("ValueIn")),Double.toString(resultSet.getDouble("ValueOut"))));
                }
                connection.close();

            }
            if(Date_field.getText()=="" && Client_field.getText()!=""){
                ResultSet resultSet=statement.executeQuery("SELECT * FROM exhistory WHERE LoginDate='"+manyClient+"'");
                while (resultSet.next()){
                    usersData.add(new ExListOfMembers(resultSet.getString("DayEx"),resultSet.getString("CurIn"),resultSet.getString("CurOut"),resultSet.getString("LoginDate"),Double.toString(resultSet.getDouble("ValueIn")),Double.toString(resultSet.getDouble("ValueOut"))));
                }
                connection.close();

            }
            if(Date_field.getText()!="" && Client_field.getText()==""){
                ResultSet resultSet=statement.executeQuery("SELECT * FROM exhistory WHERE DayEx='"+manyDate+"'");
                while (resultSet.next()){
                    usersData.add(new ExListOfMembers(resultSet.getString("DayEx"),resultSet.getString("CurIn"),resultSet.getString("CurOut"),resultSet.getString("LoginDate"),Double.toString(resultSet.getDouble("ValueIn")),Double.toString(resultSet.getDouble("ValueOut"))));
                }
                connection.close();

            }if(Date_field.getText()!="" && Client_field.getText()!=""){
                ResultSet resultSet=statement.executeQuery("SELECT * FROM exhistory WHERE LoginDate='"+manyClient+"' AND DayEx='"+manyDate+"'");
                while (resultSet.next()){
                    usersData.add(new ExListOfMembers(resultSet.getString("DayEx"),resultSet.getString("CurIn"),resultSet.getString("CurOut"),resultSet.getString("LoginDate"),Double.toString(resultSet.getDouble("ValueIn")),Double.toString(resultSet.getDouble("ValueOut"))));
                }
                connection.close();

            }
            Col_Name.setCellValueFactory(data -> data.getValue().loginProperty());
            Col_Date.setCellValueFactory(data -> data.getValue().dateProperty());
            Col_from.setCellValueFactory(data -> data.getValue().vInProperty());
            Col_in.setCellValueFactory(data -> data.getValue().vOutProperty());
            ValueOut.setCellValueFactory(data -> data.getValue().curInProperty());
            ValueIn.setCellValueFactory(data -> data.getValue().curFromProperty());
            table.setItems(usersData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

}
