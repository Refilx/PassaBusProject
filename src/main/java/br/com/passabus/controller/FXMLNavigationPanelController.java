package br.com.passabus.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class FXMLNavigationPanelController implements Initializable {
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private Button btnDashboard;
    
    @FXML
    private Button btnSair;

    @FXML
    private AnchorPane dashboardScreen;

    @FXML
    private void getCadastroUserScreen(MouseEvent event) throws IOException {
        loadPage("FXMLCadastroUserScreen");
    }

    @FXML
    private void getCancelPassagemScreen(MouseEvent event) throws IOException {
        loadPage("FXMLCancelPassagemScreen");
    }

    @FXML
    private void getDashboardScreen(MouseEvent event) {
        borderPane.setCenter(dashboardScreen);
    }

    @FXML
    private void getHistLoginScreen(MouseEvent event) throws IOException {
        loadPage("FXMLHistLoginScreen");
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

    @Override
    public void initialize(URL location, ResourceBundle resourses) {
        
    }
    
}
