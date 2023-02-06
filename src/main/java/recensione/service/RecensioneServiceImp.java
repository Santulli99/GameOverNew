package recensione.service;

import model.dao.product.SqlProductDao;
import model.dao.review.SqlReviewDao;
import model.entity.Account;
import model.entity.Prodotto;
import model.entity.Review;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecensioneServiceImp implements RecensioneService {
    private SqlReviewDao reviewDao=new SqlReviewDao();
    private SqlProductDao productDao=new SqlProductDao();
    private ArrayList<Review> recensioni=new ArrayList<>();

    public RecensioneServiceImp() {
    }

    @Override
    public boolean aggiungiRecensione(Review review){
        try {
            if(reviewDao.createReview(review))
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean modificaRecensione(Review review) {
        try {
            if(reviewDao.updateReview(review))
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean rimuoviRecensione(Account account,Prodotto prodotto) {
        try {
            int id_account=account.getId();
            int id_prodotto=prodotto.getId();
            if(reviewDao.deleteReview(id_account,id_prodotto))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public ArrayList<Review> cercaRecensioniPerProdotto(Prodotto prodotto) {
        try {
            if((recensioni=reviewDao.searchAllReviewWithProduct(prodotto.getId()))!=null)
                return recensioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public Review cercaRecensionePerProdotto(Prodotto prodotto,Account account) {
        Review review=new Review();
        try {
            if((review=reviewDao.searchReviewWithProductAndAccount(prodotto.getId(),account.getId()))!=null)
                return review;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
