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
    private int idPagamento;
    private double taxaEmbarque;
    private double tarifa;
    private double seguro;
    private double valorTotal;
    private Instant dtVenda;

    public Venda() { }

    public Venda(int idVenda, int idViagem, int idPassageiro, int idPagamento,
                 double taxaEmbarque, double tarifa, double seguro, double valorTotal,
                 Instant dtVenda) {
        this.idVenda = idVenda;
        this.idViagem = idViagem;
        this.idPassageiro = idPassageiro;
        this.idPagamento = idPagamento;
        this.taxaEmbarque = taxaEmbarque;
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

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public double getTaxaEmbarque() {
        return taxaEmbarque;
    }

    public void setTaxaEmbarque(double taxaEmbarque) {
        this.taxaEmbarque = taxaEmbarque;
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
