package br.com.passabus.controller;

import br.com.passabus.model.validator.CartaoValidator;
import br.com.passabus.model.util.TextFieldFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FXMLPopUpDadosPassageiroController {

    @FXML
    private Button btnFinalizarVendaCartao;

    @FXML
    private Button btnFinalizarVendaDinheiro;

    @FXML
    private TabPane tapPane;

    @FXML
    private TextField textFieldCPFPassageiro;

    @FXML
    private TextField textFieldDataNascimento;

    @FXML
    private TextField textFieldNomePassageiro;

    @FXML
    private TextField textFieldNumCartao;

    @FXML
    private TextField textFieldPropCartao;

    @FXML
    private TextField textFieldValorPago;

    @FXML
    private TextField textFieldValorTotal;

    @FXML
    void tfCPFPassageiroKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldCPFPassageiro);
        tff.formatter();
    }

    @FXML
    void tfDataNascimentoKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##/##/####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldDataNascimento);
        tff.formatter();
    }

    @FXML
    void tfNumCartaoKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("####-####-####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldNumCartao);
        tff.formatter();
    }

    @FXML
    void btnFinalizarVendaCartaoMouseClicked(MouseEvent event) {
        // teste se o validador de cartão funciona
        System.out.println(CartaoValidator.validarCartaoCredito(textFieldNumCartao.getText()));
        System.out.println(tapPane.getSelectionModel().getSelectedItem().getText());
    }

    @FXML
    void btnFinalizarVendaDinheiroMouseClicked(MouseEvent event) {
        System.out.println(tapPane.getSelectionModel().getSelectedItem().getText());
    }

}
