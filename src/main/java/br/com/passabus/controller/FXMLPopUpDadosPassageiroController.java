package br.com.passabus.controller;

import br.com.passabus.model.validator.Validator;
import br.com.passabus.util.TextFieldFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class FXMLPopUpDadosPassageiroController {

    @FXML
    private Button btnFinalizarVendaCartao;

    @FXML
    private Button btnFinalizarVendaDinheiro;

    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldData;

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
        System.out.println(Validator.validarCartaoCredito(textFieldNumCartao.getText()));
    }

    @FXML
    void btnFinalizarVendaDinheiroMouseClicked(MouseEvent event) {

    }

}
