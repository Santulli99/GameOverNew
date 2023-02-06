package recensione.service;

import model.entity.Account;
import model.entity.Prodotto;
import model.entity.Review;

import java.util.ArrayList;
    /**
     * interfaccia per i metodi del sottosistema Recensione
     *
     * @author Andrea Santulli
     */
public interface RecensioneService {
    /**
     * firma del metodo che implementa l'aggiunta di una recensione al prodotto
     *
     * @param review Review contiene la recensione da aggiungere
     *
     * @return un boolean che indica se la modifica va a buon fine
     */
    boolean  aggiungiRecensione(Review review);
    /**
     * firma del metodo che implementa la modifica di una recensione di un prodotto
     *
     * @param review Review contiene la recensione da modificare
     *
     * @return un boolean che indica se la modifica va a buon fine
     */
    boolean modificaRecensione(Review review);
    /**
     * firma del metodo che implementa la rimozione di una recensione di un prodotto
     *
     * @param account Account contiene i dati di un account
     * @param prodotto Prodotto contiene i dati di un prodotto al quale si vuole rimuovere la recensione
     *
     * @return un boolean che indica se la modifica va a buon fine
     */
    boolean rimuoviRecensione(Account account,Prodotto prodotto);
    /**
     * firma del metodo che implementa la ricerca delle recensioni per un prodotto
     *
     * @param prodotto Prodotto contiene i dati di un prodotto del quale si vogliono cercare tutte le recensioni
     *
     * @return un ArrayList di recensioni
     */
    ArrayList<Review> cercaRecensioniPerProdotto(Prodotto prodotto);


    /**
     * firma del metodo che implementa la ricerca della recensione per un prodotto
     *
     * @param prodotto Prodotto contiene i dati di un prodotto del quale si vuole cercare la recensione
     *
     * @return Review una recensione
     */
    Review cercaRecensionePerProdotto(Prodotto prodotto,Account account);
}
