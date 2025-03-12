package br.com.passabus.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.passabus.model.aplication.FXMLLoginScreenAplication;
import br.com.passabus.model.dao.LogDAO;
import br.com.passabus.model.domain.Log;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;

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
    private Label labelRole;
    @FXML
    private Label labelUsername;

    private LogDAO logDoUsuario = new LogDAO();
    private Log usuarioLogado;

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
    private void logout(MouseEvent event) throws IOException {
        int opcao = JOptionPane.showOptionDialog(null, "Tem certeza que deseja sair?", "Confirmação",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] {"Sim", "Não"}, null);

        //Se o usuário selecionar a opção "Sim", a aplicação irá fechar a tela de dashboard e voltar para a tela de login
        if(opcao == 0){
            LogDAO logDAO = new LogDAO();
            logDAO.saveLogout(usuarioLogado);

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stageAtual.close(); // fecha a tela de dashboard

            // Chama a tela de login e reinicia a aplicação
            FXMLLoginScreenAplication login = new FXMLLoginScreenAplication();
            login.start(new Stage());
        }
    }
    
    public void loadPage(String page) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/passabus/view/screens/"+page+".fxml"));
        Parent root = fxmlLoader.load();
        
        borderPane.setCenter(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        usuarioLogado = logDoUsuario.getUltimoLogado();

        labelRole.setText(usuarioLogado.getRole());
        labelUsername.setText(usuarioLogado.getUsername());
    }
    
}
