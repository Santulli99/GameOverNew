package model.entity.cart;

import model.entity.Prodotto;

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

    public void addProduct(Prodotto p){
        cartItems.add(new CartItem(p));
    }

    public void removeProduct(Prodotto p){

        for (int i=0; i<cartItems.size(); i++){

            if(p.equals(cartItems.get(i).getItem())){
                cartItems.remove(i);
            }

        }
    }

    public boolean isPresent(Prodotto p){

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
