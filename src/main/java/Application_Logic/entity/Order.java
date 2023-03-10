package Application_Logic.entity;

import Application_Logic.entity.cart.Cart;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class Order {

    private int id,num_product;
    private LocalDate date;
    private Account account;
    private double total;
    private String dataString;
    private ArrayList<Prodotto> products =new ArrayList<>();
    private Cart cart;



    public Order() {
        super();
    }

    public double getTotal() {
        for(int i = 0; i< products.size(); i++){
            total+= products.get(i).getPrice();
        }
        return total;
    }


    public int getId() {
        return id;
    }

    public String getDataString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withLocale(Locale.ITALY);
        return date.format(formatter);
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getNum_product() {
        return num_product;
    }

    public void setNum_product(int num_product) {
        this.num_product = num_product;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<Prodotto> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Prodotto> prodottos) {
        this.products = prodottos;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId() == order.getId();
    }


}
