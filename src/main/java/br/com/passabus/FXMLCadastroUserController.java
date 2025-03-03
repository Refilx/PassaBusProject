package br.com.passabus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class FXMLCadastroUserController {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Pane cadastroUserScreen;

    @FXML
    private HBox passFieldConfirmSenha;

    @FXML
    private PasswordField passFieldSenha;

    @FXML
    private RadioButton radioButtonAdministrador;

    @FXML
    private RadioButton radioButtonGerente;

    @FXML
    private RadioButton radioButtonOperador;

    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldData;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldUsername;

    @FXML
    void btnCadastrarMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnCancelarMouseClicked(MouseEvent event) {

    }

}
