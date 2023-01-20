package listaDesideri.service;

import model.entity.Account;
import model.entity.ListaDesideri;
import model.entity.Prodotto;

import java.util.ArrayList;

public interface ListaDesideriService {

    boolean aggiungiProdottoListaDesideri(Prodotto prodotto, Account account);

    boolean eliminaProdottoListaDesideri(Prodotto prodotto, Account account);

    ArrayList<Prodotto> getListaDesideri(Account account);

}
