package br.com.passabus.model.domain;

import java.time.Instant;

/**
 * Essa é a Classe model de Venda, contendo todos os atributos e métodos sobre a Venda
 * @author Bruno Sousa
 */
public class Venda {

    private int idVenda;
    private int idViagem;
    private int idPassageiro;
    private double tarifa;
    private double seguro;
    private double valorTotal;
    private String opcaoPagamento;
    private String status;
    private Instant dtVenda;

    public Venda() { }

    public Venda(int idVenda, int idViagem, int idPassageiro, String status, String opcaoPagamento,
                 double tarifa, double seguro, double valorTotal, Instant dtVenda) {
        this.idVenda = idVenda;
        this.idViagem = idViagem;
        this.idPassageiro = idPassageiro;
        this.status = status;
        this.opcaoPagamento = opcaoPagamento;
        this.tarifa = tarifa;
        this.seguro = seguro;
        this.valorTotal = valorTotal;
        this.dtVenda = dtVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdViagem() {
        return idViagem;
    }

    public void setIdViagem(int idViagem) {
        this.idViagem = idViagem;
    }

    public int getIdPassageiro() {
        return idPassageiro;
    }

    public void setIdPassageiro(int idPassageiro) {
        this.idPassageiro = idPassageiro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpcaoPagamento() {
        return opcaoPagamento;
    }

    public void setOpcaoPagamento(String opcaoPagamento) {
        this.opcaoPagamento = opcaoPagamento;
    }

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public double getSeguro() {
        return seguro;
    }

    public void setSeguro(double seguro) {
        this.seguro = seguro;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Instant getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Instant dtVenda) {
        this.dtVenda = dtVenda;
    }
}
