package br.com.passabus.model.domain;

/**
 * Essa é a Classe model de viagem, contendo todos os atributos e métodos sobre viagem
 * @author Bruno Sousa
 */
public class Viagem {

    private int idViagem;
    private int idViacao;
    private String linha;
    private String tipoViagem;
    private String classe;

    public Viagem() { }

    public Viagem(int idViagem, int idViacao, String linha, String tipoViagem, String classe) {
        this.idViagem = idViagem;
        this.idViacao = idViacao;
        this.linha = linha;
        this.tipoViagem = tipoViagem;
        this.classe = classe;
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
}
