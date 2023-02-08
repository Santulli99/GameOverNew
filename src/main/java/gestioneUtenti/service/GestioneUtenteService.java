package gestioneUtenti.service;

import model.entity.Account;
import model.entity.Address;
import model.entity.DataClient;

import java.util.ArrayList;

/**
 * interfaccia per i metodi del sottosistema GestioneUtente
 *
 * @author Andrea Serpico
 */
public interface GestioneUtenteService {


    /**
     * firma del metodo che implementa la modifica dell'indirizzo di un utente
     *
     * @param address oggetto della classe Address che contiene le modifiche da applicare
     * @return un boolean indica la riuscita dell'operazione
     */
    boolean ModificaDatiIndirizzo(Address address);

    /**
     * firma del metodo che implementa la modifica dei dati anagrafici di un utente
     *
     * @param account oggetto della classe Account su cui si effettua la modifica dei dati anagrafici
     * @param dataClient oggetto della classe DataClient che contiene i dati dell'account da modificare
     * @return un boolean indica la riuscita dell'operazione
     */
    boolean ModificaDatiAnagrafici(DataClient dataClient, Account account);

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
     * firma del metodo che restituisce i dati dell'account con dati anagrafici e indirizzo
     *
     * @param account oggetto della classe Account che contiene i dati dell'account da estrarre
     * @return oggetto della classe Account che contiene i dati di un utente
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
     * @param email String che contiene l'email dell'account da estrarre
     * @return oggetto della classe Account
     */
    Account getAccountEmail(String email);

    /**
     * firma del metodo che restituisce i dati dell'account dato il numero di telefono
     *
     * @param numero String che contiene il numero di  telefono dell'account da estrarre
     * @return oggetto della classe DataClient contenente i dati anagrafici dell'account
     */
    DataClient getDataClientTel(String numero);

    /**
     * firma del metodo che restituisce i dati dell'account dato il codice fiscale
     *
     * @param cf String che contiene il codice fiscale  dell'account da estrarre
     * @return oggetto della classe DataClient contenente i dati anagrafici dell'account
     */
    DataClient getDataClientCf(String cf);


}
