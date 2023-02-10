package gestioneUtenti.service;

import model.entity.Account;

import java.util.ArrayList;

/**
 * interfaccia per i metodi del sottosistema GestioneUtente
 *
 * @author Andrea Serpico
 */
public interface GestioneUtenteService {




    /**
     * firma del metodo che implementa la modifica dei dati account di un utente
     *
     * @param account oggetto della classe Account che contiene le modifiche da applicare
     * @return un boolean indica la riuscita dell'operazione
     */
    boolean ModificaDatiAccount(Account account);
    /**
     * firma del metodo che implementa la modifica della password dell'account di un utente
     *
     * @param account oggetto della classe Account che contiene le modifiche da applicare
     * @return un boolean indica la riuscita dell'operazione
     */
    boolean ModificaPasswordAccount(Account account);

    /**
     * firma del metodo che restituisce i dati dell'account
     *
     * @param id_account int che contiene l'id dell'account da prendere
     * @return Account con uno specifico id
     */

    Account getAccount(int id_account);

    /**
     * firma del metodo che restituisce tutti gli account
     *
     * @return ArrayList di account
     */
    ArrayList<Account> getAllAccount();

    /**
     * firma del metodo che restituisce gli account data l'email
     *
     * @param email String che contiene l'email dell'account da estrarre
     * @return oggetto della classe Account
     */
    Account getAccountEmail(String email);





}
