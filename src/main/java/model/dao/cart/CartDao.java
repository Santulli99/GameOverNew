package model.dao.cart;

import model.entity.Account;
import model.entity.cart.Cart;

public interface CartDao <E extends Exception> {
    boolean removeProductFromCart(int idCliente,int id_product) throws E;
    boolean addProductCart(int id_cliente, int id_product) throws E;
    Cart searchCartWithAccount(Account account) throws E;
    boolean removeAllProductFromCart(int idCliente) throws E;
}
