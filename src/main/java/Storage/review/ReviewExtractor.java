package Storage.review;

import Storage.storage.ResultSetExtractor;
import Application_Logic.entity.Review;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewExtractor implements ResultSetExtractor<Review> {
    @Override
    public Review extract(ResultSet resultSet) throws SQLException {
        Review review=new Review();
        review.setDescrizione(resultSet.getString("review.descrizione"));
        review.setValutazione(resultSet.getInt("review.valutazione"));
        review.setTitolo(resultSet.getString("review.titolo"));
        return review;
    }
}