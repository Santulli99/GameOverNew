package gestioneUtenti.service;

import model.entity.Account;
import model.entity.Address;
import model.entity.DataClient;

/**
 *  interfaccia per i metodi del sottosistema GestioneUtente implementata per rispettare il Facade Pattern
 *  @author Andrea Serpico
 */
public interface GestioneUtenteService {


    /**
     *firma del metodo che implementa la modifica dell'indirizzo di un utente
     * @param address Account che contiene le modifiche da applicare
     * @return un boolean che indica se la modifica va a buon fine
     */
    boolean ModificaDatiIndirizzo(Address address) ;

    /**
     *firma del metodo che implementa la modifica dei dati anagrafici di un utente
     * @param account Account che contiene le modifiche da applicare
     * @return un boolean che indica se la modifica va a buon fine
     */
    boolean ModificaDatiAnagrafici(DataClient dataClient,Account account) ;




}
