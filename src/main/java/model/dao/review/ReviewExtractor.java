package model.dao.review;

import model.dao.storage.ResultSetExtractor;
import model.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewExtractor implements ResultSetExtractor<Review> {
    @Override
    public Review extract(ResultSet resultSet) throws SQLException {
        Review review=new Review();
        review.setDescrizione(resultSet.getString("review.descrizione"));
        review.setValutazione(resultSet.getInt("review.valutazione"));
        return review;
    }
}