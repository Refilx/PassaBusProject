package br.com.passabus.model.dao;

import br.com.passabus.model.domain.Passageiro;
import br.com.passabus.model.factory.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe responsável por gerenciar o acesso ao banco de dados para operações relacionadas ao Passageiro.
 * Inclui métodos para inserir, atualizar, buscar e deletar registros de passageiros.
 *
 * @author Geovanna Alves
 */


public class PassageiroDAO {
    /*
    * c - ok
    * r - ok
    * u - ok
    * d - ok
    * */

    // create
    public void save(Passageiro passageiro) {
        String sql = "INSERT INTO passageiro (nome, CPF, dtNascimento, poltrona, origem, destino, dataviagem, idViagem) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstm.setString(1, passageiro.getNome());
            pstm.setString(2, passageiro.getCPF());
            pstm.setDate(3, Date.valueOf(passageiro.getDtNascimento()));
            pstm.setInt(4, passageiro.getPoltrona());
            pstm.setString(5, passageiro.getOrigem());
            pstm.setString(6, passageiro.getDestino());
            pstm.setDate(7, Date.valueOf(passageiro.getDataViagem()));
            pstm.setInt(8, passageiro.getIdViagem());

            pstm.executeUpdate();

            // recuperar o id gerado automaticamente
            rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                passageiro.setIdPassageiro(rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
                if (rs != null) rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // read
    public List<Passageiro> getPassageiros() {
        String sql = "SELECT * FROM passageiro";
        List<Passageiro> passageiros = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while (rset.next()) {
                Passageiro passageiro = new Passageiro();
                passageiro.setIdPassageiro(rset.getInt("idPassageiro"));
                passageiro.setNome(rset.getString("nome"));
                passageiro.setCPF(rset.getString("CPF"));
                passageiro.setDtNascimento(rset.getDate("dtNascimento").toLocalDate());
                passageiro.setPoltrona(rset.getInt("poltrona"));
                passageiro.setOrigem(rset.getString("origem"));
                passageiro.setDestino(rset.getString("destino"));
                passageiro.setDataViagem(rset.getDate("dataviagem").toLocalDate());
                passageiro.setIdViagem(rset.getInt("idViagem"));

                passageiros.add(passageiro);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
                if (rset != null) rset.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return passageiros;
    }

    // update
    public void update(Passageiro passageiro) {
        String sql = "UPDATE passageiro SET idViagem = ?, nome = ?, CPF = ?, dtNascimento = ?, poltrona = ?, origem = ?, destino = ?, dataviagem = ? WHERE idPassageiro = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, passageiro.getIdViagem());
            pstm.setString(2, passageiro.getNome());
            pstm.setString(3, passageiro.getCPF());
            pstm.setDate(4, Date.valueOf(passageiro.getDtNascimento()));
            pstm.setInt(5, passageiro.getPoltrona());
            pstm.setString(6, passageiro.getOrigem());
            pstm.setString(7, passageiro.getDestino());
            pstm.setDate(8, Date.valueOf(passageiro.getDataViagem()));
            pstm.setInt(9, passageiro.getIdPassageiro());

            int rowsUpdated = pstm.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Passageiro atualizado com sucesso!");
            } else {
                System.out.println("Nenhum passageiro encontrado com esse ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // deleta
    public void deleteById(int idPassageiro) {
        String sql = "DELETE FROM passageiro WHERE idPassageiro = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, idPassageiro);

            int rowsDeleted = pstm.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Passageiro deletado com sucesso!");
            } else {
                System.out.println("Nenhum passageiro encontrado com esse id;");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
