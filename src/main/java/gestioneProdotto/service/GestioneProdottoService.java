package gestioneProdotto.service;

import model.entity.Category;
import model.entity.Platform;
import model.entity.Prodotto;

import java.util.ArrayList;

public interface GestioneProdottoService  {

    Platform getPiattaforma(int id);

    Category getCategoria(int id);

    Prodotto getProdotto(int id);

    ArrayList<Prodotto> getAllProdottiConCategoriaEPiattaforma();
    ArrayList<Prodotto> getAllProdottiPerCategoriaEPiattaforma(String categoria,int piattaforma);
    ArrayList<Prodotto> getAllProdotti();
    Prodotto getProdottoConCategoria(int id);

    Prodotto getProdottoPerId(int id);
    boolean rimuoviProdotto(int id);

    boolean aggiungiProdotto(Prodotto prodotto);
    boolean modificaProdotto(Prodotto prodotto);

}
