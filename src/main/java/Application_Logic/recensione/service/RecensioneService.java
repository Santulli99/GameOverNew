package Application_Logic.recensione.service;

import Application_Logic.entity.Account;
import Application_Logic.entity.Prodotto;
import Application_Logic.entity.Review;

import java.util.ArrayList;

/**
 * interfaccia per i metodi del sottosistema Recensione
 *
 * @author Andrea Santulli
 */
public interface RecensioneService {
    /**
     * firma del metodo che implementa la creazione di una recensione su un  prodotto
     *
     * @param review oggetto della classe Review usato per recensire un prodotto
     * @return un boolean indica la riuscita dell'operazione
     */
    boolean aggiungiRecensione(Review review);

    /**
     * firma del metodo che implementa la modifica di una recensione di un prodotto
     *
     * @param review Review contiene la recensione da modificare
     * @return un boolean indica la riuscita dell'operazione
     */
    boolean modificaRecensione(Review review);

    /**
     * firma del metodo che implementa la rimozione di una recensione di un prodotto
     *
     * @param account  oggetto della classe Account contenente i dati dell'account
     * @param prodotto oggetto della classe Prodotto contenete i dati di un prodotto di cui si vuole rimuovere la recensione
     * @return un boolean indica la riuscita dell'operazione
     */
    boolean rimuoviRecensione(Account account, Prodotto prodotto);

    /**
     * firma del metodo che implementa la ricerca di tutte le recensioni di un prodotto
     *
     * @param prodotto oggetto della classe Prodotto che contiene i dati di un prodotto del quale si vogliono cercare tutte le recensioni
     * @return un ArrayList di recensioni
     */
    ArrayList<Review> cercaRecensioniPerProdotto(Prodotto prodotto);


    /**
     * firma del metodo che implementa la ricerca della recensione dato un prodotto e un account
     *
     * @param prodotto oggetto della classe Prodotto contiene i dati di un prodotto su cui si vuole cercare la recensione
     * @param account  oggetto della classe Account  contenente i dati  di un account usato per verificare le recensioni effettuate
     * @return oggetto della classe Review contenente i dettagli di una recensione su un determinato prodotto e di un determinato account
     */
    Review cercaRecensionePerProdotto(Prodotto prodotto, Account account);
}
