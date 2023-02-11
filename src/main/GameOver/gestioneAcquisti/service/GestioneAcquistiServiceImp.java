package gestioneAcquisti.service;

import model.dao.cart.SqlCartDao;
import model.dao.order.SqlOrderDao;
import model.entity.Account;
import model.entity.Order;
import model.entity.cart.Cart;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * implementa la classe che esplicita i metodi definiti nell'interfaccia di GestioneAcquisti
 *
 * @author Andrea Serpico
 */

public class GestioneAcquistiServiceImp implements GestioneAcquistiService {
    private SqlCartDao sqlCartDao = new SqlCartDao();
    private SqlOrderDao orderDao = new SqlOrderDao();

    /**
     * Implementa la funzionalità per effettuare la rimozione di un prodotto dal carrello
     *
     * @param idCliente  int rappresenta l'id del cliente
     * @param id_product int rappresenta l'id del prodotto da rimuovere dal carrello
     * @return boolean indica la riuscita dell'operazione
     */

    @Override
    public boolean rimuoviProdottoDalCarrello(int idCliente, int id_product) {
        try {
            if (sqlCartDao.removeProductFromCart(idCliente, id_product))
                return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Implementa la funzionalità per effettuare la rimozione di tutti i prodotti dal carrello
     *
     * @param idCliente int rappresenta l'id del cliente
     * @return boolean indica la riuscita dell'operazione
     */

    @Override
    public boolean rimuoviAllProdottiDalCarrello(int idCliente) {
        try {
            if (sqlCartDao.removeAllProductFromCart(idCliente))
                return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Implementa la funzionalità per effettuare l'aggiunta di un prodotto al carrello
     *
     * @param idCliente  int rappresenta l'id del cliente
     * @param id_product int rappresenta l'id del prodotto da aggiungere al carrello
     * @return boolean indica la riuscita dell'operazione
     */

    @Override
    public boolean aggiungiProdottoAlCarrello(int idCliente, int id_product) {
        try {
            if (sqlCartDao.addProductCart(idCliente, id_product))
                return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Implementa la funzionalità per effettuare la restituzione del carrello
     *
     * @param account l'ccount dell'utente loggato
     * @return Cart carrello
     */

    @Override
    public Cart getCart(Account account) {
        Cart cart = new Cart();
        try {
            if ((cart = sqlCartDao.searchCartWithAccount(account)) != null)
                return cart;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità per effettuare la creazione di un ordine
     *
     * @param order oggetto della classe Order che contiene le info sull'acquisto effettuato
     * @return boolean indica la riuscita dell'operazione
     */

    @Override
    public boolean creaOrdine(Order order) {
        try {
            if (orderDao.createOrder(order))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Implementa la funzionalità per effettuare la restituzione di tutti gli ordini effettuati dagli utenti registrati al sito web
     *
     * @return ArrayList di ordini
     */

    @Override
    public ArrayList<Order> getAllOrdiniConAccount() {
        ArrayList<Order> ordini = new ArrayList<>();
        try {
            if ((ordini = orderDao.searchAllOrdersWithAccount()) != null)
                return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità  che restituisce tutti gli ordini con i prodotti  effettuati
     * da un singolo utente registrato al sito web e loggato in quel momento
     *
     * @param account utente registrato al sito web e loggato
     * @return ArrayList di ordini
     */

    @Override
    public ArrayList<Order> searchAllOrderWithProductsbyAccount(Account account) {
        ArrayList<Order> ordini = new ArrayList<>();
        try {
            if ((ordini = orderDao.searchAllOrderWithProductsbyAccount(account)) != null)
                return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità che restituisce tutti gli ordini effettuati
     * da un singolo utente registrato al sito web e loggato in quel momento
     *
     * @param id int rappresenta l'id dell'acount
     * @return ArrayList di ordini
     */


    @Override
    public ArrayList<Order> getAllOrdiniDiUnAccount(int id) {
        ArrayList<Order> ordini = new ArrayList<>();
        try {
            if ((ordini = orderDao.searchAllOrdersByAccount(id)) != null)
                return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità che restituisce un ordine con i prodotti
     *
     * @param id_ordine int rappresenta l'id dell'ordine
     * @return oggetto della classe Order che contiene le info sull'acquisto effettuato con i prodotti
     */

    @Override
    public Order getOrdineConProdotti(int id_ordine) {
        Order order = new Order();
        try {
            if ((order = orderDao.searchOrderWithProducts(id_ordine)) != null)
                return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implemeneta la funzionalità che restituisce tutti gli ordini con i prodotti
     *
     * @return ArrayList di ordini
     */
    @Override
    public ArrayList<Order> getAllOrdiniConProdotti() {
        ArrayList<Order> ordini = new ArrayList<>();
        try {
            if ((ordini = orderDao.searchAllOrderWithProducts()) != null)
                return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
