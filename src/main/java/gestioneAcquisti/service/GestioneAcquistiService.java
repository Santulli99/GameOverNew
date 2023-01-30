package gestioneAcquisti.service;

import model.entity.Account;
import model.entity.cart.Cart;

public interface GestioneAcquistiService {

    boolean rimuoviProdottoDalCarrello(int idCliente, int id_product);

    boolean aggiungiProdottoAlCarrello(int idCliente, int id_product);
    Cart getCart(Account account);
}
