package br.com.passabus.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.passabus.model.aplication.FXMLLoginScreenAplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXMLLoginScreenController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;
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
    
    @Override
    public void initialize(URL location, ResourceBundle resourses) {
        
    }
    
    @FXML
    private void btnEntrarOnMouseClicked(MouseEvent event) throws IOException {
        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FXMLLoginScreenAplication loginScreen = new FXMLLoginScreenAplication();

        loginScreen.trocarDeTela(stageAtual, "/br/com/passabus/view/screens/FXMLNavigationPanel.fxml");

    }

    @FXML
    public void btnEntrarOnMouseEntered(MouseEvent event) {
        System.out.println("Mouse entrou no botão");
    }

    @FXML
    public void btnEntrarOnMouseExited(MouseEvent event) {
        System.out.println("Mouse Saiu do botão");
    }
}

