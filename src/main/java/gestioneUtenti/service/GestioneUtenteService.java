package gestioneUtenti.service;

import model.entity.Account;
import model.entity.Address;
import model.entity.DataClient;

import java.util.ArrayList;

/**
 * interfaccia per i metodi del sottosistema GestioneUtente implementata per rispettare il Facade Pattern
 *
 * @author Andrea Serpico
 */
public interface GestioneUtenteService {


    /**
     * firma del metodo che implementa la modifica dell'indirizzo di un utente
     *
     * @param address Account che contiene le modifiche da applicare
     * @return un boolean che indica se la modifica va a buon fine
     */
    boolean ModificaDatiIndirizzo(Address address);

    /**
     * firma del metodo che implementa la modifica dei dati anagrafici di un utente
     *
     * @param account Account che contiene le modifiche da applicare
     * @return un boolean che indica se la modifica va a buon fine
     */
    boolean ModificaDatiAnagrafici(DataClient dataClient, Account account);

    /**
     * firma del metodo che implementa la modifica dei dati account di un utente
     *
     * @param account Account che contiene le modifiche da applicare
     * @return un boolean che indica se la modifica va a buon fine
     */
    boolean ModificaDatiAccount(Account account);

    /**
     * firma del metodo che restituisce i dati dell'account
     *
     * @param account Account che contiene i dati dell'account da prendere
     * @return Account
     */
    Account getAccountDati(Account account);

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
     * @param email String che contiene l'email dell'account da prendere
     * @return Account con una specifico email
     */
    Account getAccountEmail(String email);

    /**
     * firma del metodo che restituisce i dati dell'account dato il numero di telefono
     *
     * @param numero String che contiene il telefono dell'account da prendere
     * @return DataClient dato uno specifico numero di telefono
     */
    DataClient getDataClientTel(String numero);

    /**
     * firma del metodo che restituisce i dati dell'account dato il codice fiscale
     *
     * @param cf String che contiene il codice fiscale  dell'account da prendere
     * @return DataClient dato uno specifico codice fiscale
     */
    DataClient getDataClientCf(String cf);


}
