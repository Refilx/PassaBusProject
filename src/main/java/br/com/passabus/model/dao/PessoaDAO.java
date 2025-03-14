package br.com.passabus.model.dao;

import br.com.passabus.model.domain.Passageiro;
import br.com.passabus.model.factory.ConnectionFactory;
import br.com.passabus.model.domain.Pessoa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar o acesso ao banco de dados para operações relacionadas à Pessoa.
 * Inclui métodos para inserir, atualizar, buscar e deletar registros de pessoas.
 *
 * @author Geovanna Alves
 */


public class PessoaDAO {

    /*
    * c - ok
    * r - ok
    * u - ok
    * d - ok
    * */

    //create
    public void save(Pessoa pessoa){

        String sql = "INSERT INTO pessoa(nome, CPF, telefone, dtNascimento, email) VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try{

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, pessoa.getNome());
            pstm.setString(2, pessoa.getCPF());
            pstm.setString(3, pessoa.getTelefone());
            pstm.setDate(4, Date.valueOf(pessoa.getDtNascimento()));
            pstm.setString(5, pessoa.getEmail());

            pstm.executeQuery();

            // recuperar o id gerado automaticamente
            rset = pstm.getGeneratedKeys();
            if(rset.next()) {
                pessoa.setIdPessoa(rset.getInt(1));
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {

            try{

                if(pstm != null){
                    pstm.close();
                }

                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    //read
    public List<Pessoa> getPessoa(){

        String sql = "SELECT * FROM pessoa";

        List<Pessoa> listaPessoa = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;


        ResultSet rset = null;

        try{

            conn = ConnectionFactory.createConnectionToMySQL();


            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();


            while(rset.next()){
                Pessoa pessoa = new Pessoa();


                pessoa.setIdPessoa(rset.getInt("idPessoa"));

                pessoa.setNome(rset.getString("nome"));

                pessoa.setCPF(rset.getString("CPF"));

                pessoa.setTelefone(rset.getString("telefone"));

                pessoa.setDtNascimento(rset.getDate("dtNascimento").toLocalDate());

                pessoa.setEmail(rset.getString("email"));

                listaPessoa.add(pessoa);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {

            try{

                if(rset != null){
                    rset.close();
                }

                if(pstm != null){
                    pstm.close();
                }

                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return listaPessoa;
    }

    //update
    public void update(Pessoa pessoa){

        String sql = "UPDATE pessoa SET nome = ?, CPF = ?, telefone = ?, dtNascimento = ?, email = ? WHERE idPessoa = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, pessoa.getNome());
            pstm.setString(2, pessoa.getCPF());
            pstm.setString(3, pessoa.getTelefone());
            pstm.setDate(4, Date.valueOf(pessoa.getDtNascimento()));
            pstm.setString(5, pessoa.getEmail());

            pstm.setInt(6, pessoa.getIdPessoa());

            pstm.execute();

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try{

                if(pstm != null){
                    pstm.close();
                }

                if(conn != null){
                    conn.close();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    //delete
    public void deleteByID(int idPessoa){

        String sql = "DELETE FROM pessoa WHERE idPessoa = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{

            conn = ConnectionFactory.createConnectionToMySQL();


            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, idPessoa);

            pstm.execute();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{

                if(pstm != null){
                    pstm.close();
                }

                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
