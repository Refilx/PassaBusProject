package br.com.passabus.model.domain;

import java.time.Instant;

/**
 * Essa é a Classe model do Usuário, contendo todos os atributos e métodos sobre o Usuário
 * @author Bruno Sousa
 */
public class Usuario extends Pessoa{

    private int idUser;
    private String username;
    private String password;
    private String role;
    private Instant dtRegistro;

    public Usuario() { }

    public Usuario(int idUser, String username) {
        this.idUser = idUser;
        this.username = username;
    }

    public Usuario(int idUser, String username, String password, String role, Instant dtRegistro) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.role = role;
        this.dtRegistro = dtRegistro;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
}
