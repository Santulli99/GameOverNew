package Storage.cart;

import Application_Logic.entity.Account;
import Application_Logic.entity.cart.Cart;

public interface CartDao <E extends Exception> {
    boolean removeProductFromCart(int idCliente,int id_product) throws E;
    boolean addProductCart(int id_cliente, int id_product) throws E;
    Cart searchCartWithAccount(Account account) throws E;
    boolean removeAllProductFromCart(int idCliente) throws E;
}
