package model.entity;

import java.util.ArrayList;

public class ListaDesideri {
    private Account account;
    private ArrayList<Prodotto> prodotto;

    public ListaDesideri() { super(); }

    public ListaDesideri(Account account, ArrayList<Prodotto> prodotto) {
        this.account = account;
        this.prodotto = prodotto;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<Prodotto> getProducts() {
        return prodotto;
    }

    public void setProducts(ArrayList<Prodotto> prodotto) {
        this.prodotto = prodotto;
    }

    public boolean containsListaDesideri(Prodotto pr){
        return prodotto.contains(pr);
    }
}
