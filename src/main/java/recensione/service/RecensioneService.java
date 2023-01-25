package recensione.service;

import model.entity.Account;
import model.entity.Prodotto;
import model.entity.Review;

import java.util.ArrayList;

public interface RecensioneService {

    boolean  aggiungiRecensione(Account account,Prodotto prodotto,String descrizione,double valutazione);

    boolean modificaRecensione(String descrizione,double valutazione);

    boolean rimuoviRecensione(Account account,Prodotto prodotto);

    ArrayList<Review> cercaRecensioniPerProdotto(Prodotto prodotto);

    ArrayList<Review> cercaRecensioniPerUtente(Account account);


}
