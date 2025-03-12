package br.com.passabus.controller;

import br.com.passabus.model.domain.Viagem;
import br.com.passabus.model.validator.CartaoValidator;
import br.com.passabus.model.util.TextFieldFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLVendaScreenController implements Initializable {

    // -----------------------TELA PRINCIPAL DA VENDA-----------------------
    @FXML
    private Button btnPesquisaViagem;
    @FXML
    private Button btnVendaProximo;
    @FXML
    private TextField textFieldDataViagemPesquisa;
    @FXML
    private TextField textFieldDestinoViagemPesquisa;
    @FXML
    private TextField textFieldOrigemViagemPesquisa;
    @FXML
    private TableView<Viagem> tvViagensPesquisadas;

    // -----------------------TELA POP UP SELEÇÃO DE POLTRONA-----------------------
    @FXML
    private Button btnSelecaoPoltronaCancelar;
    @FXML
    private Button btnSelecaoPoltronaProximo;
    @FXML
    private GridPane gridPane = new GridPane();
    @FXML
    private TextField textFieldPoltronaSelecionada;

    //
    private static boolean chamarTelaPopUp;
    int[] poltronasVendidas = {3,15,17,39,27,10,1,4,30,31};

    //-------------------------------------------------------
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


    // -----------------------INICIALIZE E BOTÕES DA TELA PRINCIPAL DA VENDA-----------------------
    @Override
    public void initialize(URL location, ResourceBundle resourses) {
        configurarGridPane();
    }

    @FXML
    void btnPesquisarViagemOnMouseClicked(MouseEvent event) {
        textFieldOrigemViagemPesquisa.getText();
        textFieldDestinoViagemPesquisa.getText();
        textFieldDataViagemPesquisa.getText();


    }

    @FXML
    void btnVendaProximoMouseClicked(MouseEvent event) throws IOException {
        abrirTelaPopUp("FXMLPopUpSelecionarAssentosPassageiro");
    }

    // -----------------------BOTÕES DA TELA POP UP SELEÇÃO DE POLTRONA-----------------------
    @FXML
    void btnSelecaoPoltronaCancelarMouseClicked(MouseEvent event) {
        chamarTelaPopUp = false;
        Stage stageAtual = (Stage) ((javafx.scene.Node) (btnSelecaoPoltronaCancelar)).getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    void btnSelecaoPoltronaProximoMouseClicked(MouseEvent event) {
        chamarTelaPopUp = true;
        Stage stageAtual = (Stage) ((javafx.scene.Node) (btnSelecaoPoltronaProximo)).getScene().getWindow();
        stageAtual.close();
    }

    public void gridMouseClicked(MouseEvent mouseEvent) {

    }

    // -----------------------BOTÕES DA TELA POP UP DE DADOS DO PASSAGEIRO-----------------------
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

    // -----------------------MÉTODOS DE CONFIGURAÇÃO/AUXILIAR AS TELAS DE VENDAS-----------------------
    /**
     * MÉTODO QUE CHAMA A PRIMEIRA TELA POP UP DE SELEÇÃO DE POLTRONA
     * @param page
     * @throws IOException
     */
     void abrirTelaPopUp(String page) throws IOException {
        // Carrega o FXML do pop-up
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/passabus/view/screens/"+page+".fxml"));
        Parent popupRoot = loader.load();

        // Cria um novo Stage para o pop-up
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);  // Modalidade que bloqueia todas as janelas ate a pop up ser fechada
        popupStage.setResizable(false);  // Impede redimensionamento

        // Define a cena e exibe
        Scene popupScene = new Scene(popupRoot);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();  // Aguarda até o pop-up ser fechado

         // Se o usuário apertar o botão próximo na tela de seleção de poltrona
         if(chamarTelaPopUp) {
             chamarTelaPopUp = false;
             abrirTelaPopUp("FXMLPopUpDadosPassageiro"); // chama a tela de dados do passageiro
         }
    }

//
//    public void abrirDadosPassageiroPopUp() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/passabus/view/screens/FXMLPopUpDadosPassageiro.fxml"));
//            Parent root = loader.load();
//
//            // Obtendo o controller da segunda pop-up para manipular campos depois
//            FXMLPopUpDadosPassageiroController controller = loader.getController();
////            controller.inicializarDados("Texto Inicial");  // Exemplo de passagem de dados
//
//            Stage segundaPopUp = new Stage();
//            segundaPopUp.initModality(Modality.APPLICATION_MODAL);  // Modal: Bloqueia janela principal
//            segundaPopUp.setResizable(false);
//            segundaPopUp.setScene(new Scene(root));
//            segundaPopUp.showAndWait();  // Espera até a janela ser fechada
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * Método que configura a tela pop up de seleção de poltrona
     */
    public void configurarGridPane() {
        chamarTelaPopUp = false;
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
                    textFieldPoltronaSelecionada.setText(""+numeroBotao);
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

    // -----------------------MASCARAS DOS CAMPOS DAS TELA PRINCIPAL DA VENDA-----------------------
    @FXML
    void tfDataViagemPesquisaKeyReleased(KeyEvent event) {
        TextFieldFormatter dataFormater = new TextFieldFormatter();
        dataFormater.setMask("##/##/####");
        dataFormater.setCaracteresValidos("0123456789");
        dataFormater.setTf(textFieldDataViagemPesquisa);
        dataFormater.formatter();
    }

    // -----------------------MASCARAS DOS CAMPOS DA TELA POP UP SELEÇÃO DE POLTRONA----------------


    // -----------------------MASCARAS DOS CAMPOS DA TELA POP UP DADOS DO PASSAGEIRO----------------
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
}
