package br.com.passabus.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.passabus.model.aplication.FXMLLoginScreenAplication;
import br.com.passabus.model.dao.VerifyDAO;
import br.com.passabus.model.util.CaseTextFormatter;
import br.com.passabus.model.validator.LoginValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLLoginScreenController implements Initializable {

    @FXML
    private AnchorPane loginScreen;
    @FXML
    private VBox VBoxFields;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Pane bottomPane;
    @FXML
    private Button btnEntrar;
    @FXML
    private Pane leftPane;
    @FXML
    private ImageView logoImgView;
    @FXML
    private PasswordField passFieldSenha;
    @FXML
    private Pane rightPane;
    @FXML
    private Label senhaLabel;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private Pane topPane;
    @FXML
    private Label usernameLabel;

    // ------- RECOVER PASSWORD SCREEN -------

    @FXML
    private Button btnRecuperar;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField textFieldEmailToRecoverPassword;
    
    @Override
    public void initialize(URL location, ResourceBundle resourses) {
        CaseTextFormatter.applyLowerCase(textFieldUsername);
    }
    
    @FXML
    private void btnEntrarOnMouseClicked(MouseEvent event) throws IOException {
        //Pegamos o conteúdo do campo username da tela de login e armazenamos na variável user
        String username = textFieldUsername.getText();

        //Inicializamos a String que pegará o valor do campo password da tela de login
        String password = passFieldSenha.getText();

        boolean resultVerify = new LoginValidator().resultVerify(username, password);

        if(resultVerify){

            Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoginScreenAplication loginScreen = new FXMLLoginScreenAplication();

            if (new VerifyDAO().verifySuperUser()) {
                loginScreen.trocarDeTela(stageAtual, "/br/com/passabus/view/screens/FXMLNavigationPanel.fxml");
            } else {
                loginScreen.trocarDeTela(stageAtual, "/br/com/passabus/view/screens/FXMLCommonUserNavigationPaneScreen.fxml");
            }
        }
    }

    // --------- NAVEGAÇÃO ENTRE TELAS DE RECUPERAÇÃO E LOGIN ----------
    @FXML
    private void getLoginScreen(MouseEvent event) throws IOException {
        loadPage("FXMLLoginScreen");
    }

    @FXML
    private void getEmailToRecoverPasswordScreen(MouseEvent event) throws IOException {
        loadPage("FXMLEmailToRecoverPasswordScreen");
    }

    @FXML
    private void getEnterRecoveryCodeScreen(MouseEvent event) throws IOException {
        loadPage("FXMLEnterRecoveryCodeScreen");
    }

    @FXML
    private void getEnterNewPasswordScreen(MouseEvent event) throws IOException {
        loadPage("FXMLEnterNewPasswordScreen");
    }

    public void loadPage(String page) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/passabus/view/screens/"+page+".fxml"));
        Parent root = fxmlLoader.load();

        borderPane.setCenter(root);
    }

    // ------- RECOVER PASSWORD SCREEN -------
    @FXML
    void btnRecuperarOnMouseClicked(MouseEvent event) {

    }

}

