package model.dao.product;

import model.entity.Category;
import model.dao.category.CategoryExtractor;
import model.entity.Order;
import model.entity.Prodotto;
import model.dao.order.OrderExtractor;
import model.entity.Platform;
import model.dao.platform.PlatformExtractor;
import model.dao.storage.SqlDao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SqlProductDao implements  ProductDao<SQLException>{

    public SqlProductDao() {
        super();
    }
    @Override

    public  ArrayList<Prodotto> searchProductsvetrina(int idpiattaforma ) throws SQLException{

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,platform AS pla WHERE pro.id_piattaforma=pla.id and pla.id=? LIMIT 15;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1,idpiattaforma);
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                ArrayList<Prodotto> prodottos = new ArrayList<>();
                while (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                    platform = platformExtractor.extract(rs);
                    prodotto.setPlatform(platform);
                    prodottos.add(prodotto);
                }
                return prodottos;
            }
        }

    }

    public ArrayList<Prodotto> searchProductsByName(String nome) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro WHERE (pro.nome=?);";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, nome);
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
    public ArrayList<Prodotto> searchProductsByPrice(double price_MAX) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro WHERE (pro.prezzo=?);";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setDouble(1, price_MAX);

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


    public  ArrayList<Prodotto> searchProductsByCategoryAndPlatform1() throws SQLException{

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,category AS cat, platform AS pla " +
                    "WHERE pro.id_categoria=cat.id_categoria AND pro.id_piattaforma=pla.id ;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {

                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                ProductExtractor extractor = new ProductExtractor();
                PlatformExtractor platformExtractor=new PlatformExtractor();
                CategoryExtractor categoryExtractor=new CategoryExtractor();
                Category category;
                Platform platform;
                ArrayList<Prodotto> prodottos = new ArrayList<>();
                while (rs.next()) {
                    prodotto = extractor.extract(rs);
                    category=categoryExtractor.extract(rs);
                    platform=platformExtractor.extract(rs);
                    prodottos.add(prodotto);
                    prodotto.setCategory(category);
                    prodotto.setPlatform(platform);
                }
                return prodottos;
            }
        }
    }






  public  ArrayList<Prodotto> searchProductsByCategoryAndPlatform(String category, int idPlatform) throws SQLException{

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,category AS cat, platform AS pla " +
                    "WHERE pro.id_categoria=cat.id_categoria AND pro.id_piattaforma=pla.id " +
                    "AND cat.nome=? AND pla.id=? ;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, category);
                ps.setInt(2,idPlatform);

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
    public ArrayList<Prodotto> searchProductsByCategory(String category) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,category AS cat " +
                    "WHERE pro.id_catgoria=cat.id_categoria " +
                    "AND cat.nome=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, category);

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
    public ArrayList<Prodotto> searchProductsByDate(LocalDate date) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro WHERE (pro.data_uscita=?);";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setDate(1, Date.valueOf(date));

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
            String query1 = "INSERT INTO product(id_categoria, nome, descrizione, path_img, prezzo, data_uscita,id_piattaforma) " +
                    "VALUES (?,?,?,?,?,?,?);";

            try (PreparedStatement ps1 = connection.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS)) {
                ps1.setInt(1, prodotto.getCategory().getId());
                ps1.setString(2, prodotto.getProductName());
                ps1.setString(3, prodotto.getDescription());
                ps1.setString(4, prodotto.getCover());
                ps1.setDouble(5, prodotto.getPrice());
                ps1.setDate(6, Date.valueOf(prodotto.getDate()));
                ps1.setInt(7, prodotto.getPlatform().getId());
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
    public Prodotto searchProductWithCategory(int id_pro) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,category AS cat WHERE pro.id_categoria=cat.id_categoria " +
                    "AND pro.id_prodotto=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_pro);
                ResultSet rs = ps.executeQuery();

                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Category category = null;
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                if (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                    category = categoryExtractor.extract(rs);
                    prodotto.setCategory(category);
                }

                return prodotto;
            }
        }
    }

    @Override
    public Prodotto searchProductWithPlatforms(int id_pro) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {

            String query = "SELECT *  FROM product AS pro,platform AS pla WHERE pro.id_piattaforma=pla.id" +
                    "AND pro.id_prodotto=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_pro);
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                if (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                    platform = platformExtractor.extract(rs);
                    prodotto.setPlatform(platform);
                }
                return prodotto;
            }
        }
    }

    public Prodotto searchProductWithPlatformsAndCategory(int id_pro) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {

            String query = "SELECT *  FROM product AS pro,platform AS pla,category AS cat WHERE pro.id_piattaforma=pla.id " +
                    "AND pro.id_categoria=cat.id_categoria" +
                    " AND pro.id_prodotto=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_pro);
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                Category category =null;
                CategoryExtractor categoryExtractor= new CategoryExtractor();

                if (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                    platform = platformExtractor.extract(rs);
                    category=categoryExtractor.extract(rs);
                    prodotto.setPlatform(platform);
                    prodotto.setCategory(category);
                }
                return prodotto;
            }
        }
    }

    @Override
    public Prodotto searchProductWithOrders(int id_pro) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,orders AS ord,order_product AS op " +
                    "WHERE pro.id_prodotto=op.id_prodotto AND op.id_ordine=ord.id_ordine" +
                    "AND pro.id_prodotto=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_pro);
                ResultSet rs = ps.executeQuery();

                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Order order = null;
                OrderExtractor orderExtractor = new OrderExtractor();
                if (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                    prodotto.getOrders().add(orderExtractor.extract(rs));
                    while (rs.next()) {
                        prodotto.getOrders().add(orderExtractor.extract(rs));
                    }
                }
                return prodotto;
            }
        }
    }

    @Override
    public ArrayList<Prodotto> searchProductWithCategory() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,category AS cat WHERE pro.id_categoria=cat.id_categoria ;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Category category = null;
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                ArrayList<Prodotto> prodottos = new ArrayList<>();
                while (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                    category = categoryExtractor.extract(rs);
                    prodotto.setCategory(category);
                    prodottos.add(prodotto);
                }
                return prodottos;
            }
        }
    }

    @Override
    public ArrayList<Prodotto> searchProductWithPlatform(int idpiattaforma) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,platform AS pla WHERE pro.id_piattaforma=pla.id and pla.id=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1,idpiattaforma);
                ResultSet rs = ps.executeQuery();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                ArrayList<Prodotto> prodottos = new ArrayList<>();
                while (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                    platform = platformExtractor.extract(rs);
                    prodotto.setPlatform(platform);
                    prodottos.add(prodotto);
                }
                return prodottos;
            }
        }

    }

    @Override//hashmap
    public ArrayList<Prodotto> searchProductWithOrders() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM orders AS ord,product AS pro,order_product AS op " +
                    "WHERE ord.id_ordine=op.id_ordine AND op.id_prodotto=pro.id_prodotto;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                Order order = null;
                OrderExtractor orderExtractor = new OrderExtractor();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                HashMap<Integer, Prodotto> hashMap = new LinkedHashMap();
                while (rs.next()) {
                    int id_prodotto = rs.getInt("id_prodotto");
                    //relazione N:
                    if (!hashMap.containsKey(id_prodotto)) {
                        prodotto = productExtractor.extract(rs);
                        hashMap.put(id_prodotto, prodotto);
                    }
                    order = orderExtractor.extract(rs);
                    hashMap.get(id_prodotto).getOrders().add(order);
                }

                return new ArrayList<>(hashMap.values());
            }
        }

    }
}
