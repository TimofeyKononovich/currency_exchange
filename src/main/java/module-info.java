module com.example.currency_exchange {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.currency_exchange to javafx.fxml;
    exports com.example.currency_exchange;
}