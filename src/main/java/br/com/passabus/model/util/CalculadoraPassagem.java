package br.com.passabus.model.util;

public class CalculadoraPassagem {

    private double tarifa;
    private double custoPorKm;
    private double seguro;
    private double precoTotal;

    public double calcularPrecoPassagem(double distanciaKm) {
        tarifa = 5.00; // Tarifa inicial fixa
        custoPorKm = 0.30; // Custo por quilômetro
        seguro = 2.0; // Custo por quilômetro
        precoTotal = tarifa + seguro + (distanciaKm * custoPorKm);

        return precoTotal;
    }

    public static void main(String[] args) {
//        double distancia = 200.0; // Exemplo: 200 km de distância
//        double preco = calcularPrecoPassagem(distancia);
//        System.out.printf("O preço da passagem para %.2f km é R$ %.2f%n", distancia, preco);
    }
}

