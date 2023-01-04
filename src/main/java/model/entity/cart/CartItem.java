package model.entity.cart;

import model.entity.Product;

public class CartItem {

    private Product item;

    public CartItem(Product product) {
        this.item = product;
    }

    public Product getItem() {
        return item;
    }

    public void setItem(Product item) {
        this.item = item;
    }

}
