
import Application_Logic.entity.Account;
import Application_Logic.entity.Prodotto;

import org.junit.Test;
import org.mockito.Mockito;
import Application_Logic.recensione.controller.RecensioneController;

import Application_Logic.recensione.service.RecensioneServiceImp;
import Application_Logic.ValidateForm;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RecensioneTest {

    @Test
    public void titoloNonCorrettoTest() {

        String titolo = "";
        String descrizione = "Descrizione troppo corta";
        double valutazione = 3.0;
        Account account = new Account();
        Prodotto prodotto = new Prodotto();
        RecensioneServiceImp mockRecensioneService = Mockito.mock(RecensioneServiceImp.class);
        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(false);
        Mockito.when(mockValidateForm.validateCommentoReview(descrizione)).thenReturn(true);


        RecensioneController recensioneController = new RecensioneController(mockRecensioneService);
        boolean result = recensioneController.aggiungiRecensione(titolo, descrizione, valutazione, account, prodotto);


        assertFalse(result);
    }

    @Test
    public void descrizioneNonCorrettaTest() {

        String titolo = "bello";
        String descrizione = "";
        double valutazione = 3.0;
        Account account = new Account();
        Prodotto prodotto = new Prodotto();
        RecensioneServiceImp mockRecensioneService = Mockito.mock(RecensioneServiceImp.class);
        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(true);
        Mockito.when(mockValidateForm.validateCommentoReview(descrizione)).thenReturn(false);


        RecensioneController recensioneController = new RecensioneController(mockRecensioneService);
        boolean result = recensioneController.aggiungiRecensione(titolo, descrizione, valutazione, account, prodotto);


        assertFalse(result);
    }

    @Test
    public void RecensioneCorrettaTest() {
        String titolo = "Ottimo";
        String commento = "Ottimo prodotto ad un prezzo conveniente.";
        double valutazione = 5;
        RecensioneServiceImp MockRecensioneService = Mockito.mock(RecensioneServiceImp.class);
        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(true);
        Mockito.when(mockValidateForm.validateCommentoReview(commento)).thenReturn(true);

        Account account = new Account();
        Prodotto prodotto = new Prodotto();

        RecensioneController recensioneController = new RecensioneController(MockRecensioneService);
        assertTrue(recensioneController.aggiungiRecensione(titolo, commento, valutazione, account, prodotto));
    }
}