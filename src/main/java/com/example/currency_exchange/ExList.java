package com.example.currency_exchange;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;



public class ExList {

    @FXML
    private TableColumn<?, ?> Col_from;

    @FXML
    private TableColumn<?, ?> Col_Date;

    @FXML
    private TableColumn<?, ?> Col_Name;

    @FXML
    private TableColumn<?, ?> Col_in;

    @FXML
    private TableColumn<?, ?> Col_Val;

    @FXML
    private AnchorPane ExList;

    @FXML
    private TableView<?> table;

}

