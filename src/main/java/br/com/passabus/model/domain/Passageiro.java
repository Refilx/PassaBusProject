package br.com.passabus.model.domain;

import java.sql.Date;

/**
 * Essa é a Classe model do Passageiro, contendo todos os atributos e métodos sobre o Passageiro
 * @author Bruno Sousa
 */
public class Passageiro extends Pessoa{

    private int idPassageiro;
    private int idViagem;
    private int poltrona;
    private String origem;
    private String destino;
    private Date dataViagem;

    public Passageiro() { }

    public Passageiro(int idPassageiro, int idViagem, int poltrona, String origem, String destino, Date dataViagem) {
        this.idPassageiro = idPassageiro;
        this.idViagem = idViagem;
        this.poltrona = poltrona;
        this.origem = origem;
        this.destino = destino;
        this.dataViagem = dataViagem;
    }

    public int getIdPassageiro() {
        return idPassageiro;
    }

    public void setIdPassageiro(int idPassageiro) {
        this.idPassageiro = idPassageiro;
    }

    public int getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(int idViagem) {
        this.idViagem = idViagem;
    }

    public int getPoltrona() {
        return poltrona;
    }

    public void setPoltrona(int poltrona) {
        this.poltrona = poltrona;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getDataViagem() {
        return dataViagem;
    }

    public void setDataViagem(Date dataViagem) {
        this.dataViagem = dataViagem;
    }
}
