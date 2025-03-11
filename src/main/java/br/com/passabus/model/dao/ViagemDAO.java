package br.com.passabus.model.dao;

import br.com.passabus.model.domain.Viagem;
import br.com.passabus.model.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar o acesso ao banco de dados para operações relacionadas à Viagem.
 * Inclui métodos para inserir, atualizar, buscar e deletar registros de viagens.
 *
 * @author Geovanna Alves
 */

public class ViagemDAO {
    /*
    * c - ok
    * r - ok
    * u - ok
    * d - ok
    * */

    //create
    public void save(Viagem viagem) {
        String sql = "INSERT INTO viagem (idViacao, linha, tipoViagem, classe) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, viagem.getIdViacao());
            pstm.setString(2, viagem.getLinha());
            pstm.setString(3, viagem.getTipoViagem());
            pstm.setString(4, viagem.getClasse());

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

    // Read
    public List<Viagem> getViagens() {
        List<Viagem> viagens = new ArrayList<>();
        String sql = "SELECT * FROM viagem";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Viagem viagem = new Viagem();
                viagem.setIdViagem(rset.getInt("idViagem"));
                viagem.setIdViacao(rset.getInt("idViacao"));
                viagem.setLinha(rset.getString("linha"));
                viagem.setTipoViagem(rset.getString("tipoViagem"));
                viagem.setClasse(rset.getString("classe"));

                viagens.add(viagem);
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

        return viagens;
    }

    //update
    public void update(Viagem viagem) {
        String sql = "UPDATE viagem SET idViacao = ?, linha = ?, tipoViagem = ?, classe = ? WHERE idViagem = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, viagem.getIdViacao());
            pstm.setString(2, viagem.getLinha());
            pstm.setString(3, viagem.getTipoViagem());
            pstm.setString(4, viagem.getClasse());
            pstm.setInt(5, viagem.getIdViagem());

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

    // Delete
    public void deleteById(int idViagem) {
        String sql = "DELETE FROM viagem WHERE idViagem = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try  {

            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, idViagem);

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
