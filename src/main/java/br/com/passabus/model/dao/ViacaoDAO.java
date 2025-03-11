package br.com.passabus.model.dao;

import br.com.passabus.model.domain.Viacao;
import br.com.passabus.model.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar o acesso ao banco de dados para operações relacionadas à Viação.
 * Inclui métodos para inserir, atualizar, buscar e deletar registros de viações.
 *
 * @author Geovanna Alves
 */


public class ViacaoDAO {

    /*
    * c - ok
    * r - ok
    * u - ok
    * d - ok
    * */

    //create
    public void save(Viacao viacao) {
        String sql = "INSERT INTO viacao (nome, CNPJ, sede) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1, viacao.getNome());
            pstm.setString(2, viacao.getCNPJ());
            pstm.setString(3, viacao.getSede());

            pstm.executeUpdate();

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

    //read
    public List<Viacao> getViacoes() {
        List<Viacao> viacoes = new ArrayList<>();
        String sql = "SELECT * FROM viacao";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Viacao viacao = new Viacao();
                viacao.setIdViacao(rset.getInt("idViacao"));
                viacao.setNome(rset.getString("nome"));
                viacao.setCNPJ(rset.getString("CNPJ"));
                viacao.setSede(rset.getString("sede"));

                viacoes.add(viacao);
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

        return viacoes;
    }

    //update
    public void update(Viacao viacao) {
        String sql = "UPDATE viacao SET nome = ?, CNPJ = ?, sede = ? WHERE idViacao = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try  {
             conn = ConnectionFactory.createConnectionToMySQL();
             pstm = conn.prepareStatement(sql);

            pstm.setString(1, viacao.getNome());
            pstm.setString(2, viacao.getCNPJ());
            pstm.setString(3, viacao.getSede());
            pstm.setInt(4, viacao.getIdViacao());

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Delete
    public void deleteById(int idViacao) {
        String sql = "DELETE FROM viacao WHERE idViacao = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, idViacao);

            pstm.executeUpdate();

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
