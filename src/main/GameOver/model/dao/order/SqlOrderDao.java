package model.dao.order;

import model.entity.Account;
import model.dao.account.AccountExtractor;
import model.entity.cart.CartItem;
import model.entity.Order;
import model.entity.Prodotto;
import model.dao.product.ProductExtractor;

import model.dao.storage.SqlDao;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.sql.Connection;
import java.sql.SQLException;

public class  SqlOrderDao implements OrderDao<SQLException>{


    public SqlOrderDao() {
        super();
    }

    @Override
    public Order searchOrderId(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM orders AS ord WHERE id_ordine=?;";

          try(  PreparedStatement ps = connection.prepareStatement(query)) {
              ps.setInt(1, id);

              ResultSet rs = ps.executeQuery();

              Order order = null;
              OrderExtractor orderExtractor = new OrderExtractor();

              if (rs.next()) {
                  order = orderExtractor.extract(rs);
              }
              return order;
          }
        }
    }

    /** insert the paginator **/

    @Override
     public Order searchOrderWithProducts(int id_order) throws SQLException{

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM  orders AS ord,product AS pro,order_product AS op " +
                    "WHERE ord.id_ordine=op.id_ordine AND op.id_prodotto=pro.id_prodotto AND ord.id_ordine=? ";
            try(PreparedStatement ps = connection.prepareStatement(query)) {

                ps.setInt(1,id_order);

                ResultSet resultSet= ps.executeQuery();
                Order order= null;
                ProductExtractor productExtractor= new ProductExtractor();
                if(resultSet.next()){
                    OrderExtractor orderExtractor=new OrderExtractor();
                    order=orderExtractor.extract(resultSet);
                    order.getProducts().add(productExtractor.extract(resultSet));

                }
                while(resultSet.next()){
                    order.getProducts().add(productExtractor.extract(resultSet));
                }

                return order;
            }
        }
    }

    public ArrayList<Order> searchAllOrderWithProductsbyAccount(Account account) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {

            String query = "SELECT *  FROM orders AS ord,product AS pro,order_product AS op WHERE ord.id_cliente=? AND ord.id_ordine=op.id_ordine AND op.id_prodotto=pro.id_prodotto";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1,account.getId());
                ResultSet rs = ps.executeQuery();

                Order order = null;
                OrderExtractor orderExtractor = new OrderExtractor();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                HashMap<Integer, Order> hashMap = new LinkedHashMap();
                while (rs.next()) {

                    int id_ordine = rs.getInt("id_ordine");

                    //relazione N:
                    if (!hashMap.containsKey(id_ordine)) {
                        order = orderExtractor.extract(rs);
                        hashMap.put(id_ordine, order);
                    }
                    prodotto = productExtractor.extract(rs);
                    hashMap.get(id_ordine).getProducts().add(prodotto);
                }


                return new ArrayList<>(hashMap.values());
            }

        }

    }

    @Override
    public ArrayList<Order> searchAllOrderWithProducts() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {

            String query = "SELECT *  FROM orders AS ord,product AS pro,order_product AS op WHERE ord.id_ordine=op.id_ordine AND op.id_prodotto=pro.id_prodotto";

            try(PreparedStatement ps = connection.prepareStatement(query)) {

                ResultSet rs = ps.executeQuery();

                Order order = null;
                OrderExtractor orderExtractor = new OrderExtractor();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                HashMap<Integer, Order> hashMap = new LinkedHashMap();
                while (rs.next()) {

                    int id_ordine = rs.getInt("id_ordine");

                    //relazione N:
                    if (!hashMap.containsKey(id_ordine)) {
                        order = orderExtractor.extract(rs);
                        hashMap.put(id_ordine, order);
                    }
                    prodotto = productExtractor.extract(rs);
                    hashMap.get(id_ordine).getProducts().add(prodotto);
                }


                return new ArrayList<>(hashMap.values());
            }

        }

    }

    @Override
    public boolean createOrder(Order order) throws SQLException {
        try(Connection connection=SqlDao.getConnection()) {
            String query1 = "INSERT INTO orders(data_ordine, n_prodotti, id_cliente) VALUES (?,?,?);";
            String query2 = "INSERT INTO order_product(id_ordine, id_prodotto) VALUES (?,?);";

            connection.setAutoCommit(false);

         try( PreparedStatement ps1 = connection.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS)) {
             ps1.setDate(1, Date.valueOf(order.getDate()));
             ps1.setInt(2, order.getNum_product());
             ps1.setInt(3, order.getAccount().getId());
             int rows = ps1.executeUpdate();
             PreparedStatement psAssoc = connection.prepareStatement(query2);
             int total = rows;

             ResultSet rs = ps1.getGeneratedKeys();
             rs.next();
             int id = rs.getInt(1);
             order.setId(id);



             for (CartItem item : order.getCart().getCartItems()) {
                 psAssoc.setInt(1, order.getId());
                 psAssoc.setInt(2, item.getItem().getId());
                 total += psAssoc.executeUpdate();
             }
             if (total == (rows + order.getNum_product())) {
                 connection.commit();
                 connection.setAutoCommit(true);
                 return true;
             }
             else{
                 connection.rollback();
                 connection.setAutoCommit(true);
                 return false;
             }


         }
        }
    }

    @Override
    public boolean deleteOrder(Order order) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query1 = "DELETE FROM orders WHERE (id_ordine=?);";
            String query2 = "DELETE FROM order_product WHERE (id_ordine=?);";
            try (PreparedStatement ps = connection.prepareStatement(query1)) {
                PreparedStatement ps1 = connection.prepareStatement(query2);

                ps.setInt(1, order.getId());
                ps1.setInt(1,order.getId());

                int rows = ps.executeUpdate();

                ps1.executeUpdate();
                return rows == 1 ;
            }
        }
    }


    @Override
    public ArrayList<Order> searchAllOrdersByAccount(int id_Account) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM orders AS ord WHERE (ord.id_cliente=?);";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_Account);

                ResultSet rs = ps.executeQuery();

                Order order = null;
                OrderExtractor orderExtractor = new OrderExtractor();
                ArrayList<Order> orders = new ArrayList<>();

                while (rs.next()) {
                    order = orderExtractor.extract(rs);
                    orders.add(order);
                }
                return orders;
            }
        }
    }




    public ArrayList<Order> searchAllOrdersWithAccount() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM orders AS ord,account AS acc  WHERE ord.id_cliente=acc.id_cliente;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {


                ResultSet rs = ps.executeQuery();

                Order order = null;
                OrderExtractor orderExtractor = new OrderExtractor();
                ArrayList<Order> orders = new ArrayList<>();
                Account account;
                AccountExtractor accountExtractor= new AccountExtractor();

                while (rs.next()) {
                    order = orderExtractor.extract(rs);
                    account=accountExtractor.extract(rs);
                    order.setAccount(account);
                    orders.add(order);
                }
                return orders;
            }
        }
    }
}
