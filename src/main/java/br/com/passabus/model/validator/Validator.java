package br.com.passabus.model.validator;

public class Validator {

    public static boolean validarCartaoCredito(String numeroCartao) {
        // Remover espaços e verificar se é um número válido
        numeroCartao = numeroCartao.replaceAll("-", "");

        if (!numeroCartao.matches("[0-9]+")) {
            return false; // Se não for um número válido, retorna falso
        }
        // Verificar o comprimento do cartão, geralmente entre 13 e 19 dígitos
        if (numeroCartao.length() < 13 || numeroCartao.length() > 19) {
            return false;
        }
        // Algoritmo de Luhn
        int soma = 0;
        boolean alternar = false;
        for (int i = numeroCartao.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroCartao.charAt(i));
            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            soma += digito;
            alternar = !alternar;
        }
        // Se a soma for múltiplo de 10, o número é válido
        return soma % 10 == 0;
    }
    public static void main(String[] args) {
        String numero = "4539-1488-0343-6467"; // Exemplo de número de cartão
        if (validarCartaoCredito(numero)) {
            System.out.println("Cartão válido");
        } else {
            System.out.println("Cartão inválido");
        }
    }
}
