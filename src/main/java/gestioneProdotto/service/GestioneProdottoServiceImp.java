package gestioneProdotto.service;

import model.dao.category.SqlCategoryDao;
import model.dao.platform.SqlPlatformDao;
import model.dao.product.SqlProductDao;
import model.entity.Category;
import model.entity.Platform;
import model.entity.Prodotto;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestioneProdottoServiceImp implements GestioneProdottoService{

   private  Prodotto prodotto;
   private Platform piattaforma;
   private Category categoria;
   private SqlPlatformDao sqlPlatformDao=new SqlPlatformDao();
   private SqlProductDao sqlProductDao=new SqlProductDao();
   private SqlCategoryDao sqlCategoryDao=new SqlCategoryDao();
   private ArrayList<Prodotto> prodotti=new ArrayList<>();

    @Override
    public Platform getPiattaforma(int id) {
        try {
            if((piattaforma=sqlPlatformDao.searchPlatform(id))!=null)
                return piattaforma;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Category getCategoria(int id) {
        try {
            if((categoria=sqlCategoryDao.searchCategory(id))!=null)
                return categoria;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Prodotto getProdotto(int id) {
        try {
            if((prodotto = sqlProductDao.searchProductWithPlatformsAndCategory(id))!=null)
                return prodotto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public Prodotto getProdottoPerId(int id) {
        try {
            if((prodotto =sqlProductDao.searchProduct(id))!=null)
                return prodotto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Prodotto getProdottoConCategoria(int id) {
        try {
            if((prodotto =sqlProductDao.searchProductWithCategory(id))!=null)
                return prodotto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    @Override
    public ArrayList<Prodotto> getAllProdottiConCategoriaEPiattaforma() {
        try {
            if((prodotti =sqlProductDao.searchProductsByCategoryAndPlatform1())!=null)
                return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Prodotto> getAllProdottiPerCategoriaEPiattaforma(String categoria, int piattaforma) {
        try {
            if((prodotti=sqlProductDao.searchProductsByCategoryAndPlatform(categoria,piattaforma))!=null)
                return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Prodotto> getAllProdotti() {
        try {
            if((prodotti =sqlProductDao.searchAllProducts())!=null)
                return prodotti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean rimuoviProdotto(int id) {
        try {
            if(sqlProductDao.deleteProduct(id))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean aggiungiProdotto(Prodotto prodotto) {
        try {
            if(sqlProductDao.createProduct(prodotto))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean modificaProdotto(Prodotto prodotto) {
        try {
            if(sqlProductDao.updateProduct(prodotto))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
