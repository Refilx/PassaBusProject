package br.com.passabus.controller;

import br.com.passabus.model.util.TextFieldFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class FXMLCancelPassagemController {

    @FXML
    private Button btnCancelarPassagem;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Pane cancelPassagemScreen;

    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldHorario;

    @FXML
    private TextField textFieldDataViagem;

    @FXML
    private TextField textFieldDestino;

    @FXML
    private TextField textFieldOrigem;

    @FXML
    private TextField textFieldPoltrona;

    @FXML
    private TextField tfShowDataViagem;

    @FXML
    private TextField tfShowDestino;

    @FXML
    private TextField tfShowFormaPagamento;

    @FXML
    private TextField tfShowHorario;

    @FXML
    private TextField tfShowLinha;

    @FXML
    private TextField tfShowOrigem;

    @FXML
    private TextField tfShowPassageiro;

    @FXML
    private TextField tfShowPoltrona;

    @FXML
    private TextField tfShowTotalPago;

    @FXML
    private TextField tfShowTxCancelamento;

    @FXML
    void btnCancelarPassagemOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnPesquisarOnMouseClicked(MouseEvent event) {
        btnCancelarPassagem.setDisable(false);
        tfShowPassageiro.setDisable(false);
        tfShowOrigem.setDisable(false);
        tfShowDestino.setDisable(false);
        tfShowFormaPagamento.setDisable(false);
        tfShowTotalPago.setDisable(false);
        tfShowTxCancelamento.setDisable(false);
        tfShowLinha.setDisable(false);
        tfShowDataViagem.setDisable(false);
        tfShowHorario.setDisable(false);
        tfShowPoltrona.setDisable(false);

    }

    @FXML
    void textFieldCPFOnKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldCPF);
        tff.formatter();
    }

    @FXML
    void textFieldDataViagemOnKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##/##/####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldDataViagem);
        tff.formatter();
    }

    @FXML
    void textFieldHorarioOnKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##:##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldHorario);
        tff.formatter();
    }

    @FXML
    void textFieldPoltronaOnKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldPoltrona);
        tff.formatter();
    }

}
