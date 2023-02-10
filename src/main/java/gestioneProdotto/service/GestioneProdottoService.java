package gestioneProdotto.service;

import model.entity.Category;
import model.entity.Platform;
import model.entity.Prodotto;

import java.util.ArrayList;

/**
 * Interfaccia per i metodi del sottosistema GestioneProdotto
 *
 * @author Gerardo Esposito
 */

public interface GestioneProdottoService {

    /**
     * firma del metodo che restituisce il prodotto
     *
     * @param id int rappresenta l'id del prodotto
     * @return l'oggetto prodotto
     */
    Prodotto getProdotto(int id);

    /**
     * firma del metodo che restituisce tutti i prodotti di una data categoria e piattaforma
     *
     * @param categoria   String rappresenta la categoria del prodotto
     * @param piattaforma int rappresenta l'id della piattaforma
     * @return ArrayList di prodotti
     */
    ArrayList<Prodotto> getAllProdottiPerCategoriaEPiattaforma(String categoria, String piattaforma);

    /**
     * firma del metodo che restituisce tutti i  prodotti
     *
     * @return ArrayList di prodotti
     */
    ArrayList<Prodotto> getAllProdotti();


    /**
     * firma del metodo che implementa la rimozione di un prodotto dal sito
     *
     * @param id int del prodotto da rimuovere
     * @return boolean indica la riuscita dell'operazione
     */
    boolean rimuoviProdotto(int id);

    /**
     * firma del metodo che implementa l'aggiunta di un prodotto alla piattaforma
     *
     * @param prodotto l'oggetto della classe Prodotto che devo aggiungere alla piattaforma
     * @return boolean indica la riuscita dell'operazione
     */
    boolean aggiungiProdotto(Prodotto prodotto);

    /**
     * firma del metodo che implementa la modifica dei parametri di un prodotto presente sulla piattaforma
     *
     * @param prodotto l'oggetto della classe Prodotto su cui devo effettuare la modifica
     * @return boolean indica la riuscita dell'operazione
     */

    boolean modificaProdotto(Prodotto prodotto);

    /**
     * firma del metodo che si occupa dell'aggiornamento della valutazione media di un prodotto  presente sulla piattaforma
     *
     * @param prodotto l'oggetto della classe Prodotto su cui devo effettuare l'aggiornamento della sua valutazione media
     * @return boolean indica la riuscita dell'operazione
     */
    boolean modificaValutazioneMediaProdotto(Prodotto prodotto);
}
