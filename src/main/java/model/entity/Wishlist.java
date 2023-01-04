package model.entity;

import java.util.ArrayList;

public class Wishlist {
    private Account account;
    private ArrayList<Product> product;

    public Wishlist() { super(); }

    public Wishlist(Account account, ArrayList<Product> product) {
        this.account = account;
        this.product = product;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }
}
