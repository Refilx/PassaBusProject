package br.com.passabus.model.validator;

import br.com.passabus.model.dao.VerifyDAO;

import javax.swing.*;

public class LoginValidator {

    public LoginValidator(){

    }

    public boolean resultVerify(String user, String pass) {

        boolean resultVerify = false;

        //Verificamos se os campos de usuário e senha estão devidamente preenchidos
        if(!user.isBlank() && !pass.isBlank()){
            VerifyDAO verifyDAO= new VerifyDAO();

            //Verificação se o usuário ou a senha fornecida estão corretos
            resultVerify = verifyDAO.verifyPass(user, pass);

            if(resultVerify){
                //Mensagem de login bem sucedido
                JOptionPane.showMessageDialog(null, "Login Realizado com Sucesso",
                        "", JOptionPane.INFORMATION_MESSAGE, null);
            }
            else{
                //Mensagem de erro no usuário/senha
                JOptionPane.showMessageDialog(null, "Usuário ou Senha Incorretos \nPor favor tente novamente",
                        "Erro ao tentar realizar login", JOptionPane.ERROR_MESSAGE, null);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Preencha todos os campos, por favor",
                    "Erro ao tentar realizar o login",JOptionPane.WARNING_MESSAGE, null);
        }

        return resultVerify;
    }
}
