package model.domain;

/**
 * Essa é a Classe model do Pagamento, contendo todos os atributos e métodos sobre o Pagamento
 * @author Bruno Sousa
 */
public class Pagamento {

    private int idPagamento;
    private String formaPagamento;

    public Pagamento() { }

    public Pagamento(int idPagamento, String formaPagamento) {
        this.idPagamento = idPagamento;
        this.formaPagamento = formaPagamento;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}
