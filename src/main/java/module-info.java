module br.com.passabus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    opens br.com.passabus.view.screens to javafx.fxml;
    opens br.com.passabus.util to javafx.fxml;
    exports br.com.passabus.controller;
    opens br.com.passabus.controller to javafx.fxml;
    exports br.com.passabus.model.aplication;
    opens br.com.passabus.model.aplication to javafx.fxml;
}