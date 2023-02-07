package gestioneProdotto.service;

import model.dao.category.SqlCategoryDao;
import model.dao.platform.SqlPlatformDao;
import model.dao.product.SqlProductDao;
import model.entity.Category;
import model.entity.Platform;
import model.entity.Prodotto;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * implementa la classe che esplicita i metodi definiti nell'interfaccia di GestioneProdotto
 *
 * @author Gerardo Esposito
 */

public class GestioneProdottoServiceImp implements GestioneProdottoService {

    private Prodotto prodotto;
    private Platform piattaforma;
    private Category categoria;
    private SqlPlatformDao sqlPlatformDao = new SqlPlatformDao();
    private SqlProductDao sqlProductDao = new SqlProductDao();
    private SqlCategoryDao sqlCategoryDao = new SqlCategoryDao();
    private ArrayList<Prodotto> prodotti = new ArrayList<>();


    /**
     * Implementa la funzionalità che restituisce il tipo di piattaforma del prodotto
     *
     * @param id int rappresenta l'id della piattaforma
     * @return oggetto della classe Platform che contiene i dati relativi alla piattaforma
     */


    @Override
    public Platform getPiattaforma(int id) {
        try {
            if ((piattaforma = sqlPlatformDao.searchPlatform(id)) != null)
                return piattaforma;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità che restituisce la categoria del prodotto
     *
     * @param id int rappresenta l'id della categoria
     * @return l'oggetto category
     */
    @Override
    public Category getCategoria(int id) {
        try {
            if ((categoria = sqlCategoryDao.searchCategory(id)) != null)
                return categoria;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità che restituisce il prodotto
     *
     * @param id int rappresenta l'id del prodotto
     * @return l'oggetto prodotto
     */

    @Override
    public Prodotto getProdotto(int id) {
        try {
            if ((prodotto = sqlProductDao.searchProductWithPlatformsAndCategory(id)) != null)
                return prodotto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità che restituisce un prodotto dato un id
     *
     * @param id int rappresenta l'id del prodotto
     * @return l'oggetto prodotto
     */

    @Override
    public Prodotto getProdottoPerId(int id) {
        try {
            if ((prodotto = sqlProductDao.searchProduct(id)) != null)
                return prodotto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    /**
     * Implementa la funzionalità che restituisce un prodotto con la relativa categoria
     *
     * @param id int rappresenta l'id del prodotto
     * @return l'oggetto prodotto
     */

    @Override
    public Prodotto getProdottoConCategoria(int id) {
        try {
            if ((prodotto = sqlProductDao.searchProductWithCategory(id)) != null)
                return prodotto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità che restituisce tutti i prodotti del sito web con categoria e piattaforma
     *
     * @return ArrayList di prodotti
     */

    @Override
    public ArrayList<Prodotto> getAllProdottiConCategoriaEPiattaforma() {
        try {
            if ((prodotti = sqlProductDao.searchProductsByCategoryAndPlatform1()) != null)
                return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità che restituisce tutti i prodotti di una data categoria e piattaforma
     *
     * @param categoria   String rappresenta la categoria del prodotto
     * @param piattaforma int rappresenta l'id della piattaforma
     * @return ArrayList di prodotti
     */

    @Override
    public ArrayList<Prodotto> getAllProdottiPerCategoriaEPiattaforma(String categoria, int piattaforma) {
        try {
            if ((prodotti = sqlProductDao.searchProductsByCategoryAndPlatform(categoria, piattaforma)) != null)
                return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Implementa la funzionalità che restituisce tutti i  prodotti
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
     * Implementa la funzionalità che implementa la rimozione di un prodotto dal sito
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
     * Implementa la funzionalità che implementa l'aggiunta di un prodotto alla piattaforma
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
     * Implementa la funzionalità che implementa la modifica dei parametri di un prodotto presente sulla piattaforma
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
     * Implementa la funzionalità che si occupa dell'aggiornamento della valutazione media di un prodotto  presente sulla piattaforma
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

