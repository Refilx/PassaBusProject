package br.com.passabus.controller;

import br.com.passabus.model.dao.PassageiroDAO;
import br.com.passabus.model.dao.VendaDAO;
import br.com.passabus.model.dao.ViagemDAO;
import br.com.passabus.model.domain.Passageiro;
import br.com.passabus.model.domain.Venda;
import br.com.passabus.model.domain.Viagem;
import br.com.passabus.model.factory.ConnectionFactory;
import br.com.passabus.model.util.CalculadoraPassagem;
import br.com.passabus.model.util.CaseTextFormatter;
import br.com.passabus.model.validator.CPFValidator;
import br.com.passabus.model.validator.CartaoValidator;
import br.com.passabus.model.util.TextFieldFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    private ObservableList<Viagem> observableList;

    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private List<Viagem> listaDeViagens = new LinkedList<>();

    private static CalculadoraPassagem calc = new CalculadoraPassagem();

    private static Viagem dadosDaViagemEscolhida = new Viagem();
    private static Passageiro dadosDoPassageiro = new Passageiro();
    private static Venda dadosDaVenda = new Venda();

    private PassageiroDAO passageiroDAO = new PassageiroDAO();
    private VendaDAO vendaDAO = new VendaDAO();

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
    private static LinkedList<Integer> poltronasVendidas = new LinkedList<>();

    // -----------------------TELA POP UP PREENCHIMENTO DOS DADOS DO PASSAGEIRO-----------------------
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
        CaseTextFormatter.applyUpperCase(textFieldOrigemViagemPesquisa);
        CaseTextFormatter.applyUpperCase(textFieldDestinoViagemPesquisa);
    }

    @FXML
    void btnPesquisarViagemOnMouseClicked(MouseEvent event) throws Exception {
        String origem = textFieldOrigemViagemPesquisa.getText();
        String destino = textFieldDestinoViagemPesquisa.getText();

        if(textFieldDataViagemPesquisa.getText() != null && textFieldDataViagemPesquisa.getText().trim().length() == 10) {

            LocalDate hoje = new Timestamp(System.currentTimeMillis()).toLocalDateTime().toLocalDate();
            LocalDate diaPesquisado = null;
            boolean dataValida = false;

            try {
                diaPesquisado = LocalDate.parse(textFieldDataViagemPesquisa.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                dataValida = true;
            } catch (DateTimeException e) {
                dataValida = false;
                JOptionPane.showMessageDialog(null, "Data digitada inválida!\nDetalhes: %s".formatted(e.getMessage()));
            }

            // Se a data for válida, verificados se a data é pode ser usada para a venda
            if(dataValida) {

                // Comparando a data digitada com a data atual, impedindo a venda para dias que já passaram
                if (diaPesquisado.isAfter(hoje) || diaPesquisado.isEqual(hoje)) {
                    // Se a tabela de viagens ainda não tiver sido usada em alguma pesquisa
                    if (tvViagensPesquisadas.isDisable())
                        tvViagensPesquisadas.setDisable(false);

                    prepararListaTabela(origem, destino);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "A data informada já passou.\nPor favor, insira a data atual ou uma data futura para continuar.");
                    alert.showAndWait();
                }
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Preencha o campo da Data da Viagem corretamente, por favor!");
            alert.showAndWait();
        }
    }

    @FXML
    void tvViagensPesquisadasOnMouseClicked(MouseEvent event) {
        if(btnVendaProximo.isDisable())
            btnVendaProximo.setDisable(false);
    }

    @FXML
    void btnVendaProximoMouseClicked(MouseEvent event) throws IOException {
        dadosDaViagemEscolhida = tvViagensPesquisadas.getSelectionModel().getSelectedItem();

        if(dadosDaViagemEscolhida != null && textFieldDataViagemPesquisa.getText() != null) {
                dadosDoPassageiro.setDataViagem(LocalDate.parse(textFieldDataViagemPesquisa.getText(), format));

                poltronasVendidas = new VendaDAO().getPoltronasCompradas(dadosDaViagemEscolhida.getIdViagem(), Date.valueOf(dadosDoPassageiro.getDataViagem()));
                configurarGridPane();

                abrirTelaPopUp("FXMLPopUpSelecionarAssentosPassageiro");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Selecione uma viagem clicando na tabela antes de continuar!");
            alert.showAndWait();
        }
    }

    // -----------------------BOTÕES DA TELA POP UP SELEÇÃO DE POLTRONA-----------------------
    @FXML
    void btnSelecaoPoltronaCancelarMouseClicked(MouseEvent event) {
        chamarTelaPopUp = false;
        Stage stageAtual = (Stage) ((Node) (btnSelecaoPoltronaCancelar)).getScene().getWindow();
        stageAtual.close();
    }

    /**
     * Botão de próximo da tela de Seleção da poltrona do passageiro
     * @param event
     */
    @FXML
    void btnSelecaoPoltronaProximoMouseClicked(MouseEvent event) {
        if(!textFieldPoltronaSelecionada.getText().isEmpty()) {
            chamarTelaPopUp = true; // Habilitamos a chamada da próxima tela pop up

            // Armazenamos a poltrona selecionada na poltrona do respectivo passageiro
            dadosDoPassageiro.setPoltrona(Integer.parseInt(textFieldPoltronaSelecionada.getText()));

            // Já calculamos o valor da passagem antes da tela ser chamada
            calc.calcularPrecoPassagem(dadosDaViagemEscolhida.getDistancia(), dadosDaViagemEscolhida.getClasse());

            // fechamos o stage da tela Pop Up
            Stage stageAtual = (Stage) ((Node) (btnSelecaoPoltronaProximo)).getScene().getWindow();
            stageAtual.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Selecione uma das poltronas disponíveis, por favor!");
            alert.showAndWait();
        }
    }

    @FXML
    public void gridMouseClicked(MouseEvent mouseEvent) {

    }

    // -----------------------BOTÕES DA TELA POP UP DE DADOS DO PASSAGEIRO-----------------------
    @FXML
    void btnFinalizarVendaCartaoMouseClicked(MouseEvent event) throws Exception {
        dadosFinaisDaVenda();

        if(CartaoValidator.validarCartaoCredito(textFieldNumCartao.getText())) {
            int opcao = JOptionPane.showOptionDialog(null, "Cartão de crédito Aprovado!\nTem certeza que deseja finalizar a venda?", "Confirmação final",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim", "Não"}, 0);

            if (opcao == 0) {
                finalizaVenda();

                // fechamos o stage da tela Pop Up
                Stage stageAtual = (Stage) ((Node) (btnFinalizarVendaCartao)).getScene().getWindow();
                stageAtual.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Venda ainda NÃO finalizada");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"O número do Cartão de Crédito é inválido!");
            alert.showAndWait();
        }
    }

    @FXML
    void btnFinalizarVendaDinheiroMouseClicked(MouseEvent event) throws Exception {
        dadosFinaisDaVenda();

        if(!textFieldValorPago.getText().isEmpty() && calc.calculaTroco(Double.parseDouble(textFieldValorPago.getText()))) {
            int opcao = JOptionPane.showOptionDialog(null, "Tem certeza que deseja finalizar a venda?", "Confirmação final",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim", "Não"}, 0);

            if (opcao == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Troco: R$ " + calc.getTroco());
                alert.showAndWait();

                finalizaVenda();

                // fechamos o stage da tela Pop Up
                Stage stageAtual = (Stage) ((Node) (btnFinalizarVendaDinheiro)).getScene().getWindow();
                stageAtual.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Venda ainda NÃO finalizada");
                alert.showAndWait();
            }
        }
    }

    @FXML
    void tapDinheiroOnSelectionChanged(Event event) {
        textFieldValorTotal.setText("R$ %.2f".formatted(calc.getPrecoTotal()));
    }

    // -----------------------MÉTODOS DE CONFIGURAÇÃO/AUXILIAR AS TELAS DE VENDAS-----------------------

    /**
     * Salva os dados no banco de dados e finaliza de fato a venda e chama a impressão da passagem
     */
    void finalizaVenda() throws Exception {

        passageiroDAO.save(dadosDoPassageiro);

        // Pega o id do ultimo passageiro salvo
        dadosDaVenda.setIdPassageiro(dadosDoPassageiro.getIdPassageiro());
        vendaDAO.save(dadosDaVenda);

        imprimirPassagem();
    }

    /**
     * Armazena os dados do passageiro (pessoa) e da venda para salvar
     */
    void dadosFinaisDaVenda() {
        if(!textFieldNomePassageiro.getText().isEmpty())
            dadosDoPassageiro.setNome(textFieldNomePassageiro.getText());

        if(!textFieldDataNascimento.getText().isEmpty())
            dadosDoPassageiro.setDtNascimento(LocalDate.parse(textFieldDataNascimento.getText(), format));

        if(CPFValidator.validateCPF(textFieldCPFPassageiro.getText())) {
            dadosDoPassageiro.setCPF(textFieldCPFPassageiro.getText());
        } else
            JOptionPane.showMessageDialog(null, "O CPF informado é inválido");

        dadosDoPassageiro.setIdViagem(dadosDaViagemEscolhida.getIdViagem());
        dadosDoPassageiro.setOrigem(dadosDaViagemEscolhida.getOrigem());
        dadosDoPassageiro.setDestino(dadosDaViagemEscolhida.getDestino());

        // Armazenando os dados da venda
        dadosDaVenda.setIdViagem(dadosDaViagemEscolhida.getIdViagem());
        dadosDaVenda.setDtVenda(new Timestamp(System.currentTimeMillis()));
        dadosDaVenda.setBilhete(dadosDaVenda.getDtVenda().getTime());
        dadosDaVenda.setTarifa(calc.getTarifa());
        dadosDaVenda.setSeguro(calc.getSeguro());
        dadosDaVenda.setValorTotal(calc.getPrecoTotal());
        dadosDaVenda.setOpcaoPagamento(tapPane.getSelectionModel().getSelectedItem().getText());
        dadosDaVenda.setStatus("EM VIGOR");
    }

    void imprimirPassagem() throws Exception {

        // Trabalhamos com uma URL para poder definir o local onde se encontra o .jasper
//        URL url = getClass().getResource("/br/com/passabus/relatorios/JAVAFXFXMLRelatórioPassagem.jasper");

//         Caminho do arquivo de imagem dentro do classpath
        InputStream logoStream = getClass().getResourceAsStream("/br/com/passabus/view/imgs/logo.png");
        InputStream mainStream = getClass().getResourceAsStream("/br/com/passabus/view/imgs/mainbus.png");

        if (logoStream == null) {
            throw new RuntimeException("Imagem não encontrada: logo.png");
        }

        // Criamos um mapa de parâmetros para passar ao relatório
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("logo", logoStream); // Passa a imagem como parâmetro
        parameters.put("mainbus", mainStream);


        InputStream jasperStream = getClass().getResourceAsStream("/br/com/passabus/relatorios/JAVAFXFXMLRelatórioPassagem.jasper");

        if (jasperStream == null) {
            throw new JRException("Arquivo do relatório não encontrado!");
        }


        // O objeto JasperReport serve para carregar o arquivo do relatório.jarper
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        // JasperPrint irá buscar os valores que serão passados para a impressão no banco de dados
        // null: caso não existam filtros
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ConnectionFactory.createConnectionToMySQL());

        // JasperViewer serve para poder exibir o relatório e carregá-lo em uma página visualizável
        // false: serve para não deixar fechar a aplicação principal
        JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
        jasperViewer.setVisible(true);
    }

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
