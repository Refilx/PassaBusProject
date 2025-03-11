module br.com.passabus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires jasypt;


    opens br.com.passabus.view.screens to javafx.fxml;
    opens br.com.passabus.util to javafx.fxml;
    exports br.com.passabus.controller;
    opens br.com.passabus.controller to javafx.fxml;
    exports br.com.passabus.model.aplication;
    opens br.com.passabus.model.aplication to javafx.fxml;
    opens br.com.passabus.model.validator to javafx.fxml;
}