package Application_Logic.gestioneProdotto.service;
import Storage.product.SqlProductDao;
import Application_Logic.entity.Prodotto;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * implementa la classe che esplicita i metodi definiti nell'interfaccia di GestioneProdotto
 *
 * @author Gerardo Esposito
 */

public class GestioneProdottoServiceImp implements GestioneProdottoService {

    private Prodotto prodotto;
    private SqlProductDao sqlProductDao = new SqlProductDao();
    private ArrayList<Prodotto> prodotti = new ArrayList<>();

    public GestioneProdottoServiceImp(SqlProductDao sqlProductDao) {
        this.sqlProductDao=sqlProductDao;
    }

    public GestioneProdottoServiceImp() {

    }
    /**
     * Implementa la funzionalit√† che restituisce i prodotti per la vetrina
     *
     * @param piattaforma String che rappresenta in nome della piattaforma del prodotto
     * @return ArrayList di prodotti
     */
    @Override
    public ArrayList<Prodotto> getProdottiVetrina(String piattaforma) {
        try {
            if ((prodotti = sqlProductDao.searchProductsvetrina(piattaforma)) != null)
                return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalit√† che restituisce il prodotto
     *
     * @param id int rappresenta l'id del prodotto
     * @return l'oggetto prodotto
     */

    @Override
    public Prodotto getProdotto(int id) {
        try {
            if ((prodotto = sqlProductDao.searchProduct(id)) != null)
                return prodotto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalit√† che restituisce tutti i prodotti di una data categoria e piattaforma
     *
     * @param categoria   String rappresenta la categoria del prodotto
     * @param piattaforma int rappresenta l'id della piattaforma
     * @return ArrayList di prodotti
     */

    @Override
    public ArrayList<Prodotto> getAllProdottiPerCategoriaEPiattaforma(String categoria, String piattaforma) {
        try {
            if ((prodotti = sqlProductDao.searchProductsByCategoryAndPlatform(categoria, piattaforma)) != null)
                return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalit√† che restituisce tutti i  prodotti
     *
     * @return ArrayList di prodotti
     */

    @Override
    public ArrayList<Prodotto> getAllProdotti() {
        try {
            if ((prodotti = sqlProductDao.searchAllProducts()) != null)
                return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalit√† che implementa la rimozione di un prodotto dal sito
     *
     * @param id int del prodotto da rimuovere
     * @return boolean indica la riuscita dell'operazione
     */

    @Override
    public boolean rimuoviProdotto(int id) {
        try {
            if (sqlProductDao.deleteProduct(id))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Implementa la funzionalit√† che implementa l'aggiunta di un prodotto alla piattaforma
     *
     * @param prodotto l'oggetto della classe Prodotto che devo aggiungere alla piattaforma
     * @return boolean indica la riuscita dell'operazione
     */

    @Override
    public boolean aggiungiProdotto(Prodotto prodotto) {
        try {
            if (sqlProductDao.createProduct(prodotto))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Implementa la funzionalit√† che implementa la modifica dei parametri di un prodotto presente sulla piattaforma
     *
     * @param prodotto l'oggetto della classe Prodotto su cui devo effettuare la modifica
     * @return boolean indica la riuscita dell'operazione
     */

    @Override
    public boolean modificaProdotto(Prodotto prodotto) {
        try {
            if (sqlProductDao.updateProduct(prodotto))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Implementa la funzionalit√† che si occupa dell'aggiornamento della valutazione media di un prodotto  presente sulla piattaforma
     *
     * @param prodotto l'oggetto della classe Prodotto su cui devo effettuare l'aggiornamento della sua valutazione media
     * @return boolean indica la riuscita dell'operazione
     */

    public boolean modificaValutazioneMediaProdotto(Prodotto prodotto) {
        try {
            if (sqlProductDao.updateProductValuation(prodotto))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}

