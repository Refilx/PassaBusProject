package br.com.passabus.controller;

import br.com.passabus.model.aplication.FXMLLoginScreenAplication;
import br.com.passabus.model.dao.LogDAO;
import br.com.passabus.model.domain.Log;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLCommonUserNavigationPaneController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnSair;

    @FXML
    private AnchorPane dashboardScreen;

//    @FXML
//    private Label labelRole;
//
//    @FXML
//    private Label labelUsername;
//
//    private LogDAO logDoUsuario = new LogDAO();

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
    private void logout(MouseEvent event) throws IOException {
//        int opcao = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair?", "Confirmação",
//                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Sim", "Não"}, null);
//
//        //Se o usuário selecionar a opção "Sim", a aplicação irá fechar a tela de dashboard e voltar para a tela de login
//        if(opcao == 0){
////            new LogDAO.saveLogout(logDoUsuario);
////
////            this.dispose();
////
////            FXMLLoginScreen loginScreen = new LoginScreen();
////            loginScreen.setVisible(true);
//
//            btnSair.setOnMouseClicked(e -> {
//                System.exit(0);
//            });
//
//            FXMLLoginScreenAplication login = new FXMLLoginScreenAplication();
//
//            login.start(new Stage());
//        }

    }

    public void loadPage(String page) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/passabus/view/screens/"+page+".fxml"));
        Parent root = fxmlLoader.load();

        borderPane.setCenter(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Log log = logDoUsuario.getUltimoLogado();
//
//        labelRole.setText(log.getRole());
//        labelUsername.setText(log.getUsername());
    }
}
