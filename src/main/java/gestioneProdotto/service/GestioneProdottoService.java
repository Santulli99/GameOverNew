package gestioneProdotto.service;

import model.entity.Category;
import model.entity.Platform;
import model.entity.Prodotto;

import java.util.ArrayList;
/**
 * interfaccia per i metodi del sottosistema GestioneProdotto
 *
 * @author Andrea Santulli
 */

public interface GestioneProdottoService  {

    /**
     * firma del metodo che restituisce la piattaforma del prodotto
     * @param id int rappresenta l'id del prodotto
     *
     * @return Platform piattaforma
     */
    Platform getPiattaforma(int id);
    /**
     * firma del metodo che restituisce la categoria del prodotto
     * @param id int rappresenta l'id del prodotto
     *
     * @return Category categoria
     */

    Category getCategoria(int id);
    /**
     * firma del metodo che restituisce il prodotto
     * @param id int rappresenta l'id del prodotto
     *
     * @return Prodotto
     */
    Prodotto getProdotto(int id);

    /**
     * firma del metodo che restituisce tutti i prodotti con una specifica categoria e piattaforma
     * @param
     *
     * @return ArrayList di prodotti
     */
    ArrayList<Prodotto> getAllProdottiConCategoriaEPiattaforma();

    /**
     * firma del metodo che restituisce tutti i prodotti per categoria e piattaforma
     * @param categoria String rappresenta la categoria del prodotto
     * @param piattaforma int rappresenta l'id della piattaforma
     *
     * @return ArrayList di prodotti
     */
    ArrayList<Prodotto> getAllProdottiPerCategoriaEPiattaforma(String categoria,int piattaforma);
    /**
     * firma del metodo che restituisce tutti i  prodotti
     * @param
     *
     * @return ArrayList di prodotti
     */
    ArrayList<Prodotto> getAllProdotti();

    /**
     * firma del metodo che restituisce tutti i  prodotti per una categoria
     * @param id int rappresenta l'id del prodotto
     *
     * @return Prodotto
     */
    Prodotto getProdottoConCategoria(int id);
    /**
     * firma del metodo che restituisce tutti i  prodotti per un id
     * @param id int rappresenta l'id del prodotto
     *
     * @return Prodotto
     */
    Prodotto getProdottoPerId(int id);
    /**
     * firma del metodo che implementa la rimozione di un prodotto dal sito
     * @param id int del prodotto che devo rimuovere
     *
     * @return boolean
     */
    boolean rimuoviProdotto(int id);
    /**
     * firma del metodo che implementa l'aggiunta di un prodotto al sito
     * @param prodotto Prodotto che devo aggiungere
     *
     * @return boolean
     */
    boolean aggiungiProdotto(Prodotto prodotto);
    /**
     * firma del metodo che implementa la modifica di un prodotto del sito
     * @param prodotto Prodotto che devo modificare
     *
     * @return boolean
     */

    boolean modificaProdotto(Prodotto prodotto);

    /**
     * firma del metodo che implementa la modifica della valutazione media di un prodotto del sito
     * @param prodotto Prodotto che devo modificare
     *
     * @return boolean
     */
    boolean modificaValutazioneMediaProdotto(Prodotto prodotto);
}
