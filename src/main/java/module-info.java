module br.com.passabus {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.desktop;


    opens br.com.passabus to javafx.fxml;
    exports br.com.passabus;
}