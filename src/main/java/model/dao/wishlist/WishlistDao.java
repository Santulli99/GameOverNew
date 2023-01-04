package model.dao.wishlist;

import model.entity.Account;
import model.entity.Product;
import model.entity.Wishlist;

public interface WishlistDao<E extends Exception> {

    boolean deleteWishlist(int idCliente,int id_product) throws E;
    boolean createWishlist(Account account, Product product) throws E;
    Wishlist searchWishlistWithAccount(Account account) throws E;


}
