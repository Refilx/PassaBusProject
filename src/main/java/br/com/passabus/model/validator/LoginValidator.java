package br.com.passabus.model.validator;

import br.com.passabus.model.dao.VerifyDAO;
import javafx.scene.control.Alert;

import javax.swing.*;

public class LoginValidator {

    public LoginValidator(){

    }

    public static boolean resultVerify(String user, String pass) {

        boolean resultVerify = false;

        //Verificamos se os campos de usuário e senha estão devidamente preenchidos
        if(!user.isBlank() && !pass.isBlank()){
            VerifyDAO verifyDAO= new VerifyDAO();

            //Verificação se o usuário ou a senha fornecida estão corretos
            resultVerify = verifyDAO.verifyPass(user, pass);

            if(resultVerify){
                //Mensagem de login bem sucedido
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login Realizado com Sucesso");
                alert.showAndWait();
            }
            else{
                //Mensagem de erro no usuário/senha
                Alert alert = new Alert(Alert.AlertType.WARNING, "Erro ao tentar realizar login\n\nUsuário ou Senha Incorretos \nPor favor tente novamente");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Erro ao tentar realizar o login\n\nPreencha todos os campos, por favor!");
            alert.showAndWait();
        }

        return resultVerify;
    }
}
