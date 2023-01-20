package model.dao.category;

import model.entity.Category;
import model.entity.Prodotto;
import model.dao.product.ProductExtractor;
import model.dao.storage.SqlDao;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SqlCategoryDao implements CategoryDao<SQLException>{

    public SqlCategoryDao() {
        super();
    }

    @Override
    public Category searchCategory(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
        String query="SELECT *  FROM category AS cat WHERE id_categoria=?;";

        try(PreparedStatement ps=connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Category category = null;
            CategoryExtractor categoryExtractor = new CategoryExtractor();
            if (rs.next()) {
                category = categoryExtractor.extract(rs);
            }
            return category;
        }
        }
    }

    @Override
    public ArrayList<Category> searchAllCategory() throws SQLException {
        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM category AS cat;";
            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<Category> categories = new ArrayList<>();
                Category category = null;
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                while (rs.next()) {
                    category = categoryExtractor.extract(rs);
                    categories.add(category);
                }
                return categories;
            }
        }
    }

    @Override
    public boolean createCategory(Category category) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "INSERT INTO category( nome, descrizione) VALUES (?,?);";

            try(PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, category.getCategoryName());
                ps.setString(2, category.getDescription());

                int rows = ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                category.setId(id);

                return rows == 1;
            }
        }
    }

    @Override
    public boolean updateCategory(Category category) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "UPDATE category SET (descrizione=?) WHERE id_categoria=?;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, category.getDescription());
                ps.setInt(2, category.getId());

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean deleteCategory(Category category) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "DELETE FROM category WHERE  (id_categoria=?);";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, category.getId());

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public Category searchCategoryWithProducts(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM category AS cat,product AS pro " +
                    "WHERE cat.id_categoria=pro.id_categoria " +
                    "AND (id_categoria=?);";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                Category category = null;
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();

                if (rs.next()) {

                    //relazione 1:N
                    //mi salvo la categoria e il primo prodotto
                    category = categoryExtractor.extract(rs);
                    prodotto = productExtractor.extract(rs);

                    //corretto l'array non era allocato percio lui fa new ArrayList
                    category.getProducts().add(prodotto);//aggiungo primo prodotto

                    while (rs.next()) {
                        prodotto = productExtractor.extract(rs);
                        category.getProducts().add(prodotto);
                    }
                }
                return category;
            }
        }

    }

    @Override
    public ArrayList<Category> searchAllCategoryWithProducts() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT *  FROM category AS cat,product AS pro " +
                    "WHERE cat.id_categoria=pro.id_categoria;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {

                ResultSet rs = ps.executeQuery();

                Category category = null;
                CategoryExtractor categoryExtractor = new CategoryExtractor();
                Prodotto prodotto = null;
                ProductExtractor productExtractor = new ProductExtractor();
                HashMap<Integer, Category> hashMap = new LinkedHashMap();

                ArrayList<Category> categories = new ArrayList<>();

                while (rs.next()) {

                    int id_categoria = rs.getInt("id_categoria");

                    //relazione N:N

                    if (!hashMap.containsKey(id_categoria)) {
                        category = categoryExtractor.extract(rs);
                        hashMap.put(id_categoria, category);
                    }

                    prodotto = productExtractor.extract(rs);
                    hashMap.get(id_categoria).getProducts().add(prodotto);
                }

                //categories = (ArrayList<Category>) hashMap.values();

                return new ArrayList<>(hashMap.values());
            }
        }

    }
}
