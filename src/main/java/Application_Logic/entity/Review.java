package Application_Logic.entity;

public class Review {
    private String descrizione;
    private String titolo;

    private double valutazione;
    private Account account;
    private Prodotto prodotto;

    public Review() {
        super();
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
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

}
