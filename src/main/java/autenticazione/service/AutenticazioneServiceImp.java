package autenticazione.service;

import model.dao.account.SqlAccountDao;
import model.entity.Account;

import javax.servlet.http.HttpSession;

import java.sql.SQLException;


/**
 * implementa la classe che esplicita i metodi definiti nell'interfaccia di autenticazione
 *
 * @author Andrea Santulli
 */

public class AutenticazioneServiceImp implements AutenticazioneService {
    private SqlAccountDao sqlAccountDao = null;


    public AutenticazioneServiceImp() {
        sqlAccountDao = new SqlAccountDao();
    }

    public AutenticazioneServiceImp(SqlAccountDao sqlAccountDao) {
        this.sqlAccountDao = sqlAccountDao;
    }


    /**
     * Implementa la funzionalità per effettuare l'accesso al sito web.
     *
     * @param password String che contiene la password per accedere al sito web
     * @param email    String contiene l'email per accedere al sito
     * @return utente loggato
     */

    @Override
    public Account login(String email, String password) {

        Account account = new Account();
        try {
            account = sqlAccountDao.searchAccountIdWithDataClientandAndressLogin(password, email);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    /**
     * Implementa la funzionalità di verifica dell'account che si è loggato, se è l'admin del sito web
     *
     * @param account utente loggato
     * @return un boolean true mi indica che l'account è l'admin  del sito web
     */

    @Override
    public boolean verificaAdmin(Account account) {

        return account.isAdmin();
    }

    /**
     * Implementa la funzionalità per  l'uscita dal sito web
     *
     * @param sessione rappresenta l'oggetto della classe HttpSession su cui
     *                 sono memorizzate le informazioni dell'utente loggato
     */

    @Override
    public void logout(HttpSession sessione) {

        sessione.invalidate();

    }
}
