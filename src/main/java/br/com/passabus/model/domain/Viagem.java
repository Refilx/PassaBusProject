package br.com.passabus.model.domain;

import java.sql.Time;

/**
 * Essa é a Classe model de viagem, contendo todos os atributos e métodos sobre viagem
 * @author Bruno Sousa
 */
public class Viagem {

    private int idViagem;
    private int idViacao;
    private String origem;
    private String destino;
    private double distancia;
    private String linha;
    private String tipoViagem;
    private String classe;
    private Time horario;

    public Viagem() { }

    public Viagem(int idViagem, int idViacao, String origem, String destino, double distancia,
                  String linha, String tipoViagem, String classe, Time horario) {
        this.idViagem = idViagem;
        this.idViacao = idViacao;
        this.origem = origem;
        this.destino = destino;
        this.distancia = distancia;
        this.linha = linha;
        this.tipoViagem = tipoViagem;
        this.classe = classe;
        this.horario = horario;
    }

    public int getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(int idViagem) {
        this.idViagem = idViagem;
    }

    public int getIdViacao() {
        return idViacao;
    }

    public void setIdViacao(int idViacao) {
        this.idViacao = idViacao;
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

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getTipoViagem() {
        return tipoViagem;
    }

    public void setTipoViagem(String tipoViagem) {
        this.tipoViagem = tipoViagem;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }
}
