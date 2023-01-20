package model.dao.wishlist;

import model.entity.Account;
import model.entity.Prodotto;
import model.entity.ListaDesideri;

public interface ListaDesideriDao<E extends Exception> {

    boolean eliminaProdottoListaDesideri(Account account, Prodotto prodotto) throws E;
    boolean inserisciProdottoListaDesideri(Account account, Prodotto prodotto) throws E;
    ListaDesideri cercaListaDesideriPerUtente(Account account) throws E;




}
