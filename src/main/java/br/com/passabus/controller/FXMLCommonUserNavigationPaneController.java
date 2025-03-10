package br.com.passabus.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class FXMLCommonUserNavigationPaneController {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnSair;

    @FXML
    private AnchorPane dashboardScreen;

    @FXML
    private void getCancelPassagemScreen(MouseEvent event) throws IOException {
        loadPage("FXMLCancelPassagemScreen");
    }

    @FXML
    private void getDashboardScreen(MouseEvent event) {
        borderPane.setCenter(dashboardScreen);
    }

    @FXML
    private void getHistVendaScreen(MouseEvent event) throws IOException {
        loadPage("FXMLHistVendaScreen");
    }

    @FXML
    private void getVendaScreen(MouseEvent event) throws IOException {
        loadPage("FXMLVendaScreen");
    }

    @FXML
    private void logout(MouseEvent event) {
        btnSair.setOnMouseClicked(e -> {
            System.exit(0);
        });
    }

    public void loadPage(String page) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/passabus/view/screens/"+page+".fxml"));
        Parent root = fxmlLoader.load();

        borderPane.setCenter(root);
    }

}
