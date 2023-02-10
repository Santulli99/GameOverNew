package model.dao.order;


import model.entity.Account;
import model.entity.Order;

import java.util.ArrayList;

public interface OrderDao <E extends Exception > {

    Order searchOrderId(int id)throws E;
    //hash Map
    ArrayList<Order> searchAllOrderWithProducts() throws E;
    ArrayList<Order> searchAllOrder() throws E;
    Order searchOrderWithAccount(int id_ordine,int id_Account)throws E;
    Order searchOrderWithProducts(int id_order) throws E;

    boolean createOrder(Order order) throws E;
    boolean deleteOrder(Order order) throws E;
    ArrayList<Order> searchAllOrdersByAccount(int id_Account) throws E;
    ArrayList<Order> searchAllOrdersWithAccount() throws E;

    ArrayList<Order> searchAllOrderWithProductsbyAccount(Account account) throws E;
    /** amministratore **/
    /** ricerca  ordini relativi al prodotto(nome)**/





    /** utente**/
    /** utente schermata con tutti gli  ordini  e quando clicca su un ordine compaiono i prodotti acquistati **/
    /** searchOrderWithProducts **/


}
