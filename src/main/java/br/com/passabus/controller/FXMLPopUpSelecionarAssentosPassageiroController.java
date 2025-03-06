package br.com.passabus.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLPopUpSelecionarAssentosPassageiroController implements Initializable {

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnProximo;
    @FXML
    private TextField textFieldSelecionada;
    @FXML
    private GridPane gridPane;

    private static boolean proximo;

    int[] poltronasVendidas = {3,15,17,39,27,10,1,4,30,31};

    public boolean getProximo() {
        return proximo;
    }

    @FXML
    void btnCancelarMouseClicked(MouseEvent event) {
        Stage stageAtual = (Stage) ((javafx.scene.Node) (btnCancelar)).getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    void btnProximoMouseClicked(MouseEvent event) throws IOException {
        proximo = true;
        Stage stageAtual = (Stage) ((javafx.scene.Node) (btnProximo)).getScene().getWindow();
        stageAtual.close();

//        new FXMLVendaScreenController().abrirDadosPassageiroPopUp();
    }

    @FXML
    void gridMouseClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resourses) {
        proximo = false;
        int colunas = 12;
        int linhas = 5;
        int contador = 1;  // Contador para numerar os botões

        // Adicionando botões dinamicamente
        for (int i = 0; i < colunas; i++) {
            for (int j = 0; j < linhas; j++) {

                if(j == 2)
                    continue;

                if((i == 11 && j == 0) || (i == 11 && j == 1))
                    continue;

                Button botao = new Button(""+contador);
                botao.setPrefSize(40, 40);  // Tamanho dos botões
                botao.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 40;");

                // Capturando o número do botão no evento de clique
                int numeroBotao = contador;
                botao.setOnAction(evento -> {
                    textFieldSelecionada.setText(""+numeroBotao);
                });

                // Pintando de vermelho as poltronas vendidas e impedindo de o usuário selecionar as poltronas já vendidas
                for(int vendidas: poltronasVendidas) {
                    if (contador == vendidas) {
                        botao.setStyle("-fx-background-color: #EF4444; -fx-background-radius: 40;");
                        botao.setOnAction(null);
                        break;
                    }
                }

                gridPane.add(botao, i, j);  // Adiciona o botão na coluna (j) e linha (i)
                if(contador <= 46)
                    contador++;
            }
        }
    }
}
