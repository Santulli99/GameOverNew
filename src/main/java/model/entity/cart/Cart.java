package model.entity.cart;

import model.entity.Product;
import model.entity.cart.CartItem;

import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> cartItems;
    private boolean isPresent;

    public Cart() {
        this.cartItems = new ArrayList<>();
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void addProduct(Product p){
        cartItems.add(new CartItem(p));
    }

    public void removeProduct(Product p){

        for (int i=0; i<cartItems.size(); i++){

            if(p.equals(cartItems.get(i).getItem())){
                cartItems.remove(i);
            }

        }
    }

    public boolean isPresent(Product p){

        for(int i=0; i<cartItems.size(); i++){

            if( p.equals(cartItems.get(i).getItem())){

                return true;
            }
        }
        return  false;
    }



    public double totalPrice(){

        double total=0;

        for (CartItem cartItem: cartItems) {

            total+=cartItem.getItem().getPrice();
        }
        return total;
    }

}
