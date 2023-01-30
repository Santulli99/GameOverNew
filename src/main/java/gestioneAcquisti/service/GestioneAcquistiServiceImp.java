package gestioneAcquisti.service;

import model.dao.cart.SqlCartDao;
import model.entity.Account;
import model.entity.cart.Cart;

public class GestioneAcquistiServiceImp implements GestioneAcquistiService {
    private SqlCartDao sqlCartDao=new SqlCartDao();
    @Override
    public boolean rimuoviProdottoDalCarrello(int idCliente, int id_product) {
        try {
            if(sqlCartDao.removeProductFromCart(idCliente,id_product))
                return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean aggiungiProdottoAlCarrello(int idCliente, int id_product) {
        try {
            if(sqlCartDao.addProductCart(idCliente,id_product))
                return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Cart getCart(Account account) {
        Cart cart=new Cart();
        try {
            if((cart=sqlCartDao.searchCartWithAccount(account))!=null)
                return cart;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
