package com.example.currency_exchange;

import com.example.currency_exchange.models.SetExchangeBar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class ExchangeApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ExchangeApplication.class.getResource("MenuBoot.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 639,566 );
        stage.setTitle("Currency Exchanger");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        try(Connection connection=JDBCSource.getConnection()) {
            Statement statement=connection.createStatement();
            statement.executeUpdate("UPDATE members SET RUB_rem=1000, USD_rem=1000, EU_rem=1000, DateLogOn='"+LocalDate.now().toString()+"' WHERE NOT DateLogOn='"+LocalDate.now().toString()+"'");
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        launch();
    }
}