package Storage.wishlist;

import Application_Logic.entity.Account;
import Application_Logic.entity.Prodotto;
import Application_Logic.entity.ListaDesideri;

public interface ListaDesideriDao<E extends Exception> {

    boolean eliminaProdottoListaDesideri(Account account, Prodotto prodotto) throws E;
    boolean inserisciProdottoListaDesideri(Account account, Prodotto prodotto) throws E;
    ListaDesideri cercaListaDesideriPerUtente(Account account) throws E;


}
