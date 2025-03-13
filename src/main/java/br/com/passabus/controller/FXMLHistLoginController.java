package br.com.passabus.controller;

import br.com.passabus.model.dao.LogDAO;
import br.com.passabus.model.dao.ViagemDAO;
import br.com.passabus.model.domain.Log;
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

public class FXMLHistLoginController implements Initializable {

    @FXML
    private TableColumn<?, ?> tc_login;

    @FXML
    private TableColumn<?, ?> tc_logout;

    @FXML
    private TableColumn<?, ?> tc_username;

    @FXML
    private TableView<?> tvHistLogin;

    private ObservableList observableList;
    private LinkedList<Log> listaDeLogs = new LinkedList<>();

    void prepararListaTabela() {
        tc_login.setCellValueFactory(new PropertyValueFactory<>("dtLogin"));
        tc_logout.setCellValueFactory(new PropertyValueFactory<>("dtLogout"));
        tc_username.setCellValueFactory(new PropertyValueFactory<>("username"));

        // Pegando a lista de viagens do banco
        listaDeLogs =  new LogDAO().getLogs();

        // configurando o observable list com os dados da lista do banco
        observableList = FXCollections.observableList(listaDeLogs);

        // Configurando a tabela após a pesquisa
        tvHistLogin.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prepararListaTabela();
    }
}
