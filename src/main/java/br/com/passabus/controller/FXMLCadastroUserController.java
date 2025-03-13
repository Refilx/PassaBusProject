package br.com.passabus.controller;

import br.com.passabus.model.dao.PessoaDAO;
import br.com.passabus.model.dao.UsuarioDAO;
import br.com.passabus.model.domain.Usuario;
import br.com.passabus.model.util.TextFieldFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FXMLCadastroUserController {

    @FXML
    private ToggleGroup TipoUsuario;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Pane cadastroUserScreen;

    @FXML
    private PasswordField passFieldConfirmSenha;

    @FXML
    private PasswordField passFieldSenha;

    @FXML
    private RadioButton rb_admin;

    @FXML
    private RadioButton rb_gerente;

    @FXML
    private RadioButton rb_operador;

    @FXML
    private TextField textFieldCPF;

    @FXML
    private TextField textFieldDtNascimento;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private TextField textFieldNome;

    @FXML
    private TextField textFieldTelefone;

    @FXML
    private TextField textFieldUsername;

    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static Usuario usuario = new Usuario();
    private static PessoaDAO pessoaDAO = new PessoaDAO();
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    void tfCPFKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("###.###.###-##");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldCPF);
        tff.formatter();
    }

    @FXML
    void tfDataKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("##/##/####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldDtNascimento);
        tff.formatter();
    }

    @FXML
    void textFieldTelefoneOnKeyReleased(KeyEvent event) {
        TextFieldFormatter tff = new TextFieldFormatter();
        tff.setMask("(##) #####-####");
        tff.setCaracteresValidos("0123456789");
        tff.setTf(textFieldTelefone);
        tff.formatter();
    }

    @FXML
    void btnCadastrarMouseClicked(MouseEvent event) {
        if(
                !textFieldNome.getText().isEmpty() &&
                        !textFieldEmail.getText().isEmpty() &&
                        !textFieldUsername.getText().isEmpty() &&
                        !textFieldTelefone.getText().isEmpty() &&
                        !textFieldCPF.getText().isEmpty() &&
                        !textFieldDtNascimento.getText().isEmpty() &&
                        !passFieldSenha.getText().isEmpty() &&
                        !passFieldConfirmSenha.getText().isEmpty() &&
                        (rb_admin.isSelected() || rb_gerente.isSelected() || rb_operador.isSelected())
        ) {

            if(passFieldSenha.getText().equals(passFieldConfirmSenha.getText())) {

                int opcao = JOptionPane.showOptionDialog(null, "Tem certeza que deseja cadastrar um novo usuário?", "Confirmação final",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim", "Não"}, 0);

                if(opcao == 0) {
                    usuario.setNome(textFieldNome.getText());
                    usuario.setCPF(textFieldCPF.getText());
                    usuario.setDtNascimento(LocalDate.parse(textFieldDtNascimento.getText(), format));
                    usuario.setEmail(textFieldEmail.getText());
                    usuario.setTelefone(textFieldTelefone.getText());

                    usuario.setUsername(textFieldUsername.getText());
                    usuario.setPassword(passFieldSenha.getText());
                    if (rb_operador.isSelected())
                        usuario.setRole("Operador");
                    else if (rb_gerente.isSelected())
                        usuario.setRole("Gerente");
                    else if (rb_admin.isSelected())
                        usuario.setRole("Administrador");

                    pessoaDAO.save(usuario);

                    usuario.setIdPessoa(pessoaDAO.getIdUltimaPessoa());
                    usuarioDAO.save(usuario);

                    textFieldNome.setText(null);
                    textFieldUsername.setText(null);
                    textFieldCPF.setText(null);
                    textFieldDtNascimento.setText(null);
                    textFieldEmail.setText(null);
                    textFieldTelefone.setText(null);
                    passFieldSenha.setText(null);
                    passFieldConfirmSenha.setText(null);
                } else
                    JOptionPane.showMessageDialog(null, "O usuário NÃO foi cadastrado!");

            } else
                JOptionPane.showMessageDialog(null, "As senhas estão diferentes!");

        } else
            JOptionPane.showMessageDialog(null, "Preencha todos os campos, por favor!");

    }

    @FXML
    void btnCancelarMouseClicked(MouseEvent event) {
        textFieldNome.setText(null);
        textFieldUsername.setText(null);
        textFieldCPF.setText(null);
        textFieldDtNascimento.setText(null);
        textFieldEmail.setText(null);
        textFieldTelefone.setText(null);
        passFieldSenha.setText(null);
        passFieldConfirmSenha.setText(null);
    }
}
