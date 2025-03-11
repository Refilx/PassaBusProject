package br.com.passabus.model.domain;

import java.time.Instant;

/**
 * Essa é a Classe model do Usuário, contendo todos os atributos e métodos sobre o Usuário
 * @author Bruno Sousa
 */
public class Usuario extends Pessoa{

    private int idUsuario;
    private String username;
    private String password;
    private String role;
    private Instant dtRegistro;
    private String status;

    public Usuario() { }

    public Usuario(int idUsuario, String username) {
        this.idUsuario = idUsuario;
        this.username = username;
    }

    public Usuario(int idUsuario, String username, String password, String role, Instant dtRegistro) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.role = role;
        this.dtRegistro = dtRegistro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(Instant dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
