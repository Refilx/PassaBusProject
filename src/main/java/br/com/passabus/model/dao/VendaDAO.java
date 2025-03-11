package br.com.passabus.model.dao;

import br.com.passabus.model.domain.Venda;
import br.com.passabus.model.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar o acesso ao banco de dados para operações relacionadas à Venda.
 * Inclui métodos para inserir, atualizar, buscar e deletar registros de vendas.
 *
 * @author Geovanna Alves
 */


public class VendaDAO {

    /*
    * c - ok
    * r - ok
    * u - ok
    * d - ok
    * */

    //create

    public void save(Venda venda) {
        String sql = "INSERT INTO venda (idViagem, idPassageiro, opcaoPagamento, taxaEmbarque, tarifa, seguro, valorTotal, dtVenda) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, venda.getIdViagem());
            pstm.setInt(2, venda.getIdPassageiro());
            pstm.setInt(3, venda.getIdPagamento());
            pstm.setDouble(4, venda.getTaxaEmbarque());
            pstm.setDouble(5, venda.getTarifa());
            pstm.setDouble(6, venda.getSeguro());
            pstm.setDouble(7, venda.getValorTotal());
            pstm.setTimestamp(8, Timestamp.from(venda.getDtVenda()));

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(conn!=null){
                    conn.close();
                }
                if(pstm!=null){
                    pstm.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public List<Venda> getAll() {
        String sql = "SELECT * FROM venda";
        List<Venda> vendas = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Venda venda = new Venda();
                venda.setIdVenda(rset.getInt("idVenda"));
                venda.setIdViagem(rset.getInt("idViagem"));
                venda.setIdPassageiro(rset.getInt("idPassageiro"));
                venda.setIdPagamento(rset.getInt("opcaoPagamento")); //modificar
                venda.setTaxaEmbarque(rset.getDouble("taxaEmbarque"));
                venda.setTarifa(rset.getDouble("tarifa"));
                venda.setSeguro(rset.getDouble("seguro"));
                venda.setValorTotal(rset.getDouble("valorTotal"));
                venda.setDtVenda(rset.getTimestamp("dtVenda").toInstant());

                vendas.add(venda);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(conn!=null){
                    conn.close();
                }
                if(pstm!=null){
                    pstm.close();
                }
                if(rset!=null){
                    rset.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return vendas;
    }

    public void update(Venda venda) {
        String sql = "UPDATE venda SET idViagem = ?, idPassageiro = ?, opcaoPagamento = ?, taxaEmbarque = ?, tarifa = ?, seguro = ?, valorTotal = ?, dtVenda = ? WHERE idVenda = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, venda.getIdViagem());
            pstm.setInt(2, venda.getIdPassageiro());
            pstm.setInt(3, venda.getIdPagamento());
            pstm.setDouble(4, venda.getTaxaEmbarque());
            pstm.setDouble(5, venda.getTarifa());
            pstm.setDouble(6, venda.getSeguro());
            pstm.setDouble(7, venda.getValorTotal());
            pstm.setTimestamp(8, Timestamp.from(venda.getDtVenda()));
            pstm.setInt(9, venda.getIdVenda());

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn!=null){
                    conn.close();
                }
                if(pstm!=null){
                    pstm.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void deleteById(int idVenda) {
        String sql = "DELETE FROM venda WHERE idVenda = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, idVenda);
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn!=null){
                    conn.close();
                }
                if(pstm!=null){
                    pstm.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
