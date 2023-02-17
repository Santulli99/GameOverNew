package Storage.product;


import Application_Logic.entity.Prodotto;
import Storage.storage.SqlDao;
import java.sql.*;
import java.util.ArrayList;

public class SqlProductDao implements  ProductDao<SQLException>{

    public SqlProductDao() {
        super();
    }
    @Override

    public  ArrayList<Prodotto> searchProductsvetrina(String piattaforma) throws SQLException{

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro WHERE pro.piattaforma=? LIMIT 15;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1,piattaforma);
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                ArrayList<Prodotto> prodottos = new ArrayList<>();
                while (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                    prodottos.add(prodotto);
                }
                return prodottos;
            }
        }

    }

  public  ArrayList<Prodotto> searchProductsByCategoryAndPlatform(String category, String platform) throws SQLException{

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro WHERE  pro.categoria=? AND pro.piattaforma=? ;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, category);
                ps.setString(2,platform);

                ResultSet rs = ps.executeQuery();

                Prodotto prodotto = null;
                ProductExtractor extractor = new ProductExtractor();
                ArrayList<Prodotto> prodottos = new ArrayList<>();
                while (rs.next()) {
                    prodotto = extractor.extract(rs);
                    prodottos.add(prodotto);
                }
                return prodottos;
            }
        }
    }


    @Override
    public Prodotto searchProduct(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro WHERE id_prodotto=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                if (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                }
                return prodotto;
            }
        }

    }

    @Override
    public ArrayList<Prodotto> searchAllProducts() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro ;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                ArrayList<Prodotto> prodottos = new ArrayList<>();
                while (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                    prodottos.add(prodotto);
                }
                return prodottos;
            }
        }
    }

    @Override
    public boolean createProduct(Prodotto prodotto) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query1 = "INSERT INTO product(categoria, nome, descrizione, path_img, prezzo, data_uscita,piattaforma) " +
                    "VALUES (?,?,?,?,?,?,?);";

            try (PreparedStatement ps1 = connection.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS)) {
                ps1.setString(1, prodotto.getCategoryName());
                ps1.setString(2, prodotto.getProductName());
                ps1.setString(3, prodotto.getDescription());
                ps1.setString(4, prodotto.getCover());
                ps1.setDouble(5, prodotto.getPrice());
                ps1.setDate(6, Date.valueOf(prodotto.getDate()));
                ps1.setString(7, prodotto.getPlatformName());
                int rows = ps1.executeUpdate();

                ResultSet rs = ps1.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                prodotto.setId(id);

                return rows == 1;
            }
        }
    }

    @Override
    public boolean updateProduct(Prodotto prodotto) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "UPDATE product SET descrizione=?,prezzo=?,nome=? WHERE id_prodotto=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, prodotto.getDescription());
                ps.setDouble(2, prodotto.getPrice());
                ps.setString(3, prodotto.getProductName());
                ps.setInt(4, prodotto.getId());

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }

    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "DELETE FROM product WHERE (id_prodotto=?);";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1,id);

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }

    }

    @Override
    public boolean updateProductValuation(Prodotto prodotto) throws SQLException {
        try(Connection connection=SqlDao.getConnection()) {
            String query = "UPDATE product SET valutazione_media=? WHERE id_prodotto=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setDouble(1, prodotto.getValutazioneMedia());
                ps.setDouble(2, prodotto.getId());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

}
