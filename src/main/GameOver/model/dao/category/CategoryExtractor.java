package model.dao.category;

import model.entity.Category;
import model.dao.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryExtractor implements ResultSetExtractor<Category> {

    @Override
    public Category extract(ResultSet resultSet) throws SQLException {
        Category category=new Category();
        category.setId(resultSet.getInt("cat.id_categoria"));
        category.setCategoryName(resultSet.getString("cat.nome"));
        category.setDescription(resultSet.getString("cat.descrizione"));
        return category;
    }
}
