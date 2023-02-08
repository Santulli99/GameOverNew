package recensione.service;

import model.dao.product.SqlProductDao;
import model.dao.review.SqlReviewDao;
import model.entity.Account;
import model.entity.Prodotto;
import model.entity.Review;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * implementa la classe che esplicita i metodi definiti nell'interfaccia di RecensioneService
 *
 * @author Andrea Serpico
 */

public class RecensioneServiceImp implements RecensioneService {
    private SqlReviewDao reviewDao = new SqlReviewDao();
    private SqlProductDao productDao = new SqlProductDao();
    private ArrayList<Review> recensioni = new ArrayList<>();

    public RecensioneServiceImp() {
    }

    /**
     * Implementa la funzionalità per la creazione di una recensione su un prodotto
     *
     * @param review oggetto della classe Review usato per recensire un prodotto
     * @return un boolean indica la riuscita dell'operazione
     */
    @Override
    public boolean aggiungiRecensione(Review review) {
        try {
            if (reviewDao.createReview(review))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Implementa la funzionalità  per la modifica di una recensione di un prodotto
     *
     * @param review Review contiene la recensione da modificare
     * @return un boolean indica la riuscita dell'operazione
     */


    @Override
    public boolean modificaRecensione(Review review) {
        try {
            if (reviewDao.updateReview(review))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Implementa la funzionalità per la  rimozione di una recensione di un prodotto
     *
     * @param account  oggetto della classe Account contenente i dati dell'account
     * @param prodotto oggetto della classe Prodotto contenete i dati di un prodotto di cui si vuole rimuovere la recensione
     * @return un boolean indica la riuscita dell'operazione
     */

    @Override
    public boolean rimuoviRecensione(Account account, Prodotto prodotto) {
        try {
            int id_account = account.getId();
            int id_prodotto = prodotto.getId();
            if (reviewDao.deleteReview(id_account, id_prodotto))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    /**
     * Implementa la funzionalità per la ricerca di tutte le recensioni  di un prodotto
     *
     * @param prodotto oggetto della classe Prodotto che contiene i dati di un prodotto del quale si vogliono cercare tutte le recensioni
     * @return un ArrayList di recensioni
     */


    @Override
    public ArrayList<Review> cercaRecensioniPerProdotto(Prodotto prodotto) {
        try {
            if ((recensioni = reviewDao.searchAllReviewWithProduct(prodotto.getId())) != null)
                return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità per  la ricerca della recensione dato un prodotto e un account
     *
     * @param prodotto oggetto della classe Prodotto contiene i dati di un prodotto su cui si vuole cercare la recensione
     * @param account  oggetto della classe Account  contenente i dati  di un account usato per verificare le recensioni effettuate
     * @return oggetto della classe Review contenente i dettagli di una recensione su un determinato prodotto e di un determinato account
     */

    @Override
    public Review cercaRecensionePerProdotto(Prodotto prodotto, Account account) {
        Review review = new Review();
        try {
            if ((review = reviewDao.searchReviewWithProductAndAccount(prodotto.getId(), account.getId())) != null)
                return review;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
