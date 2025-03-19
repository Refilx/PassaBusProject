package br.com.passabus.model.util;


/**
 * Classe que calcula todos os valores que envolvem a passagem, seja venda ou cancelamento
 * @author Bruno Sousa da Silva
 */
public class CalculadoraPassagem {

    private final double tarifa = 5.00; // Tarifa fixa
    private double custoPorKm; // Custo por quilômetro
    private final double seguro = 2.00; // Custo por seguro do passageiro
    private final double txCancelamento = 0.05; // A empresa pode cobrar até 5% do valor da passagem
    private double precoTotal;
    private double troco;

    /**
     * O método calcula o preço da passagem ao realizar uma venda
     * @param distanciaKm
     * @return preço total da passagem
     */
    public double calcularPrecoPassagem(double distanciaKm, String classe) {

        if(classe.equals("CONVENCIONAL")) {
            custoPorKm = 0.30;
        }
        else if(classe.equals("EXECUTIVO")) {
            custoPorKm = 0.45;
        }

        precoTotal = tarifa + seguro + (distanciaKm * custoPorKm);

        return precoTotal;
    }

    /**
     * O método calcula o troco durante o processo de venda da passagem
     * @param valorPago
     * @return troco do passageiro
     */
    public boolean calculaTroco(double valorPago) {
        if(valorPago >= this.precoTotal) {
            this.troco = valorPago - this.precoTotal;
            return true;
        }
        else
            return false;
    }

    /**
     * Calcula a preço a pagar para realizar o cancelamento da passagem
     * @param precoPago
     * @return preço para cancelar
     */
    public double calculaCancelamento(double precoPago) {
        return (precoPago * txCancelamento);
    }

    public double getTxCancelamento() {
        return txCancelamento;
    }

    public double getTarifa() {
        return tarifa;
    }

    public double getCustoPorKm() {
        return custoPorKm;
    }

    public double getSeguro() {
        return seguro;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public double getTroco() {
        return troco;
    }

    public static void main(String[] args) {
//        double distancia = 200.0; // Exemplo: 200 km de distância
//        double preco = calcularPrecoPassagem(distancia);
//        System.out.printf("O preço da passagem para %.2f km é R$ %.2f%n", distancia, preco);
    }
}

