package br.com.passabus.controller;

import br.com.passabus.model.dao.ViagemDAO;
import br.com.passabus.model.domain.Passageiro;
import br.com.passabus.model.domain.Viagem;
import br.com.passabus.model.validator.CartaoValidator;
import br.com.passabus.model.util.TextFieldFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
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
    @FXML
    private TableColumn tc_tipoViagem;
    @FXML
    private TableColumn tc_classe;
    @FXML
    private TableColumn tc_destino;
    @FXML
    private TableColumn tc_horario;
    @FXML
    private TableColumn tc_origem;

    ObservableList<Viagem> observableList;

    private  List<Viagem> listaDeViagens = new LinkedList<>();

    private Viagem viagemEscolhida = new Viagem();
    private Passageiro dadosDoPassageiro = new Passageiro();

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
        String origem = textFieldOrigemViagemPesquisa.getText();
        String destino = textFieldDestinoViagemPesquisa.getText();
//        textFieldDataViagemPesquisa.getText();

        if(tvViagensPesquisadas.isDisable())
            tvViagensPesquisadas.setDisable(false);

        prepararListaTabela(origem, destino);

    }

    @FXML
    void tvViagensPesquisadasOnMouseClicked(MouseEvent event) {
        if(btnVendaProximo.isDisable())
            btnVendaProximo.setDisable(false);
    }

    @FXML
    void btnVendaProximoMouseClicked(MouseEvent event) throws IOException {
        viagemEscolhida = tvViagensPesquisadas.getSelectionModel().getSelectedItem();

        if(viagemEscolhida.getOrigem() != null) {
            dadosDoPassageiro.setOrigem(viagemEscolhida.getOrigem());
            dadosDoPassageiro.setDestino(viagemEscolhida.getDestino());

            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dadosDoPassageiro.setDataViagem(LocalDate.parse(textFieldDataViagemPesquisa.getText(), format));

            abrirTelaPopUp("FXMLPopUpSelecionarAssentosPassageiro");
        } else
            JOptionPane.showMessageDialog(null, "Selecione uma viagem clicando na tabela antes de continuar!");
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
     * MÉTODO QUE FAZ A CONFIGURAÇÃO DA TABELA DE VIAGENS
     * @param origem
     * @param destino
     */
    void prepararListaTabela(String origem, String destino) {

        tc_origem.setCellValueFactory(new PropertyValueFactory<>("origem"));
        tc_destino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        tc_classe.setCellValueFactory(new PropertyValueFactory<>("classe"));
        tc_horario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        tc_tipoViagem.setCellValueFactory(new PropertyValueFactory<>("tipoViagem"));

        // Pegando a lista de viagens do banco
        listaDeViagens =  new ViagemDAO().getViagens(origem, destino);

        // configurando o observable list com os dados da lista do banco
        observableList = FXCollections.observableList(listaDeViagens);

        // Configurando a tabela após a pesquisa
        tvViagensPesquisadas.setItems(observableList);
    }

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
