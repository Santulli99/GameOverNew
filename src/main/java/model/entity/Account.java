package model.entity;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Account {

    private String email,password,username;
    boolean admin;

    private ArrayList<Review> reviews;

    int id;
    private Address address;

    private ListaDesideri listaDesideri;




    // rappresenta il mapping con la tabella dati anagrafici
    private DataClient dataClient;

  // rappresenta il mapping con la tabella ordini
  // cardinalità N dalla parte di Account e quindi aggiungo una lista di Ordini
    private ArrayList<Order> orders=new ArrayList<>();

    //model.dao.account
    public Account() {
        super();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DataClient getDataClient() {
        return dataClient;
    }

    public void setDataClient(DataClient dataClient) {
        this.dataClient = dataClient;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            MessageDigest digest =
                    MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.password = String.format("%040x", new
                    BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public ListaDesideri getWishlist() {
        return listaDesideri;
    }

    public void setWishlist(ListaDesideri listaDesideri) {
        this.listaDesideri = listaDesideri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }


}
