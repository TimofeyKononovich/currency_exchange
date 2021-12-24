package com.example.currency_exchange;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ExList {

    @FXML
    private TableColumn<?, ?> Col_Date;

    @FXML
    private Label Date_Label;

    @FXML
    private TableColumn<?, ?> Col_Name;

    @FXML
    private TableColumn<?, ?> Col_in;

    @FXML
    private Label Date_label;

    @FXML
    private AnchorPane ExList;

    @FXML
    private TableColumn<?, ?> ValueIn;

    @FXML
    private TableColumn<?, ?> Col_from;

    @FXML
    private TableColumn<?, ?> ValueOut;

    @FXML
    private TextField Date_field;

    @FXML
    private Button ShowHistory;

    @FXML
    private TableView<?> table;

    @FXML
    private TextField Client_field;

    @FXML
    void DisplayInfo(ActionEvent event) {

    }

}
