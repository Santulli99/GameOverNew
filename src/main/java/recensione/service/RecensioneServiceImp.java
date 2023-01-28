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
    public boolean aggiungiRecensione(Account account,Prodotto prodotto,String descrizione,double valutazione){
        try {
            Review review=new Review();
            review.setAccount(account);
            review.setProduct(prodotto);
            review.setDescrizione(descrizione);
            review.setValutazione(valutazione);
            if(reviewDao.createReview(review))
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean modificaRecensione(String descrizione,double valutazione) {
        try {
            Review review=new Review();
            review.setValutazione(valutazione);
            review.setDescrizione(descrizione);
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
            recensioni=reviewDao.searchAllReviewWithProduct(prodotto.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recensioni;
    }

    @Override
    public ArrayList<Review> cercaRecensioniPerUtente(Account account) {
        try {
            recensioni=reviewDao.searchAllReviewWithAccount(account.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recensioni;
    }
}