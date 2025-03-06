package br.com.passabus;

import br.com.passabus.util.TextFieldFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
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
    void tfCPFKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldCPF);
        tff.formatter();
    }

    @FXML
    void tfDataKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##/##/####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldData);
        tff.formatter();
    }

    @FXML
    void btnCadastrarMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnCancelarMouseClicked(MouseEvent event) {

    }

}
