package br.com.passabus.model.dao;

import br.com.passabus.model.factory.ConnectionFactory;
import br.com.passabus.model.domain.Log;
import br.com.passabus.model.domain.Usuario;
import org.jasypt.util.password.BasicPasswordEncryptor;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Essa Classe faz a verificações de dados de acordo com o que está armazenado no banco de dados
 * @author Bruno Sousa da Silva
 */
public class VerifyDAO {

    /**
     *  O método verifica se existe um username no banco de dados identico ao informado no parâmetro
     * @param username
     * @return
     */
    public boolean verifyUsername(String username) {

        String sql = "SELECT username FROM usuario";

        //
        boolean resultadoVerify = true;

        Connection conn = null;

        //
        PreparedStatement pstm = null;

        //
        ResultSet rset = null;

        try{
            //
            conn = ConnectionFactory.createConnectionToMySQL();

            //
            pstm = conn.prepareStatement(sql);

            //
            rset = pstm.executeQuery();

            //
            while (rset.next()){
                //
                String userDoBanco = rset.getString("username");

                //
                if(username.equalsIgnoreCase(userDoBanco)){
                    resultadoVerify = false;
                    break;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                //
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

        return resultadoVerify;
    }

    /**
     * O método abaixo verifica se a role do ultimo usuário que realizou o login é um Administrador
     * @return
     */
    public boolean verifySuperUser() {

        String sql = "SELECT * FROM ultimo_logado";

        boolean resultadoVerify = false;

        Connection conn = null;

        PreparedStatement pstm = null;

        ResultSet rset = null;

        try{
            //
            conn = ConnectionFactory.createConnectionToMySQL();

            //
            pstm = conn.prepareStatement(sql);

            //
            rset = pstm.executeQuery();

            //
            rset.next();

            //
            String role = rset.getString("role");

            if(role.equalsIgnoreCase("Administrador")){
                resultadoVerify = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try{
                //
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

        return resultadoVerify;
    }

    /**
     *  O método verifica se existe um CPF no banco de dados identico ao informado no parâmetro
     *  Para não permitir o cadastro de pessoas com o CPF igual, por isso retorna false (caso encontre)
     * @param cpf
     * @return
     */
    public boolean verifyCPF(String cpf) {

        String sql = "SELECT CPF FROM pessoa";

        //
        boolean resultadoVerify = true;

        Connection conn = null;

        //
        PreparedStatement pstm = null;

        //
        ResultSet rset = null;

        try{
            //
            conn = ConnectionFactory.createConnectionToMySQL();

            //
            pstm = conn.prepareStatement(sql);

            //
            rset = pstm.executeQuery();

            //
            while (rset.next()){
                //
                String cpfDoBanco = rset.getString("CPF");

                //
                if(cpf.equalsIgnoreCase(cpfDoBanco)){
                    resultadoVerify = false;
                    break;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                //
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

        return resultadoVerify;

    }

    /**
     * O método abaixo valida a senha que será passada pelo usuário ao tentar fazer login
     * @param user
     * @param pass
     * @return
     */
    public boolean verifyPass(String user, String pass){

        String sql = "SELECT idUsuario, password FROM usuario WHERE username = ?";

        boolean resultadoValidacao = false;

        Connection conn = null;

        PreparedStatement pstm = null;

        // Classe que vai recuperar os dados do banco.  *** SELECT ***
        ResultSet rset = null;

        try{
            //Cria conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();

            //Criamos uma PreparedStatement para executar uma query
            pstm = conn.prepareStatement(sql);

            //Atribui a query o username que será buscado no banco
            pstm.setString(1, user);

            rset = pstm.executeQuery();

            while(rset.next()){

                //Criamos um usuário
                Usuario usuario = new Usuario();

                //Inicializamos o encriptador
                BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();

                //Neste ponto pegamos a senha do respectivo usuário cadatrado no banco de dados
                usuario.setPassword(rset.getString("password"));

                /**
                 Verificamos se a senha digitada pelo usuário na tela de login (que será passada como parâmetro do método)
                 utilizando a classe de criptografia de dados para comparar com  senha criptografada do usuário guardada no banco de dados

                 se a senha estiver correta, validamos o login
                 e armazenamos o usuário que fez login pegando o id do respectivo usuário
                 */
                if(passwordEncryptor.checkPassword(pass, usuario.getPassword())){
                    //Armazenamos o id do respectivo usuário
                    usuario.setIdUsuario(rset.getInt("idUsuario"));

                    //Criamos neste ponto as classes que irão armazenar os dados do usuário que está acessando
                    LogDAO logDAO = new LogDAO();
                    Log log = new Log();

                    //Armazenamos o idUser e a data do log no objeto log e salvamos no banco de dados
                    log.setIdUsuario(usuario.getIdUsuario());

                    logDAO.saveLogin(log);

                    resultadoValidacao = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta!");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {

            try{
                //
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
        return resultadoValidacao;
    }

    /**
     * Verifica se o bilhete informado foi vendido e está em vigor
     * @return true (se houver um bilhete igual e 'EM VIGOR' no banco) e false (se NÃO houver um bilhete igual e 'EM VIGOR' no banco)
     */
    public static boolean verifyBilhete(long bilhete) {

        String sql = "SELECT bilhete FROM venda WHERE status = 'EM VIGOR' AND bilhete = ?";

        boolean resultVerify = false;

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);
            pstm.setLong(1, bilhete);

            rset = pstm.executeQuery();

            if(rset.next()) {
                resultVerify = true;
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

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return resultVerify;
    }


    /**
     * Verifica se o email passado por parâmetro existe no banco de dados
     * @param email
     * @return true (se existir no banco) e false (se NÃO existir no banco)
     */
    public static boolean verifyEmail(String email) {
        String sql = "SELECT email FROM pessoa WHERE email = ?";

        boolean resultVerify = false;

        Connection conn =  null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);

            pstm.setString(1, email);

            rset = pstm.executeQuery();

            if(rset.next()) {
                resultVerify = true;
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

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return resultVerify;
    }

}
