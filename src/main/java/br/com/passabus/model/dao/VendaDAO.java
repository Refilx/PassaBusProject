package br.com.passabus.model.dao;

import br.com.passabus.model.domain.Passageiro;
import br.com.passabus.model.domain.Venda;
import br.com.passabus.model.domain.Viagem;
import br.com.passabus.model.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
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
        String sql = "INSERT INTO venda (idViagem, idPassageiro, opcaoPagamento, tarifa, seguro, valorTotal, status, dtVenda, bilhete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, venda.getIdViagem());
            pstm.setInt(2, venda.getIdPassageiro());
            pstm.setString(3, venda.getOpcaoPagamento());
            pstm.setDouble(4, venda.getTarifa());
            pstm.setDouble(5, venda.getSeguro());
            pstm.setDouble(6, venda.getValorTotal());
            pstm.setString(7, venda.getStatus());
            pstm.setTimestamp(8, venda.getDtVenda());
            pstm.setLong(9, venda.getBilhete());

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

    public LinkedList<Venda> getVendas() {
        String sql = "SELECT * FROM venda";
        LinkedList<Venda> vendas = new LinkedList<>();

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
                venda.setOpcaoPagamento(rset.getString("opcaoPagamento")); //modificar
                venda.setStatus(rset.getString("status"));
                venda.setTarifa(rset.getDouble("tarifa"));
                venda.setSeguro(rset.getDouble("seguro"));
                venda.setValorTotal(rset.getDouble("valorTotal"));
                venda.setDtVenda(rset.getTimestamp("dtVenda"));
                venda.setBilhete(rset.getLong("bilhete"));

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

    public static String destinoPopular() {
        String sql = "SELECT destino FROM destinoPopular";

        String destino = "";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                destino = rset.getString("destino");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(conn != null)
                    conn.close();

                if(pstm != null)
                    pstm.close();

                if(rset != null)
                    rset.close();

            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return destino;
    }

    public static int quantidadeVendida() {
        String sql = "SELECT quantidadeVendida FROM quantidadeVendida";

        int quantidadeVendida = 0;

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {
                quantidadeVendida = rset.getInt("quantidadeVendida");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(conn != null)
                    conn.close();

                if(pstm != null)
                    pstm.close();

                if(rset != null)
                    rset.close();

            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return quantidadeVendida;
    }

    /**
     * O método_ executa um select no banco de dados que busca as poltronas que já foram compradas para uma viagem específica
     * @param idViagem
     * @return LinkedList contendo os números das poltronas compradas
     * @author Bruno Sousa
     */
    public LinkedList<Integer> getPoltronasCompradas(int idViagem, Date dataviagem) {

        String sql = "SELECT P.poltrona FROM venda V\n" +
                "JOIN passageiro P ON (V.idPassageiro = P.idPassageiro)\n" +
                "WHERE V.status = 'EM VIGOR' AND P.idViagem = ? AND P.dataviagem = ?";

        LinkedList<Integer> listaPoltronasCompradas = new LinkedList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, idViagem);
            pstm.setDate(2, dataviagem);

            rset = pstm.executeQuery();

            while (rset.next()) {
                listaPoltronasCompradas.add(rset.getInt("poltrona"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(conn != null)
                    conn.close();

                if(pstm != null)
                    pstm.close();

                if(rset != null)
                    rset.close();

            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return listaPoltronasCompradas;
    }

    public void getVendaParaCancelar(Passageiro p, Viagem v, Venda vd, long bilhete) {
        String sql = "SELECT P.*, V.*, VD.opcaoPagamento, VD.valorTotal FROM venda VD\n" +
                "JOIN passageiro P ON (VD.idPassageiro = P.idPassageiro)\n" +
                "JOIN viagem V ON (VD.idViagem = V.idViagem)\n" +
                "WHERE bilhete = ?;";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, bilhete);

            rset = pstm.executeQuery();

            while (rset.next()) {
                p.setNome(rset.getString("nome"));
                p.setOrigem(rset.getString("origem"));
                p.setDestino(rset.getString("destino"));
                p.setDataViagem(rset.getDate("dataviagem").toLocalDate());
                p.setPoltrona(rset.getInt("poltrona"));
                v.setHorario(rset.getTime("horario"));
                v.setLinha(rset.getString("linha"));
                vd.setOpcaoPagamento(rset.getString("opcaoPagamento"));
                vd.setValorTotal(rset.getDouble("valorTotal"));
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
    }

    /**
     * UPDATE para cancelar a passagem
     * @param bilhete
     */
    public void cancelarPassagem(long bilhete) {
        String sql = "UPDATE venda SET status = 'CANCELADA' WHERE bilhete = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setLong(1, bilhete);

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

    public void update(Venda venda) {
        String sql = "UPDATE venda SET idViagem = ?, idPassageiro = ?, opcaoPagamento = ?, status = ?, tarifa = ?, seguro = ?, valorTotal = ?, dtVenda = ?, bilhete = ? WHERE idVenda = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, venda.getIdViagem());
            pstm.setInt(2, venda.getIdPassageiro());
            pstm.setString(3, venda.getOpcaoPagamento());
            pstm.setString(4, venda.getStatus());
            pstm.setDouble(5, venda.getTarifa());
            pstm.setDouble(6, venda.getSeguro());
            pstm.setDouble(7, venda.getValorTotal());
            pstm.setTimestamp(8, venda.getDtVenda());
            pstm.setLong(9, venda.getBilhete());
            pstm.setInt(10, venda.getIdVenda());

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
