package model.dao.platform;

import model.entity.Platform;
import model.entity.Product;
import model.dao.product.ProductExtractor;
import model.dao.storage.SqlDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SqlPlatformDao implements PlatformDao<SQLException> {


    public SqlPlatformDao() {
        super();
    }


    @Override
    public Platform searchPlatform(int id_platform) throws SQLException {
        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT *  FROM platform AS pla WHERE id=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_platform);
                ResultSet rs = ps.executeQuery();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                if (rs.next()) {
                    platform = platformExtractor.extract(rs);
                }
                return platform;
            }
        }
    }

    @Override
    public ArrayList<Platform> searchAllPlatform() throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT *  FROM platform AS pla;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Platform> platforms = new ArrayList<>();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                while (rs.next()) {
                    platform = platformExtractor.extract(rs);
                    platforms.add(platform);
                }
                return platforms;
            }
        }

    }

    @Override
    public boolean createPlatform(Platform platform) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "INSERT INTO platform(nome) VALUES (?);";

            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, platform.getPlatformName());

                int rows = ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                platform.setId(id);

                return rows == 1;
            }
        }
    }

    //non ha bisogno di modifica
   /* public boolean updatePlatform(Platform model.dao.platform) throws SQLException {
        String query="UPDATE model.dao.platform SET (descrizione=?) WHERE id_categoria=?;";
        PreparedStatement ps=connection.prepareStatement(query);

        return false;
    }*/

    @Override
    public Platform searchPlatformWithProduct(int id_platform) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT *  FROM platform AS pla,platform_product AS pp,product AS pro " +
                    "WHERE pp.id_prodotto=pro.id_prodotto AND pp.id_piattaforma=pla.id " +
                    "AND (id=?);";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_platform);
                ResultSet rs = ps.executeQuery();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                if (rs.next()) {
                    platform = platformExtractor.extract(rs);   //estratto la piattaforma
                    // model.dao.platform.setProducts();   //setto l'arraylist
                    ProductExtractor productExtractor = new ProductExtractor();
                    platform.getProducts().add(productExtractor.extract(rs));
                    while (rs.next()) {
                        platform.getProducts().add(productExtractor.extract(rs));
                    }
                }
                return platform;
            }
        }

    }

    @Override
    public ArrayList<Platform> searchAllPlatformWithProducts() throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT *  FROM platform AS pla,platform_product AS pp,product AS pro " +
                    "WHERE pp.id_prodotto=pro.id_prodotto AND pp.id_piattaforma=pla.id;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                Platform platform = null;
                Product product = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                ProductExtractor productExtractor = new ProductExtractor();
                HashMap<Integer, Platform> platformHashMap = new LinkedHashMap<>();
                while (rs.next()) {
                    int platformId = rs.getInt("pla.id");
                    if (!platformHashMap.containsKey(platformId)) {
                        platform = platformExtractor.extract(rs);
                        platformHashMap.put(platformId, platform);
                    }
                    product = productExtractor.extract(rs);
                    platformHashMap.get(platformId).getProducts().add(product);
                }
                return new ArrayList<>(platformHashMap.values());
            }
        }
    }
}
