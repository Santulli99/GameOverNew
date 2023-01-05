package model.dao.cart;

import model.dao.product.ProductExtractor;
import model.dao.storage.SqlDao;
import model.entity.Account;
import model.entity.Product;
import model.entity.cart.Cart;
import model.entity.cart.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SqlCartDao implements CartDao {
    @Override
    public boolean removeProductFromCart(int idCliente, int id_product) throws Exception {
        try (Connection connection = SqlDao.getConnection()) {
            String query = "DELETE FROM cart WHERE (id_prodotto=? AND id_cliente=?);";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_product);
                ps.setInt(2, idCliente);

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }

    }

    @Override
    public boolean addProductCart(int id_cliente, int id_product) throws Exception {
        try (Connection connection = SqlDao.getConnection()) {
            String query = "INSERT INTO cart (id_cliente,id_prodotto) VALUES(?,?);";

            try (PreparedStatement ps1 = connection.prepareStatement(query)) {
                ps1.setInt(1, id_cliente);
                ps1.setInt(2, id_product);

                int rows = ps1.executeUpdate();

                return rows == 1;
            }
        }

    }

    @Override
    public Cart searchCartWithAccount(Account account) throws Exception {
        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT * FROM  product,cart where id_cliente=?  and product.id_prodotto=cart.id_prodotto;";

            try (PreparedStatement ps1 = connection.prepareStatement(query)) {
                ps1.setInt(1, account.getId());

                ResultSet rs = ps1.executeQuery();
                Product product;
                ProductExtractor productExtractor = new ProductExtractor();

                ArrayList<CartItem> products = new ArrayList<>();
                while (rs.next()) {
                    product = productExtractor.extract(rs);
                    CartItem cartItem=new CartItem(product);
                    products.add(cartItem);
                }
                Cart cart=new Cart();
                cart.setCartItems(products);
                return cart;
            }
        }

    }
}
