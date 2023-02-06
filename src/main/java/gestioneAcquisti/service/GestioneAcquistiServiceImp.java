package gestioneAcquisti.service;

import model.dao.cart.SqlCartDao;
import model.dao.order.SqlOrderDao;
import model.entity.Account;
import model.entity.Order;
import model.entity.cart.Cart;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestioneAcquistiServiceImp implements GestioneAcquistiService {
    private SqlCartDao sqlCartDao = new SqlCartDao();
    private SqlOrderDao orderDao=new SqlOrderDao();

    @Override
    public boolean rimuoviProdottoDalCarrello(int idCliente, int id_product) {
        try {
            if (sqlCartDao.removeProductFromCart(idCliente, id_product))
                return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean rimuoviAllProdottiDalCarrello(int idCliente) {
        try {
            if(sqlCartDao.removeAllProductFromCart(idCliente))
                return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean aggiungiProdottoAlCarrello(int idCliente, int id_product) {
        try {
            if (sqlCartDao.addProductCart(idCliente, id_product))
                return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Cart getCart(Account account) {
        Cart cart = new Cart();
        try {
            if ((cart = sqlCartDao.searchCartWithAccount(account)) != null)
                return cart;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean creaOrdine(Order order) {
        try {
            if(orderDao.createOrder(order))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public ArrayList<Order> getAllOrdiniConAccount() {
        ArrayList<Order> ordini=new ArrayList<>();
        try {
            if((ordini=orderDao.searchAllOrdersWithAccount())!=null)
                return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Order> searchAllOrderWithProductsbyAccount(Account account) {
        ArrayList<Order> ordini=new ArrayList<>();
        try {
            if((ordini=orderDao.searchAllOrderWithProductsbyAccount(account))!=null)
                return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public ArrayList<Order> getAllOrdiniDiUnAccount(int id) {
        ArrayList<Order> ordini=new ArrayList<>();
        try {
            if((ordini=orderDao.searchAllOrdersByAccount(id))!=null)
                return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Order getOrdineConProdotti(int id_ordine) {
        Order order=new Order();
        try {
            if((order=orderDao.searchOrderWithProducts(id_ordine))!=null)
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean rimuoviOrdine(Order ordine){
        try {
            if(orderDao.deleteOrder(ordine))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Order getOrdine(int id) {
        Order ord=new Order();
        try {
            if((ord=orderDao.searchOrderId(id))!=null)
                return ord;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
