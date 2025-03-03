package br.com.passabus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLVendaScreenController {

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancelar;

    @FXML
    void btnCadastrarMouseClicked(MouseEvent event) {

    }

    @FXML
    void btnCancelarMouseClicked(MouseEvent event) {

    }

    private void abrirTelaPopUp(Stage stageAtual) throws IOException {
        // Carrega o FXML do pop-up
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPopup.fxml"));
        AnchorPane popupRoot = loader.load();

        // Cria um novo Stage para o pop-up
        Stage popupStage = new Stage();
        popupStage.setTitle("Escolha das poltronas do passageiro");
        popupStage.initModality(Modality.WINDOW_MODAL);  // Modalidade que bloqueia a janela principal
        popupStage.setResizable(false);  // Impede redimensionamento

        // Define a cena e exibe
        Scene popupScene = new Scene(popupRoot);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();  // Aguarda até o pop-up ser fechado
    }

}
