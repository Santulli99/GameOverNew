package model.dao.wishlist;

import model.dao.product.ProductExtractor;
import model.dao.storage.SqlDao;
import model.entity.Account;

import model.entity.Product;
import model.entity.Wishlist;

import java.sql.*;
import java.util.ArrayList;

public class SqlWishlistDao implements WishlistDao {
    @Override
    public boolean deleteWishlist(int id_product, int id_cliente) throws Exception {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "DELETE FROM wishlist WHERE (id_prodotto=? AND id_cliente=?);";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_product);
                ps.setInt(2, id_cliente);

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }

    }

    @Override
    public boolean createWishlist(Account account, Product product) throws Exception {
        try (Connection connection = SqlDao.getConnection()) {
            String query = "INSERT INTO wishlist (id_cliente,id_prodotto) VALUES(?,?);";

            try (PreparedStatement ps1 = connection.prepareStatement(query)) {
                ps1.setInt(1, account.getId());
                ps1.setInt(2, product.getId());

                int rows = ps1.executeUpdate();

                return rows == 1;
            }
        }
    }

    @Override
    public Wishlist searchWishlistWithAccount(Account account) throws Exception {
        try (Connection connection = SqlDao.getConnection()) {
            String query = "select * from  product,wishlist where id_cliente=?  and product.id_prodotto=wishlist.id_prodotto;";

            try (PreparedStatement ps1 = connection.prepareStatement(query)) {
                ps1.setInt(1, account.getId());

                ResultSet rs = ps1.executeQuery();
                Product product;
                ProductExtractor productExtractor = new ProductExtractor();

                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = productExtractor.extract(rs);
                    products.add(product);
                }
                Wishlist wishlist = new Wishlist();
                wishlist.setProduct(products);
                return wishlist;

            }
        }
    }
}
