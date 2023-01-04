package model.dao.product;

import model.entity.Product;
import model.dao.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExtractor implements ResultSetExtractor<Product> {
    @Override
    public Product extract(ResultSet resultSet) throws SQLException {
        Product product=new Product();
        product.setId(resultSet.getInt("pro.id_prodotto"));
        product.setPrice(resultSet.getDouble("pro.prezzo"));
        product.setProductName(resultSet.getString("pro.nome"));
        product.setDescription(resultSet.getString("pro.descrizione"));
        product.setCover(resultSet.getString("pro.path_img"));
        product.setDate(resultSet.getDate("pro.data_uscita").toLocalDate());
        return product;
    }
}
