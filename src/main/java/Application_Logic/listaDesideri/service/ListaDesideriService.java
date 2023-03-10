package Application_Logic.listaDesideri.service;

import Application_Logic.entity.Account;
import Application_Logic.entity.ListaDesideri;
import Application_Logic.entity.Prodotto;

/**
 * interfaccia per i metodi del sottosistema ListaDesideri
 *
 * @author Andrea Santulli
 */
public interface ListaDesideriService {

    /**
     * Implementa la funzionalit√† dell'aggiunta di un prodotto alla lista desideri
     *
     * @param account  oggetto della classe Account usato  per aggiungere un prodotto alla lista desideri dell'account loggato in quel momento
     * @param prodotto oggetto della classe Prodotto che  contiene i dati del prodotto da aggiungere alla lista desideri
     * @return un boolean indica la riuscita dell'operazione
     */
    boolean aggiungiProdottoListaDesideri(Prodotto prodotto, Account account);

    /**
     * Implementa la funzionalit√† della rimozione di un prodotto dalla lista desideri dell'account loggato in quel momento
     *
     * @param account  oggetto della classe Account usato  per rimuovere un prodotto dalla lista desideri dell'account loggato in quel momento
     * @param prodotto oggetto della classe Prodotto che  contiene i dati del prodotto da rimuovere dalla lista desideri
     * @return un boolean indica la riuscita dell'operazione
     */
    boolean eliminaProdottoListaDesideri(Prodotto prodotto, Account account);

    /**
     * Implementa la funzionalit√† che restituisce la lista desideri relativa all'account loggato in quel momento
     *
     * @param account oggetto della classe Account usato per farmi restituire la lista desideri
     * @return oggetto della classe ListaDesideri contenente i dati relativi ai prodotti desiderati dall'account loggato in quel momento
     */
    ListaDesideri getListaDesideri(Account account);

}
