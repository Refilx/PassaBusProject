package br.com.passabus.model.aplication;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Bruno Sousa
 */
public class FXMLLoginScreenAplication extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/com/passabus/FXMLLoginScreen.fxml"));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tela de Login PassaBus (JavaFX)");
        stage.setResizable(false);
        stage.show();
        
    }

    public void trocarDeTela(Stage stageAtual, String novaTela) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(novaTela));
        Scene novaCena = new Scene(loader.load());

        stageAtual.close();

        Stage novoStage = new Stage();
        novoStage.setScene(novaCena);
        novoStage.centerOnScreen();
        novoStage.show();

//        stageAtual.setScene(novaCena);
//        stageAtual.centerOnScreen();
//        stageAtual.setTitle(null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
