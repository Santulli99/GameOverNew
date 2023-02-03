package gestioneAcquisti.service;

import model.entity.Account;
import model.entity.Order;
import model.entity.cart.Cart;

import java.util.ArrayList;

public interface GestioneAcquistiService {

    boolean rimuoviProdottoDalCarrello(int idCliente, int id_product);

    boolean rimuoviAllProdottiDalCarrello(int idCliente);
    boolean aggiungiProdottoAlCarrello(int idCliente, int id_product);
    Cart getCart(Account account);

    boolean creaOrdine(Order order);
    ArrayList<Order> getAllOrdiniConAccount();
    ArrayList<Order> getAllOrdiniDiUnAccount(int id);
    Order getOrdineConProdotti(int id_ordine);

    boolean rimuoviOrdine(Order ordine);
    Order getOrdine(int id);
}
