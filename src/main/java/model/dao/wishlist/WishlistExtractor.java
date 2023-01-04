package model.dao.wishlist;

import model.dao.storage.ResultSetExtractor;
import model.entity.Wishlist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistExtractor implements ResultSetExtractor<Wishlist> {

    @Override
    public Wishlist extract(ResultSet resultSet) throws SQLException {
        return null;
    }
}
