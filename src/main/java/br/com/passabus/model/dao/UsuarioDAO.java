package br.com.passabus.model.dao;

import br.com.passabus.model.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import br.com.passabus.model.factory.ConnectionFactory;
import org.jasypt.util.password.BasicPasswordEncryptor;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por gerenciar o acesso ao banco de dados para operações relacionadas ao Usuário.
 * Inclui métodos para inserir, atualizar, buscar e deletar registros de usuários.
 *
 * @author Geovanna Alves
 */
public class UsuarioDAO {
    /*
     * create - ok
     * retrieve - ok
     * update - ok
     * delete - ok
     */

    //create
    public void save (Usuario usuario){
        String sql = "INSERT into usuario(username, password, role, dtRegistro, status, idPessoa) VALUES(?, ?, ?, ?, ?, ?)";

        Connection conn = null;

        PreparedStatement pstm = null;

        try{
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

            String senhaCriptografada = passwordEncryptor.encryptPassword(usuario.getPassword());

            pstm.setString(1, usuario.getUsername());
            pstm.setString(2, senhaCriptografada);
            pstm.setString(3, usuario.getRole());
            pstm.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            pstm.setString(5, usuario.getStatus());

            pstm.setInt(6, usuario.getIdPessoa());


            pstm.execute();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public List<Usuario> getUsuarios(){

        String sql = "SELECT * FROM usuario";

        List<Usuario> listaUsuario = new ArrayList<Usuario>();

        Connection conn = null;

        PreparedStatement pstm = null;

        //Classe que vai recuperar os dados do banco. ***SELECT***
        ResultSet rset = null;

        try{
            //Cria uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();

            //Cria uma PreparedStatement para executar uma Query
            pstm = conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            //Enquanto houver um próximo dado para ser armazenado pelo ResultSet, os comandos serão executados
            while(rset.next()){
                Usuario usuario = new Usuario();

                //Recupera o username do usuário no banco de dados
                usuario.setUsername(rset.getString("username"));

                //Recupera a role do usuário no banco de dados
                usuario.setRole(rset.getString("role"));

                //Recupera o telefone do usuário no banco de dados
                usuario.setPassword(rset.getString("password"));

                //Adiciona o Usuário com todos os dados registrados à lista de Usuários
                listaUsuario.add(usuario);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {

            try{
                //Fecha as conexões que foram abertas com o banco de dados
                if(rset!=null){
                    rset.close();
                }

                if(pstm!=null){
                    pstm.close();
                }

                if(conn!=null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return listaUsuario;
    }

    public void update(Usuario usuario){

        String sql = "UPDATE usuario SET username = ?, password = ?, role = ?, status = ?" +
                "WHERE idUsuario = ?";

        Connection conn = null;

        PreparedStatement pstm = null;

        try{

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

            String senhaCriptografada = passwordEncryptor.encryptPassword(usuario.getPassword());

            pstm.setString(1, usuario.getUsername());
            pstm.setString(2, senhaCriptografada);
            pstm.setString(3, usuario.getRole());
            pstm.setString(4, usuario.getStatus());

            pstm.setInt(5, usuario.getIdUsuario());

            pstm.execute();

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try{

                if(pstm!=null){
                    pstm.close();
                }

                if(conn!=null){
                    conn.close();
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    //delete
    public void deleteByID(int idUsuario){

        String sql = "DELETE FROM usuario WHERE idUsuario = ?";

        Connection conn = null;

        PreparedStatement pstm = null;

        try{

            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setInt(1, idUsuario);

            pstm.execute();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(pstm!=null){
                    pstm.close();
                }

                if(conn!=null){
                    conn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }


}
