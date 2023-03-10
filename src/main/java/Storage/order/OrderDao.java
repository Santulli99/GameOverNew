package Storage.order;


import Application_Logic.entity.Account;
import Application_Logic.entity.Order;

import java.util.ArrayList;

public interface OrderDao <E extends Exception > {

    Order searchOrderId(int id)throws E;
    ArrayList<Order> searchAllOrderWithProducts() throws E;
    Order searchOrderWithProducts(int id_order) throws E;
    boolean createOrder(Order order) throws E;
    boolean deleteOrder(Order order) throws E;
    ArrayList<Order> searchAllOrdersByAccount(int id_Account) throws E;
    ArrayList<Order> searchAllOrdersWithAccount() throws E;
    ArrayList<Order> searchAllOrderWithProductsbyAccount(Account account) throws E;

}
