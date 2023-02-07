package gestioneAcquisti.service;

import model.entity.Account;
import model.entity.Order;
import model.entity.cart.Cart;

import java.util.ArrayList;

/**
 * interfaccia per i metodi del sottosistema GestioneAcquisti
 *
 * @author Andrea Serpico
 */

public interface GestioneAcquistiService {
    /**
     * firma del metodo che implementa la rimozione di un prodotto dal carrello
     *
     * @param idCliente  int rappresenta l'id del cliente
     * @param id_product int rappresenta l'id del prodotto da rimuovere dal carrello
     * @return boolean indica la riuscita dell'operazione
     */

    boolean rimuoviProdottoDalCarrello(int idCliente, int id_product);

    /**
     * firma del metodo che implementa la rimozione di tutti i prodotti dal carrello
     *
     * @param idCliente int rappresenta l'id del cliente
     * @return boolean indica la riuscita dell'operazione
     */
    boolean rimuoviAllProdottiDalCarrello(int idCliente);

    /**
     * firma del metodo che implementa l'aggiunta di un prodotto al carrello
     *
     * @param idCliente  int rappresenta l'id del cliente
     * @param id_product int rappresenta l'id del prodotto da aggiungere al carrello
     * @return boolean indica la riuscita dell'operazione
     */
    boolean aggiungiProdottoAlCarrello(int idCliente, int id_product);

    /**
     * firma del metodo che restituisce il carrello
     *
     * @param account l'ccount dell'utente loggato
     * @return Cart carrello
     */
    Cart getCart(Account account);

    /**
     * firma del metodo che implementa la creazione di un ordine
     *
     * @param order oggetto della classe Order che contiene le info sull'acquisto effettuato
     * @return boolean indica la riuscita dell'operazione
     */
    boolean creaOrdine(Order order);

    /**
     * firma del metodo che restituisce tutti gli ordini effettuati dagli utenti registrati al sito web
     *
     * @return ArrayList di ordini
     */
    ArrayList<Order> getAllOrdiniConAccount();

    /**
     * firma del metodo che restituisce tutti gli ordini con i prodotti  effettuati
     * da un singolo utente registrato al sito web e loggato in quel momento
     *
     * @param account utente registrato al sito web e loggato
     * @return ArrayList di ordini
     */

    ArrayList<Order> searchAllOrderWithProductsbyAccount(Account account);


    /**
     * firma del metodo che restituisce tutti gli ordini con i prodotti  effettuati
     * da un singolo utente registrato al sito web e loggato in quel momento
     *
     * @param id int rappresenta l'id dell'acount
     * @return ArrayList di ordini
     */
    ArrayList<Order> getAllOrdiniDiUnAccount(int id);

    /**
     * firma del metodo che restituisce un ordine con i prodotti
     *
     * @param id_ordine int rappresenta l'id dell'ordine
     * @return oggetto della classe Order che contiene le info sull'acquisto effettuato con i prodotti
     */
    Order getOrdineConProdotti(int id_ordine);

    /**
     * firma del metodo che implementa la rimozione di un ordine
     *
     * @param ordine oggetto della classe Order che rappresenta l'ordine da rimuovere
     * @return boolean indica la riuscita dell'operazione
     */
    boolean rimuoviOrdine(Order ordine);

    /**
     * firma del metodo che restituisce l'ordine
     *
     * @param id int rappresenta l'id dell'ordine
     * @return oggetto della classe Order che contiene le info sull'acquisto effettuato
     */
    Order getOrdine(int id);
}
