package Application_Logic.entity.cart;

import Application_Logic.entity.Prodotto;

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
