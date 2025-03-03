package br.com.passabus;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FXMLHistVendaController {

    @FXML
    private TableColumn<?, ?> tableColumnData;

    @FXML
    private TableColumn<?, ?> tableColumnDestino;

    @FXML
    private TableColumn<?, ?> tableColumnOrigem;

    @FXML
    private TableColumn<?, ?> tableColumnValor;

    @FXML
    private TableView<?> tableViewVendas;

    void prepararListaTabela() {

    }

}
