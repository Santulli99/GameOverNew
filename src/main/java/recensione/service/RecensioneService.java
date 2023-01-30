package recensione.service;

import model.entity.Account;
import model.entity.Prodotto;
import model.entity.Review;

import java.util.ArrayList;

public interface RecensioneService {

    boolean  aggiungiRecensione(Review review);

    boolean modificaRecensione(Review review);

    boolean rimuoviRecensione(Account account,Prodotto prodotto);

    ArrayList<Review> cercaRecensioniPerProdotto(Prodotto prodotto);

    ArrayList<Review> cercaRecensioniPerUtente(Account account);

    Review cercaRecensionePerProdotto(Prodotto prodotto,Account account);
}
