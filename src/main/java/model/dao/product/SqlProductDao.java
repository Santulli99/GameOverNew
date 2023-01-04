package model.dao.product;

import model.entity.Category;
import model.dao.category.CategoryExtractor;
import model.entity.Order;
import model.entity.Product;
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

    public  ArrayList<Product> searchProductsvetrina(int idpiattaforma ) throws SQLException{

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,platform AS pla WHERE pro.id_piattaforma=pla.id and pla.id=? LIMIT 15;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1,idpiattaforma);
                ResultSet rs = ps.executeQuery();
                Product product = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = productExtractor.extract(rs);
                    platform = platformExtractor.extract(rs);
                    product.setPlatform(platform);
                    products.add(product);
                }
                return products;
            }
        }

    }

    public ArrayList<Product> searchProductsByName(String nome) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro WHERE (pro.nome=?);";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, nome);
                ResultSet rs = ps.executeQuery();
                Product product = null;
                ProductExtractor extractor = new ProductExtractor();
                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = extractor.extract(rs);
                    products.add(product);
                }
                return products;
            }
        }
    }

    @Override
    public ArrayList<Product> searchProductsByPrice(double price_MAX) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro WHERE (pro.prezzo=?);";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setDouble(1, price_MAX);

                ResultSet rs = ps.executeQuery();

                Product product = null;
                ProductExtractor extractor = new ProductExtractor();
                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = extractor.extract(rs);
                    products.add(product);
                }
                return products;
            }
        }
    }


    public  ArrayList<Product> searchProductsByCategoryAndPlatform1() throws SQLException{

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,category AS cat, platform AS pla " +
                    "WHERE pro.id_categoria=cat.id_categoria AND pro.id_piattaforma=pla.id ;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {

                ResultSet rs = ps.executeQuery();
                Product product = null;
                ProductExtractor extractor = new ProductExtractor();
                PlatformExtractor platformExtractor=new PlatformExtractor();
                CategoryExtractor categoryExtractor=new CategoryExtractor();
                Category category;
                Platform platform;
                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = extractor.extract(rs);
                    category=categoryExtractor.extract(rs);
                    platform=platformExtractor.extract(rs);
                    products.add(product);
                    product.setCategory(category);
                    product.setPlatform(platform);
                }
                return products;
            }
        }
    }






  public  ArrayList<Product> searchProductsByCategoryAndPlatform(String category,int idPlatform) throws SQLException{

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,category AS cat, platform AS pla " +
                    "WHERE pro.id_categoria=cat.id_categoria AND pro.id_piattaforma=pla.id " +
                    "AND cat.nome=? AND pla.id=? ;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, category);
                ps.setInt(2,idPlatform);

                ResultSet rs = ps.executeQuery();

                Product product = null;
                ProductExtractor extractor = new ProductExtractor();
                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = extractor.extract(rs);
                    products.add(product);
                }
                return products;
            }
        }
    }


    @Override
    public ArrayList<Product> searchProductsByCategory(String category) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,category AS cat " +
                    "WHERE pro.id_catgoria=cat.id_categoria " +
                    "AND cat.nome=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, category);

                ResultSet rs = ps.executeQuery();

                Product product = null;
                ProductExtractor extractor = new ProductExtractor();
                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = extractor.extract(rs);
                    products.add(product);
                }
                return products;
            }
        }
    }

    @Override
    public ArrayList<Product> searchProductsByDate(LocalDate date) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro WHERE (pro.data_uscita=?);";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setDate(1, Date.valueOf(date));

                ResultSet rs = ps.executeQuery();

                Product product = null;
                ProductExtractor extractor = new ProductExtractor();
                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = extractor.extract(rs);
                    products.add(product);
                }
                return products;
            }
        }
    }

    @Override
    public Product searchProduct(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro WHERE id_prodotto=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                Product product = null;
                ProductExtractor productExtractor = new ProductExtractor();
                if (rs.next()) {
                    product = productExtractor.extract(rs);
                }
                return product;
            }
        }

    }

    @Override
    public ArrayList<Product> searchAllProducts() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro ;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                Product product = null;
                ProductExtractor productExtractor = new ProductExtractor();
                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = productExtractor.extract(rs);
                    products.add(product);
                }
                return products;
            }
        }
    }

    @Override
    public boolean createProduct(Product product) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query1 = "INSERT INTO product(id_categoria, nome, descrizione, path_img, prezzo, data_uscita,id_piattaforma) " +
                    "VALUES (?,?,?,?,?,?,?);";

            try (PreparedStatement ps1 = connection.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS)) {
                ps1.setInt(1, product.getCategory().getId());
                ps1.setString(2, product.getProductName());
                ps1.setString(3, product.getDescription());
                ps1.setString(4, product.getCover());
                ps1.setDouble(5, product.getPrice());
                ps1.setDate(6, Date.valueOf(product.getDate()));
                ps1.setInt(7, product.getPlatform().getId());
                int rows = ps1.executeUpdate();

                ResultSet rs = ps1.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                product.setId(id);

                return rows == 1;
            }
        }
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "UPDATE product SET descrizione=?,prezzo=?,nome=? WHERE id_prodotto=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, product.getDescription());
                ps.setDouble(2, product.getPrice());
                ps.setString(3, product.getProductName());
                ps.setInt(4, product.getId());

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
    public Product searchProductWithCategory(int id_pro) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,category AS cat WHERE pro.id_categoria=cat.id_categoria " +
                    "AND pro.id_prodotto=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_pro);
                ResultSet rs = ps.executeQuery();

                Product product = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Category category = null;
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                if (rs.next()) {
                    product = productExtractor.extract(rs);
                    category = categoryExtractor.extract(rs);
                    product.setCategory(category);
                }

                return product;
            }
        }
    }

    @Override
    public Product searchProductWithPlatforms(int id_pro) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {

            String query = "SELECT *  FROM product AS pro,platform AS pla WHERE pro.id_piattaforma=pla.id" +
                    "AND pro.id_prodotto=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_pro);
                ResultSet rs = ps.executeQuery();
                Product product = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                if (rs.next()) {
                    product = productExtractor.extract(rs);
                    platform = platformExtractor.extract(rs);
                    product.setPlatform(platform);
                }
                return product;
            }
        }
    }

    public Product searchProductWithPlatformsAndCategory(int id_pro) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {

            String query = "SELECT *  FROM product AS pro,platform AS pla,category AS cat WHERE pro.id_piattaforma=pla.id " +
                    "AND pro.id_categoria=cat.id_categoria" +
                    " AND pro.id_prodotto=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_pro);
                ResultSet rs = ps.executeQuery();
                Product product = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                Category category =null;
                CategoryExtractor categoryExtractor= new CategoryExtractor();

                if (rs.next()) {
                    product = productExtractor.extract(rs);
                    platform = platformExtractor.extract(rs);
                    category=categoryExtractor.extract(rs);
                    product.setPlatform(platform);
                    product.setCategory(category);
                }
                return product;
            }
        }
    }

    @Override
    public Product searchProductWithOrders(int id_pro) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,orders AS ord,order_product AS op " +
                    "WHERE pro.id_prodotto=op.id_prodotto AND op.id_ordine=ord.id_ordine" +
                    "AND pro.id_prodotto=?;";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_pro);
                ResultSet rs = ps.executeQuery();

                Product product = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Order order = null;
                OrderExtractor orderExtractor = new OrderExtractor();
                if (rs.next()) {
                    product = productExtractor.extract(rs);
                    product.getOrders().add(orderExtractor.extract(rs));
                    while (rs.next()) {
                        product.getOrders().add(orderExtractor.extract(rs));
                    }
                }
                return product;
            }
        }
    }

    @Override
    public ArrayList<Product> searchProductWithCategory() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,category AS cat WHERE pro.id_categoria=cat.id_categoria ;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                Product product = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Category category = null;
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = productExtractor.extract(rs);
                    category = categoryExtractor.extract(rs);
                    product.setCategory(category);
                    products.add(product);
                }
                return products;
            }
        }
    }

    @Override
    public ArrayList<Product> searchProductWithPlatform(int idpiattaforma) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM product AS pro,platform AS pla WHERE pro.id_piattaforma=pla.id and pla.id=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1,idpiattaforma);
                ResultSet rs = ps.executeQuery();
                Product product = null;
                ProductExtractor productExtractor = new ProductExtractor();
                Platform platform = null;
                PlatformExtractor platformExtractor = new PlatformExtractor();
                ArrayList<Product> products = new ArrayList<>();
                while (rs.next()) {
                    product = productExtractor.extract(rs);
                    platform = platformExtractor.extract(rs);
                    product.setPlatform(platform);
                    products.add(product);
                }
                return products;
            }
        }

    }

    @Override//hashmap
    public ArrayList<Product> searchProductWithOrders() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM orders AS ord,product AS pro,order_product AS op " +
                    "WHERE ord.id_ordine=op.id_ordine AND op.id_prodotto=pro.id_prodotto;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                Order order = null;
                OrderExtractor orderExtractor = new OrderExtractor();
                Product product = null;
                ProductExtractor productExtractor = new ProductExtractor();
                HashMap<Integer, Product> hashMap = new LinkedHashMap();
                while (rs.next()) {
                    int id_prodotto = rs.getInt("id_prodotto");
                    //relazione N:
                    if (!hashMap.containsKey(id_prodotto)) {
                        product = productExtractor.extract(rs);
                        hashMap.put(id_prodotto, product);
                    }
                    order = orderExtractor.extract(rs);
                    hashMap.get(id_prodotto).getOrders().add(order);
                }

                return new ArrayList<>(hashMap.values());
            }
        }

    }
}
