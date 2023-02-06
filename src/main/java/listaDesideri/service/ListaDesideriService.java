package listaDesideri.service;

import model.entity.Account;
import model.entity.ListaDesideri;
import model.entity.Prodotto;

import java.util.ArrayList;
/**
 * interfaccia per i metodi del sottosistema ListaDesideri
 *
 * @author Andrea Santulli
 */
public interface ListaDesideriService {

    /**
     * firma del metodo che implementa l'aggiunta di un prodotto alla lista desideri
     *
     * @param account Account contiene l'account dove viene aggiunto un prodotto
     * @param prodotto Prodotto contiene i dati del prodotto da aggiungere
     *
     * @return un boolean che indica se la modifica va a buon fine
     */
    boolean aggiungiProdottoListaDesideri(Prodotto prodotto, Account account);
    /**
     * firma del metodo che implementa la rimozione di un prodotto dalla lista desideri
     *
     * @param account Account contiene l'account dove viene rimosso un prodotto
     * @param prodotto Prodotto contiene i dati del prodotto da rimuovere
     *
     * @return un boolean che indica se la modifica va a buon fine
     */
    boolean eliminaProdottoListaDesideri(Prodotto prodotto, Account account);

    /**
     * firma del metodo che restituisce la lista desideri
     *
     * @param account Account contiene i dati dell'account per visualizzare la propria lista desideri
     *
     * @return ListaDesideri
     */
    ListaDesideri getListaDesideri(Account account);

}
