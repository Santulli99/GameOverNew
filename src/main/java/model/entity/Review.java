package model.entity;

public class Review {
    private String descrizione;
    private double valutazione;
    private  Account account;
    private Product product;

    public Review(){super(); }

    public Review(String descrizione, double valutazione, Account account, Product product) {
        this.descrizione = descrizione;
        this.valutazione = valutazione;
        this.account = account;
        this.product = product;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getValutazione() {
        return valutazione;
    }

    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
