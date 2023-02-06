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
     * @param idCliente int rappresenta l'id del cliente
     * @param id_product int rappresenta l'id del prodotto da rimuovere dal carrello
     *
     * @return boolean
     */

    boolean rimuoviProdottoDalCarrello(int idCliente, int id_product);
    /**
     * firma del metodo che implementa la rimozione dei prodotti dal carrello
     * @param idCliente int rappresenta l'id del cliente
     *
     * @return boolean
     */
    boolean rimuoviAllProdottiDalCarrello(int idCliente);
    /**
     * firma del metodo che implementa l'aggiunta di un prodotto al carrello
     * @param idCliente int rappresenta l'id del cliente
     * @param id_product int rappresenta l'id del prodotto da aggiungere al carrello
     *
     * @return boolean
     */
    boolean aggiungiProdottoAlCarrello(int idCliente, int id_product);
    /**
     * firma del metodo che restituisce il carrello
     * @param account Account
     *
     * @return Cart carrello
     */
    Cart getCart(Account account);
    /**
     * firma del metodo che implementa la creazione di un ordine
     * @param order Order
     *
     * @return boolean
     */
    boolean creaOrdine(Order order);
    /**
     * firma del metodo che restituisce tutti gli ordini degli Account
     * @param
     *
     * @return ArrayList di ordini
     */
    ArrayList<Order> getAllOrdiniConAccount();


    ArrayList<Order> searchAllOrderWithProductsbyAccount(Account account);


    /**
     * firma del metodo che restituisce tutti gli ordini di un Account
     * @param id int rappresenta l'id dell'acount
     *
     * @return ArrayList di ordini
     */
    ArrayList<Order> getAllOrdiniDiUnAccount(int id);
    /**
     * firma del metodo che restituisce un ordine con i prodotti
     * @param id_ordine int rappresenta l'id dell'ordine
     *
     * @return Order ordine
     */
    Order getOrdineConProdotti(int id_ordine);
    /**
     * firma del metodo che implementa la rimozione di un ordine
     * @param ordine Order rappresenta l'ordine da rimuovere
     *
     * @return boolean
     */
    boolean rimuoviOrdine(Order ordine);
    /**
     * firma del metodo che restituisce l'ordine
     * @param id int rappresenta l'id dell'ordine
     *
     * @return Order ordine
     */
    Order getOrdine(int id);
}
