package br.com.passabus;

import br.com.passabus.util.TextFieldFormatter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import br.com.passabus.util.MaskTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLVendaScreenController implements Initializable {

    @FXML
    private Button btnProximo;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField textFieldData;

    @FXML
    private TextField textFieldDestino;

    @FXML
    private TextField textFieldOrigem;

    @Override
    public void initialize(URL location, ResourceBundle resourses) {

    }

    @FXML
    void tfDataKeyReleased(KeyEvent event) {
        TextFieldFormatter dataFormater = new TextFieldFormatter();
        dataFormater.setMask("##/##/####");
        dataFormater.setCaracteresValidos("0123456789");
        dataFormater.setTf(textFieldData);
        dataFormater.formatter();
    }

    @FXML
    void btnProximoMouseClicked(MouseEvent event) throws IOException {
        abrirTelaPopUp("FXMLPopUpSelecionarAssentosPassageiro");
    }

    @FXML
    void btnCancelarMouseClicked(MouseEvent event) {

    }

     void abrirTelaPopUp(String page) throws IOException {
        // Carrega o FXML do pop-up
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/passabus/"+page+".fxml"));
        Parent popupRoot = loader.load();

        // Cria um novo Stage para o pop-up
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);  // Modalidade que bloqueia todas as janelas ate a pop up ser fechada
        popupStage.setResizable(false);  // Impede redimensionamento

        // Define a cena e exibe
        Scene popupScene = new Scene(popupRoot);
        popupStage.setScene(popupScene);
        popupStage.showAndWait();  // Aguarda até o pop-up ser fechado
    }

    public void abrirDadosPassageiroPopUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/passabus/FXMLPopUpDadosPassageiro.fxml"));
            Parent root = loader.load();

            // Obtendo o controller da segunda pop-up para manipular campos depois
            FXMLPopUpDadosPassageiroController controller = loader.getController();
//            controller.inicializarDados("Texto Inicial");  // Exemplo de passagem de dados

            Stage segundaPopUp = new Stage();
            segundaPopUp.initModality(Modality.APPLICATION_MODAL);  // Modal: Bloqueia janela principal
            segundaPopUp.setScene(new Scene(root));
            segundaPopUp.showAndWait();  // Espera até a janela ser fechada
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void abrirSegundaPopUp() {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/passabus/view/SegundaPopUp.fxml"));
//            Parent root = loader.load();
//
//            // Obtendo o controller da segunda pop-up para manipular campos depois
//            SegundaPopUpController controller = loader.getController();
//            controller.inicializarDados("Texto Inicial");  // Exemplo de passagem de dados
//
//            Stage segundaPopUp = new Stage();
//            segundaPopUp.initModality(Modality.APPLICATION_MODAL);  // Modal: Bloqueia janela principal
//            segundaPopUp.setTitle("Segunda Pop-up");
//            segundaPopUp.setScene(new Scene(root));
//            segundaPopUp.showAndWait();  // Espera até a janela ser fechada
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
