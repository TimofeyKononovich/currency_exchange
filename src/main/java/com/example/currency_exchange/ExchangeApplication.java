package com.example.currency_exchange;

import com.example.currency_exchange.models.RemainValue;
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
        try (Connection connection = JDBCSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  RUB_rem, USD_rem, EU_rem FROM remvalue ORDER BY id DESC LIMIT 1");
            if (resultSet.next() == false) {
                statement.executeUpdate("INSERT INTO remvalue (RUB_rem, USD_rem, EU_rem) VALUES (1000,1000,1000)");
                statement.executeUpdate("UPDATE members SET RUB_rem=1000, USD_rem=1000, EU_rem=1000, DateLogOn='" + LocalDate.now().toString() + "' WHERE NOT DateLogOn='" + LocalDate.now().toString() + "'");
            }
            DateHandler.remainValue = new RemainValue(resultSet.getDouble("RUB_rem"), resultSet.getDouble("USD_rem"), resultSet.getDouble("EU_rem"));
            resultSet=statement.executeQuery("SELECT  buyUSD, buyRUB, buyEU, sellUSD, sellRUB, sellEU FROM exchangevalue ORDER BY id DESC LIMIT 1");
            if(resultSet.next()==false){
                statement.executeUpdate("INSERT INTO exchangevalue (buyUSD, buyRUB, buyEU, sellUSD, sellRUB, sellEU) VALUES (2.60,0.05,3,2.51,0.03,2.8)");
                resultSet=statement.executeQuery("SELECT  buyUSD, buyRUB, buyEU, sellUSD, sellRUB, sellEU FROM exchangevalue ORDER BY id DESC LIMIT 1");
                resultSet.next();
            }
            DateHandler.setExchangeBar=new SetExchangeBar(resultSet.getDouble("buyRUB"),resultSet.getDouble("sellRUB"),resultSet.getDouble("buyUSD"),resultSet.getDouble("sellUSD"),resultSet.getDouble("buyEU"),resultSet.getDouble("sellEU"));
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        launch();
    }
}
