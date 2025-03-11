package br.com.passabus.model.domain;

/**
 * Essa é a Classe model Viacao, contendo todos os atributos e métodos sobre a Viacao
 * @author Bruno Sousa
 */
public class Viacao {

    private int idViacao;
    private String nome;
    private String CNPJ;
    private String sede;

    public Viacao() { }

    public Viacao(int idViacao, String nome, String CNPJ, String sede) {
        this.idViacao = idViacao;
        this.nome = nome;
        this.CNPJ = CNPJ;
        this.sede = sede;
    }

    public int getIdViacao() {
        return idViacao;
    }

    public void setIdViacao(int idViacao) {
        this.idViacao = idViacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }
}
