package br.com.passabus.model.domain;

import java.util.Date;

/**
 * Essa é a Classe model de Pessoa, contendo todos os atributos e métodos sobre Pessoa
 * @author Bruno Sousa
 */
public class Pessoa {

    private int idPessoa;
    private String nome;
    private String CPF;
    private String telefone;
    private Date dtNascimento;
    private String email;

    public Pessoa() { }

    public Pessoa(int idPessoa, String nome, String CPF, String telefone, Date dtNascimento, String email) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.CPF = CPF;
        this.telefone = telefone;
        this.dtNascimento = dtNascimento;
        this.email = email;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
