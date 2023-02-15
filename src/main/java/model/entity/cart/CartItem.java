package model.entity.cart;

import model.entity.Prodotto;

public class CartItem {

    private Prodotto item;

    public CartItem(Prodotto prodotto) {
        this.item = prodotto;
    }

    public Prodotto getItem() {
        return item;
    }

    public void setItem(Prodotto item) {
        this.item = item;
    }

}
