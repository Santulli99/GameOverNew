import gestioneProdotto.service.GestioneProdottoServiceImp;

import model.entity.Account;
import model.entity.Prodotto;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import recensione.controller.RecensioneController;

import recensione.service.RecensioneServiceImp;
import validate.ValidateForm;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RecensioneTest {

    @Test
    public void testValidateCommentoReview_ValidCommento_ReturnsTrue() {
        ValidateForm validator = new ValidateForm();
        String validCommento = "Questo è un commento valido.";
        assertTrue(validator.validateCommentoReview(validCommento));
    }

    @Test
    public void testValidateCommentoReview_EmptyCommento_ReturnsFalse() {
        ValidateForm validator = new ValidateForm();
        String emptyCommento = "";
        assertFalse(validator.validateCommentoReview(emptyCommento));
    }

    @Test
    public void testValidateCommentoReview_LongCommento_ReturnsFalse() {
        ValidateForm validator = new ValidateForm();
        String longCommento = "Questo è un commento molto lungo. In realtà è così lungo che supera la lunghezza massima consentita dal metodo validateCommentoReview, che è di 500 caratteri. Quindi, se questo test unitario passa, significa che il metodo funziona correttamente.Questo è un commento molto lungo. In realtà è così lungo che supera la lunghezza massima consentita dal metodo validateCommentoReview, che è di 500 caratteri. Quindi, se questo test unitario passa, significa che il metodo funziona correttamente........... ";
        System.out.println(longCommento.length());
        assertFalse(validator.validateCommentoReview(longCommento));
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

        int idProdotto = 1;
        Prodotto prodotto = new Prodotto();

        GestioneProdottoServiceImp mockProdotto = Mockito.mock(GestioneProdottoServiceImp.class);
        Mockito.when(mockProdotto.getProdotto(idProdotto)).thenReturn(prodotto);


        RecensioneController recensioneController = new RecensioneController(MockRecensioneService);
        Mockito.when(recensioneController.aggiungiRecensione(titolo, commento, valutazione, account, prodotto)).thenReturn(true);
        assertTrue(recensioneController.aggiungiRecensione(titolo, commento, valutazione, account, prodotto));
    }

    @Test
    public void aggiungiRecensioneTitolo_InvalidReview_ReturnsFalse() {
        // Setup
        String titolo = "";
        String descrizione = "Descrizione troppo corta";
        double valutazione = 3.0;
        Account account = new Account();
        Prodotto prodotto = new Prodotto();
        RecensioneServiceImp mockRecensioneService = Mockito.mock(RecensioneServiceImp.class);
        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(false);
        Mockito.when(mockValidateForm.validateCommentoReview(descrizione)).thenReturn(true);

        // Call the method under test
        RecensioneController recensioneController = new RecensioneController(mockRecensioneService);
        boolean result = recensioneController.aggiungiRecensione(titolo, descrizione, valutazione, account, prodotto);

        // Verify the result
        assertFalse(result);
    }

    @Test
    public void aggiungiRecensioneDscr_InvalidReview_ReturnsFalse() {
        // Setup
        String titolo = "bello";
        String descrizione = "";
        double valutazione = 3.0;
        Account account = new Account();
        Prodotto prodotto = new Prodotto();
        RecensioneServiceImp mockRecensioneService = Mockito.mock(RecensioneServiceImp.class);
        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(true);
        Mockito.when(mockValidateForm.validateCommentoReview(descrizione)).thenReturn(false);

        // Call the method under test
        RecensioneController recensioneController = new RecensioneController(mockRecensioneService);
        boolean result = recensioneController.aggiungiRecensione(titolo, descrizione, valutazione, account, prodotto);

        // Verify the result
        assertFalse(result);
    }

}
