package Application_Logic.autenticazione.service;

import Application_Logic.entity.Account;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * interfaccia per i metodi del sottosistema Autenticazione
 *
 * @author Andrea Santulli
 */
public interface AutenticazioneService {

    /**
     * firma del metodo che implementa l'accesso al sito
     *
     * @param password String che contiene la password per accedere al sito web
     * @param email    String contiene l'email per accedere al sito
     * @return utente loggato
     * @throws SQLException
     */
    Account login(String email, String password) throws SQLException;

    /**
     * firma del metodo che implementa la verifica dell'account che si è loggato, se è l'admin del sito web
     *
     * @param account utente loggato
     * @return un boolean true mi indica che l'account è l'admin  del sito web
     */

    boolean verificaVenditore(Account account);

    /**
     * firma del metodo che implementa l'uscita dal sito web
     *
     * @param sessione L'oggetto della classe HttpSession su cui
     *                 sono memorizzate le informazioni dell'utente loggato
     */
    void logout(HttpSession sessione);

}
