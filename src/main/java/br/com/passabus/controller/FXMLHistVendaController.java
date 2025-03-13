package br.com.passabus.controller;

import br.com.passabus.model.dao.VendaDAO;
import br.com.passabus.model.domain.Venda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class FXMLHistVendaController implements Initializable {


    @FXML
    private TableColumn<?, ?> tc_data;

    @FXML
    private TableColumn<?, ?> tc_bilhete;

    @FXML
    private TableColumn<?, ?> tc_status;

    @FXML
    private TableColumn<?, ?> tc_formaPagemento;

    @FXML
    private TableColumn<?, ?> tc_valor;
    @FXML
    private TableView<?> tableViewVendas;

    private ObservableList observableList;
    private LinkedList<Venda> listaDeVendas = new LinkedList<>();

    void prepararListaTabela() {
        tc_data.setCellValueFactory(new PropertyValueFactory<>("dtVenda"));
        tc_valor.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        tc_bilhete.setCellValueFactory(new PropertyValueFactory<>("bilhete"));
        tc_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tc_formaPagemento.setCellValueFactory(new PropertyValueFactory<>("opcaoPagamento"));

        // Pegando a lista de viagens do banco
        listaDeVendas =  new VendaDAO().getVendas();

        // configurando o observable list com os dados da lista do banco
        observableList = FXCollections.observableList(listaDeVendas);

        // Configurando a tabela após a pesquisa
        tableViewVendas.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepararListaTabela();
    }

}
