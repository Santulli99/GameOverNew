
import gestioneProdotto.controller.GestioneProdottoController;
import gestioneProdotto.service.GestioneProdottoServiceImp;

import org.junit.Test;
import org.mockito.Mockito;
import validate.ValidateForm;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class AggiungiProdottoTest {

    @Test
    public void NomeNonCorrettoTest() {
        String nome = "Fi";
        String prezzo = "99";
        String descrizione = "Gioco Sportivo";
        String cover = "hogwarts.jpg";
        LocalDate dataInserita = LocalDate.of(2022, Month.NOVEMBER, 22);
        String categoria = "Sport";
        String piattaforma = "PS4";

        ValidateForm mockValidate = mock(ValidateForm.class);
        Mockito.when(mockValidate.validateNomeProdotto(nome)).thenReturn(false);
        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(true);
        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(true);
        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(true);

        GestioneProdottoServiceImp MockGestioneProdotto = Mockito.mock(GestioneProdottoServiceImp.class);
        GestioneProdottoController gestioneProdottoController = new GestioneProdottoController(MockGestioneProdotto);
        boolean result = gestioneProdottoController.aggiungiProdotto(nome, prezzo, descrizione, cover, dataInserita, categoria, piattaforma);
        assertFalse(result);
    }

    @Test
    public void PrezzoNonCorrettoTest() {
        String nome = "Fifa";
        String prezzo = "A99";
        String descrizione = "Gioco Sportivo";
        String cover = "hogwarts.jpg";
        LocalDate dataInserita = LocalDate.of(2022, Month.NOVEMBER, 22);
        ValidateForm mockValidate = mock(ValidateForm.class);
        String categoria = "Sport";
        String piattaforma = "PS4";

        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(false);
        Mockito.when(mockValidate.validateNomeProdotto(nome)).thenReturn(true);
        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(true);
        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(true);

        GestioneProdottoServiceImp MockGestioneProdotto = Mockito.mock(GestioneProdottoServiceImp.class);
        GestioneProdottoController gestioneProdottoController = new GestioneProdottoController(MockGestioneProdotto);
        boolean result = gestioneProdottoController.aggiungiProdotto(nome, prezzo, descrizione, cover, dataInserita, categoria, piattaforma);
        assertFalse(result);
    }

    @Test
    public void DescrizioneNonCorrettaTest() {
        String nome = "Fifa";
        String prezzo = "99";
        String descrizione = "";
        String cover = "hogwarts.jpg";
        LocalDate dataInserita = LocalDate.of(2022, Month.NOVEMBER, 22);
        ValidateForm mockValidate = mock(ValidateForm.class);
        String categoria = "Sport";
        String piattaforma = "PS4";

        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(false);
        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(true);
        Mockito.when(mockValidate.validateNomeProdotto(nome)).thenReturn(true);
        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(true);

        GestioneProdottoServiceImp MockGestioneProdotto = Mockito.mock(GestioneProdottoServiceImp.class);
        GestioneProdottoController gestioneProdottoController = new GestioneProdottoController(MockGestioneProdotto);
        boolean result = gestioneProdottoController.aggiungiProdotto(nome, prezzo, descrizione, cover, dataInserita, categoria, piattaforma);
        assertFalse(result);

    }

    @Test
    public void DataUscitaNonCorrettoTest() {
        String nome = "Fifa";
        String prezzo = "99";
        String descrizione = "Gioco Sportivo";
        String cover = "hogwarts.jpg";
        LocalDate dataInserita = LocalDate.of(2025, Month.NOVEMBER, 22);
        String categoria = "Sport";
        String piattaforma = "PS4";

        ValidateForm mockValidate = mock(ValidateForm.class);
        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(false);
        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(true);
        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(true);
        Mockito.when(mockValidate.validateNomeProdotto(nome)).thenReturn(true);

        GestioneProdottoServiceImp MockGestioneProdotto = Mockito.mock(GestioneProdottoServiceImp.class);
        GestioneProdottoController gestioneProdottoController = new GestioneProdottoController(MockGestioneProdotto);
        boolean result = gestioneProdottoController.aggiungiProdotto(nome, prezzo, descrizione, cover, dataInserita, categoria, piattaforma);
        assertFalse(result);
    }


    @Test
    public void AggiungiProdottoCorrettoTest() {
        String nome = "Fifa";
        String prezzo = "99";
        String descrizione = "Gioco Sportivo";
        String cover = "hogwarts.jpg";
        LocalDate dataInserita = LocalDate.of(2022, Month.NOVEMBER, 22);
        String categoria = "Sport";
        String piattaforma = "PS4";

        ValidateForm mockValidate = mock(ValidateForm.class);
        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(true);
        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(true);
        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(true);
        Mockito.when(mockValidate.validateNomeProdotto(nome)).thenReturn(true);

        GestioneProdottoServiceImp MockGestioneProdotto = Mockito.mock(GestioneProdottoServiceImp.class);
        GestioneProdottoController gestioneProdottoController = new GestioneProdottoController(MockGestioneProdotto);
        boolean result = gestioneProdottoController.aggiungiProdotto(nome, prezzo, descrizione, cover, dataInserita, categoria, piattaforma);
        assertTrue(result);
    }
}
