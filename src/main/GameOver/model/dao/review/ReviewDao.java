package model.dao.review;

import model.entity.Account;
import model.entity.Review;

import java.util.ArrayList;

public interface ReviewDao <E extends Exception> {
    boolean createReview(Review review) throws E;
    boolean updateReview(Review review) throws E;
    boolean deleteReview(int id_account,int id_prodotto) throws E;
    //per l'amministratore
    ArrayList<Review> searchAllReviewWithProduct(int id_product) throws E;
    //per l'utente
    ArrayList<Review> searchAllReviewWithAccount(int id_account) throws E;
    //Review searchReviewWithAccountAndProduct(int id_account,int id_product) throws E;

    Review searchReviewWithProductAndAccount(int id_product,int id_account) throws E;
}
