package br.com.passabus.controller;

import br.com.passabus.model.dao.PassageiroDAO;
import br.com.passabus.model.dao.VendaDAO;
import br.com.passabus.model.dao.ViagemDAO;
import br.com.passabus.model.domain.Passageiro;
import br.com.passabus.model.domain.Venda;
import br.com.passabus.model.domain.Viagem;
import br.com.passabus.model.util.TextFieldFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FXMLCancelPassagemController {

    @FXML
    private Button btnCancelarPassagem;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Pane cancelPassagemScreen;

    @FXML
    private TextField textFieldBilhete;

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

    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static Venda dadosDaVenda = new Venda();
    private static Passageiro dadosDoPassageiro = new Passageiro();
    private static Viagem dadosDaViagem = new Viagem();

    private static VendaDAO vendaDAO = new VendaDAO();

    @FXML
    void btnCancelarPassagemOnMouseClicked(MouseEvent event) {
        int opcao = JOptionPane.showOptionDialog(null, "Tem certeza que deseja cancelar a passagem?", "Confirmação final",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim", "Não"}, 0);

        if(opcao == 0) {
            vendaDAO.cancelarPassagem(Long.parseLong(textFieldBilhete.getText()));

            btnCancelarPassagem.setDisable(true);
            tfShowPassageiro.setDisable(true);
            tfShowOrigem.setDisable(true);
            tfShowDestino.setDisable(true);
            tfShowFormaPagamento.setDisable(true);
            tfShowTotalPago.setDisable(true);
            tfShowTxCancelamento.setDisable(true);
            tfShowLinha.setDisable(true);
            tfShowDataViagem.setDisable(true);
            tfShowHorario.setDisable(true);
            tfShowPoltrona.setDisable(true);

            tfShowPassageiro.setText(null);
            tfShowOrigem.setText(null);
            tfShowDestino.setText(null);
            tfShowLinha.setText(null);
            tfShowFormaPagamento.setText(null);
            tfShowTotalPago.setText(null);
            tfShowTxCancelamento.setText(null);
            tfShowDataViagem.setText(null);
            tfShowHorario.setText(null);
            tfShowPoltrona.setText(null);
            textFieldBilhete.setText(null);

        } else
            JOptionPane.showMessageDialog(null, "A passagem NÃO foi cancelada!");
    }

    @FXML
    void btnPesquisarOnMouseClicked(MouseEvent event) {

        if(!textFieldBilhete.getText().isEmpty()) {

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

            vendaDAO.getVendaParaCancelar(dadosDoPassageiro, dadosDaViagem, dadosDaVenda, Long.parseLong(textFieldBilhete.getText()));

            double txCancelamento = 7.5;

            tfShowPassageiro.setText(dadosDoPassageiro.getNome());
            tfShowOrigem.setText(dadosDoPassageiro.getOrigem());
            tfShowDestino.setText(dadosDoPassageiro.getDestino());
            tfShowLinha.setText(dadosDaViagem.getLinha());
            tfShowFormaPagamento.setText(dadosDaVenda.getOpcaoPagamento());
            tfShowTotalPago.setText(""+dadosDaVenda.getValorTotal());
            tfShowTxCancelamento.setText("R$ "+txCancelamento);
            tfShowDataViagem.setText(dadosDoPassageiro.getDataViagem().format(format));
            tfShowHorario.setText(dadosDaViagem.getHorario().toString());
            tfShowPoltrona.setText(""+dadosDoPassageiro.getPoltrona());

        }

    }

    @FXML
    void textFieldBilheteOnKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("#############");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldBilhete);
        tff.formatter();
    }
}
