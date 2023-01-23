package model.entity;

import java.util.ArrayList;

public class ListaDesideri {
    private Account account;
    private ArrayList<Prodotto> prodotti;

    public ArrayList<Prodotto> getProdotti() {
        return prodotti;
    }

    public ListaDesideri() { super(); }

    public ListaDesideri(Account account, ArrayList<Prodotto> prodotto) {
        this.account = account;
        this.prodotti = prodotto;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public void setProducts(ArrayList<Prodotto> prodotto) {
        this.prodotti = prodotto;
    }

    public boolean containsListaDesideri(Prodotto pr){
        return prodotti.contains(pr);
    }
}
