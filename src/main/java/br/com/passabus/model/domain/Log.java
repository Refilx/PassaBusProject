package br.com.passabus.model.domain;

import java.sql.Timestamp;

/**
 * Essa é a Classe model do Log de Usuário, contendo todos os atributos e métodos sobre o Log de Usuários
 * @author Bruno Sousa
 */
public class Log extends Usuario{

    private int idLog;
    private Timestamp dtLogin;
    private Timestamp dtLogout;

    public Log() { }

    public Log(int idLog, int idUser, String username, Timestamp dtLogin, Timestamp dtLogout) {
        super(idUser, username);
        this.idLog = idLog;
        this.dtLogin = dtLogin;
        this.dtLogout = dtLogout;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public Timestamp getDtLogin() {
        return dtLogin;
    }

    public void setDtLogin(Timestamp dtLogin) {
        this.dtLogin = dtLogin;
    }

    public Timestamp getDtLogout() {
        return dtLogout;
    }

    public void setDtLogout(Timestamp dtLogout) {
        this.dtLogout = dtLogout;
    }
}
