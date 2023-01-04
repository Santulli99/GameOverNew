package model.dao.order;

import model.entity.Order;
import model.dao.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderExtractor implements ResultSetExtractor <Order> {


    @Override
    public Order extract(ResultSet resultSet) throws SQLException {
        Order order=new Order();
        order.setId(resultSet.getInt("ord.id_ordine"));
        order.setNum_product(resultSet.getInt("ord.n_prodotti"));
        order.setDate(resultSet.getDate("ord.data_ordine").toLocalDate());
        return order;
    }
}
