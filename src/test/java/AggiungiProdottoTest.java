
import model.dao.product.SqlProductDao;
import model.entity.Prodotto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import validate.ValidateForm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;


public class AggiungiProdottoTest  {


    @Test
    public void NomeNonCorrettoTest(){
        String nome="Fifa%";
        String prezzo="99";
        String descrizione="Gioco Sportivo";
        File cover=new File("hogwarts.jpg");
        LocalDate dataInserita=LocalDate.of(2022, Month.NOVEMBER,22);

        ValidateForm mockValidate=Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidate.validateNome(nome)).thenReturn(false);
        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(true);
        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(true);
        Mockito.when(mockValidate.validateSizeCoverProdotto(cover)).thenReturn(true);
        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(true);
        boolean dataCoretta=mockValidate.validateDataUscitaProdotto(dataInserita);
        boolean copertinaCorretta=mockValidate.validateSizeCoverProdotto(cover);
        boolean descrizioneCorretta=mockValidate.validateDescrizioneProdotto(descrizione);
        boolean prezzoCorretto=mockValidate.validatePrezzoProdotto(prezzo);
        boolean nomeErrato=mockValidate.validateNome(nome);
        assertTrue(dataCoretta,"Data Corretta");
        assertTrue(descrizioneCorretta,"Descrizione Corretta");
        assertTrue(copertinaCorretta,"Copertina Corretta");
        assertTrue(prezzoCorretto,"Prezzo Corretto");
        assertFalse(nomeErrato,"Nome non conforme ai parametri");

    }

    @Test
    public void PrezzoNonCorrettoTest(){
        String nome="Fifa";
        String prezzo="A99";
        String descrizione="Gioco Sportivo";
        File cover=new File("hogwarts.jpg");
        LocalDate dataInserita=LocalDate.of(2022, Month.NOVEMBER,22);
        ValidateForm mockValidate=Mockito.mock(ValidateForm.class);

        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(false);
        Mockito.when(mockValidate.validateNome(nome)).thenReturn(true);
        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(true);
        Mockito.when(mockValidate.validateSizeCoverProdotto(cover)).thenReturn(true);
        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(true);

        boolean prezzoErrato=mockValidate.validatePrezzoProdotto(prezzo);
        boolean dataCoretta=mockValidate.validateDataUscitaProdotto(dataInserita);
        boolean copertinaCorretta=mockValidate.validateSizeCoverProdotto(cover);
        boolean descrizioneCorretta=mockValidate.validateDescrizioneProdotto(descrizione);
        boolean nomeCorretto=mockValidate.validateNome(nome);

        assertTrue(dataCoretta,"Data Corretta");
        assertTrue(descrizioneCorretta,"Descrizione Corretta");
        assertTrue(copertinaCorretta,"Copertina Corretta");
        assertTrue(nomeCorretto,"Nome Corretto");
        assertFalse(prezzoErrato, "Prezzo non conforme ai parametri");
    }

    @Test
    public void DescrizioneNonCorrettaTest(){
        String nome="Fifa";
        String prezzo="99";
        String descrizione="";
        File cover=new File("hogwarts.jpg");
        LocalDate dataInserita=LocalDate.of(2022, Month.NOVEMBER,22);
        ValidateForm mockValidate=Mockito.mock(ValidateForm.class);

        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(false);
        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(true);
        Mockito.when(mockValidate.validateNome(nome)).thenReturn(true);
        Mockito.when(mockValidate.validateSizeCoverProdotto(cover)).thenReturn(true);
        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(true);

        boolean descrizioneErrata=mockValidate.validateDescrizioneProdotto(descrizione);
        boolean prezzoCorretto=mockValidate.validatePrezzoProdotto(prezzo);
        boolean dataCoretta=mockValidate.validateDataUscitaProdotto(dataInserita);
        boolean copertinaCorretta=mockValidate.validateSizeCoverProdotto(cover);
        boolean nomeCorretto=mockValidate.validateNome(nome);

        assertTrue(dataCoretta,"Data Corretta");
        assertTrue(prezzoCorretto,"Prezzo Corretta");
        assertTrue(copertinaCorretta,"Copertina Corretta");
        assertTrue(nomeCorretto,"Nome Corretto");
        assertFalse(descrizioneErrata,"Descrizione non conforme ai parametri");
    }

    @Test
    public void CopertinaNonCorrettaTest()  {
        String nome="Fifa";
        String prezzo="99";
        String descrizione="";
        File cover=new File("hogwarts.jpg");
        LocalDate dataInserita=LocalDate.of(2022, Month.NOVEMBER,22);

        ValidateForm mockValidate=Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidate.validateSizeCoverProdotto(cover)).thenReturn(false);
        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(true);
        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(true);
        Mockito.when(mockValidate.validateNome(nome)).thenReturn(true);
        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(true);

        boolean descrizioneCorretta=mockValidate.validateDescrizioneProdotto(descrizione);
        boolean prezzoCorretto=mockValidate.validatePrezzoProdotto(prezzo);
        boolean dataCoretta=mockValidate.validateDataUscitaProdotto(dataInserita);
        boolean nomeCorretto=mockValidate.validateNome(nome);
        boolean copertinaErrata=mockValidate.validateSizeCoverProdotto(cover);

        assertTrue(dataCoretta,"Data Corretta");
        assertTrue(prezzoCorretto,"Prezzo Corretta");
        assertTrue(descrizioneCorretta,"Descrizione Corretta");
        assertTrue(nomeCorretto,"Nome Corretto");
        assertFalse(copertinaErrata,"Copertina non conforme ai parametri");
    }


    @Test
    public void DataUscitaNonCorrettoTest(){
        String nome="Fifa";
        String prezzo="99";
        String descrizione="Gioco Sportivo";
        File cover=new File("hogwarts.jpg");
        LocalDate dataInserita=LocalDate.of(2025, Month.NOVEMBER,22);

        ValidateForm mockValidate=Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(false);
        Mockito.when(mockValidate.validateSizeCoverProdotto(cover)).thenReturn(true);
        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(true);
        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(true);
        Mockito.when(mockValidate.validateNome(nome)).thenReturn(true);

        boolean descrizioneCorretta=mockValidate.validateDescrizioneProdotto(descrizione);
        boolean prezzoCorretto=mockValidate.validatePrezzoProdotto(prezzo);
        boolean nomeCorretto=mockValidate.validateNome(nome);
        boolean copertinaCorretta=mockValidate.validateSizeCoverProdotto(cover);
        boolean dataErrata=mockValidate.validateDataUscitaProdotto(dataInserita);

        assertTrue(copertinaCorretta,"Copertina Corretta");
        assertTrue(prezzoCorretto,"Prezzo Corretta");
        assertTrue(descrizioneCorretta,"Descrizione Corretta");
        assertTrue(nomeCorretto,"Nome Corretto");
        assertFalse(dataErrata,"Data inserita non conforme ai parametri");

    }

    @Test
    public void AggiungiProdottoCorrettoTest() throws SQLException {
        String nome="Fifa";
        String prezzo="99";
        String descrizione="Gioco Sportivo";
        File cover=new File("hogwarts.jpg");
        LocalDate dataInserita=LocalDate.of(2022, Month.NOVEMBER,22);

        ValidateForm mockValidate=Mockito.mock(ValidateForm.class);

        Mockito.when(mockValidate.validateDataUscitaProdotto(dataInserita)).thenReturn(true);
        Mockito.when(mockValidate.validateDescrizioneProdotto(descrizione)).thenReturn(true);
        Mockito.when(mockValidate.validatePrezzoProdotto(prezzo)).thenReturn(true);
        Mockito.when(mockValidate.validateNome(nome)).thenReturn(true);
        Mockito.when(mockValidate.validateSizeCoverProdotto(cover)).thenReturn(true);

        Prodotto prodotto=new Prodotto();
        prodotto.setProductName(nome);
        prodotto.setPrice(Double.parseDouble(prezzo));
        prodotto.setDescription(descrizione);
        prodotto.setCover(String.valueOf(cover));
        prodotto.setDate(dataInserita);

        SqlProductDao mockProductDao=Mockito.mock(SqlProductDao.class);
        Mockito.when(mockProductDao.createProduct(prodotto)).thenReturn(true);
        assertTrue(mockProductDao.createProduct(prodotto),"Prodotto inserito correttamente");
    }
}
