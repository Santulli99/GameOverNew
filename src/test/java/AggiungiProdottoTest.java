
import model.dao.product.SqlProductDao;
import model.entity.Prodotto;
import org.junit.Before;
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
        boolean nomeErrato=mockValidate.validateNome(nome);
        assertFalse(nomeErrato);
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
        boolean prezzoErrato=mockValidate.validatePrezzoProdotto(prezzo);
        assertFalse(prezzoErrato);
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
        boolean descrizioneErrata=mockValidate.validateDescrizioneProdotto(descrizione);
        assertFalse(descrizioneErrata);
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
        boolean copertinaErrata=mockValidate.validateSizeCoverProdotto(cover);
        assertFalse(copertinaErrata);
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
        boolean dataErrata=mockValidate.validateDataUscitaProdotto(dataInserita);
        assertFalse(dataErrata);

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
        assertTrue(mockProductDao.createProduct(prodotto));
    }
}
