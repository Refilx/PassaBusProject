package br.com.passabus.model.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import javax.swing.*;
import java.util.UUID;

public class Email {

    private String recoveryCode;

    public void sendMailTo(String destinatario) {
        // Dependências necessárias: commons-email-1.5 e mail-1.6.2

        String[] code = generateUniqueCode().split("-");
        recoveryCode = code[1]+"-"+code[2]+"-"+code[3];

        String msg = "Recebemos uma solicitação para recuperar a senha da sua conta.\n" +
                "Favor usar o código %s somente válido enquanto a tela de inserção do código estiver aberta.\n".formatted(recoveryCode) +
                "Se você não solicitou a recuperação da sua senha, favor ignorar essa mensagem.\n\n" +
                "---------------\n" +
                "PassaBus Administration";

        String appMail = "passabus.official@gmail.com";
        String appMailPass = "akql uukw epmm qosn"; // Senha de app gerada pelo google

        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);  // Porta 587 para STARTTLS
        email.setSSLOnConnect(false);  // Desabilitar SSL direto
        email.setStartTLSEnabled(true); // Habilitar STARTTLS
        email.setAuthenticator(new DefaultAuthenticator(appMail, appMailPass));

        try {
            email.setFrom(appMail);
            email.setSubject("Recuperação de senha | PassaBus | Não Responda");
            email.setMsg(msg);
            email.addTo(destinatario);
            email.send();
            JOptionPane.showMessageDialog(null, "Email enviado com sucesso!\nFavor confira sua caixa de entrada!",
                    "E-mail de recuperação de senha enviado", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Gerar um código único
    public static String generateUniqueCode() {
        UUID uniqueKey = UUID.randomUUID(); // Gera um UUID aleatório
        return uniqueKey.toString(); // Retorna o código como uma string
    }

    public String getRecoveryCode() {
        return recoveryCode;
    }
}
