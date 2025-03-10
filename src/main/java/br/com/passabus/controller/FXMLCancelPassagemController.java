package br.com.passabus.controller;

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
    private TextField textFieldCPF2;

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
        tfShowPassageiro.setText("Bruno Sousa da Silva");
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

    }

    @FXML
    void textFieldDataViagemOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void textFieldPoltronaOnKeyReleased(KeyEvent event) {

    }

}
