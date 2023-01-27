package autenticazione.service;

import model.entity.Account;

import javax.servlet.http.HttpSession;
    /**
     * interfaccia per i metodi del sottosistema Autenticazione implementata per rispettare il Façade Pattern
     *
     * @author Andrea Santulli
    */
public interface AutenticazioneService {

    /**
     * firma del metodo che implementa l'accesso al sito
     *
     * @param password String che contiene la password per accedere al sito web
     * @param email String contiene l'email per accedere al sito
     * @return un Account
     */
    Account login(String email , String password);
        /**
         * firma del metodo che implementa la verifica dell'utente che accede al sito web
         *
         * @param account Account
         * @return un Boolean che indica se l'account è dell'admin
         */

    boolean verificaAdmin(Account account);

        /**
         * firma del metodo che implementa l'uscita dal sito web
         *
         * @param sessione HttpSession
         */
    void logout(HttpSession sessione);

}
