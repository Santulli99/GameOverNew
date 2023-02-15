package model.dao.review;

import model.dao.account.AccountExtractor;
import model.dao.product.ProductExtractor;
import model.dao.storage.SqlDao;
import model.entity.Account;
import model.entity.Prodotto;
import model.entity.Review;

import java.sql.*;
import java.util.ArrayList;

public class SqlReviewDao implements ReviewDao<SQLException> {
    public SqlReviewDao() {
        super();
    }

    @Override
    public boolean createReview(Review review) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "INSERT INTO review(id_cliente,id_prodotto, descrizione,valutazione,titolo) " +
                    "VALUES (?,?,?,?,?);";

            try (PreparedStatement ps1 = connection.prepareStatement(query)) {
                ps1.setInt(1, review.getAccount().getId());
                ps1.setInt(2, review.getProdotto().getId());
                ps1.setString(3, review.getDescrizione());
                ps1.setDouble(4, review.getValutazione());
                ps1.setString(5, review.getTitolo());
                int rows = ps1.executeUpdate();

                return rows == 1;
            }
        }
    }

    @Override
    public boolean updateReview(Review review) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "UPDATE review SET descrizione=?,valutazione=?,titolo=? WHERE id_cliente=? and id_prodotto=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, review.getDescrizione());
                ps.setDouble(2, review.getValutazione());
                ps.setString(3, review.getTitolo());
                ps.setInt(4, review.getAccount().getId());
                ps.setInt(5, review.getProdotto().getId());


                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean deleteReview(int id_account, int id_prodotto) throws SQLException {
        try (Connection connection = SqlDao.getConnection()) {
            String query = "DELETE FROM review WHERE id_cliente=? and id_prodotto=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_account);
                ps.setInt(2, id_prodotto);

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public ArrayList<Review> searchAllReviewWithProduct(int id_product) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT *  FROM review,product AS pro ,account AS acc WHERE review.id_prodotto=pro.id_prodotto and review.id_cliente=acc.id_cliente and pro.id_prodotto=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_product);
                ResultSet rs = ps.executeQuery();
                Review review = null;
                ReviewExtractor reviewExtractor = new ReviewExtractor();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();
                ArrayList<Review> reviews = new ArrayList<>();
                while (rs.next()) {
                    review = reviewExtractor.extract(rs);
                    prodotto = productExtractor.extract(rs);
                    account = accountExtractor.extract(rs);
                    review.setProdotto(prodotto);
                    review.setAccount(account);
                    reviews.add(review);
                }
                return reviews;
            }
        }
    }

    @Override
    public Review searchReviewWithProductAndAccount(int id_product, int id_account) throws SQLException {
        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT *  FROM review,product AS pro WHERE review.id_prodotto=pro.id_prodotto and pro.id_prodotto=? and review.id_cliente=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_product);
                ps.setInt(2, id_account);
                ResultSet rs = ps.executeQuery();
                Review review = null;
                ReviewExtractor reviewExtractor = new ReviewExtractor();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                if (rs.next()) {
                    review = reviewExtractor.extract(rs);
                    prodotto = productExtractor.extract(rs);
                    review.setProdotto(prodotto);
                }
                return review;
            }
        }
    }


}
