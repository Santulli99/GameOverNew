package listaDesideri.service;

import model.dao.product.SqlProductDao;
import model.dao.wishlist.SqlListaDesideriDao;
import model.entity.Account;
import model.entity.ListaDesideri;
import model.entity.Prodotto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListaDesideriServiceImp implements ListaDesideriService {
    private SqlProductDao prodottoDao=new SqlProductDao();
    ListaDesideri listaDesideri=new ListaDesideri();
    private SqlListaDesideriDao listaDesideriDao=new SqlListaDesideriDao();
    @Override
    public boolean aggiungiProdottoListaDesideri(Prodotto prodotto, Account account) {
        try {
            listaDesideri=listaDesideriDao.cercaListaDesideriPerUtente(account);
            if(!listaDesideri.containsListaDesideri(prodotto)){
                listaDesideriDao.inserisciProdottoListaDesideri(account,prodotto);
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean eliminaProdottoListaDesideri(Prodotto prodotto, Account account) {
        try {
            listaDesideri=listaDesideriDao.cercaListaDesideriPerUtente(account);
            if(listaDesideri.containsListaDesideri(prodotto)){
                listaDesideriDao.eliminaProdottoListaDesideri(account,prodotto);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public ArrayList<Prodotto> getListaDesideri(Account account) {
        ArrayList<Prodotto> prodotti=new ArrayList<>();
        try {
            listaDesideri=listaDesideriDao.cercaListaDesideriPerUtente(account);
            prodotti=listaDesideri.getProducts();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prodotti;
    }
}
