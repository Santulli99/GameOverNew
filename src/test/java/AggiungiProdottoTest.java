import gestioneProdotto.controller.GestioneProdottoController;
import gestioneProdotto.service.GestioneProdottoServiceImp;
import javafx.util.converter.LocalDateStringConverter;
import model.dao.product.SqlProductDao;
import model.entity.Account;
import model.entity.Prodotto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import validate.ValidateForm;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AggiungiProdottoTest {
    GestioneProdottoController controller;

    @Test
    public void NomeNonCorrettoTest(){
        String nome="Fifa%";
        Prodotto prodotto=new Prodotto();
        prodotto.setProductName(nome);
        SqlProductDao mockProductDao=Mockito.mock(SqlProductDao.class);
        GestioneProdottoServiceImp gestioneProdottoServiceImp=new GestioneProdottoServiceImp(mockProductDao);
        Mockito.when(gestioneProdottoServiceImp.aggiungiProdotto(prodotto)).thenReturn(false);
        assertFalse(gestioneProdottoServiceImp.aggiungiProdotto(prodotto));
    }
    @Test
    public void PrezzoNonCorrettoTest(){
        String nome="Fifa%";
        String descrizione="";
        double prezzo= Double.parseDouble("-1");
        Prodotto prodotto=new Prodotto();
        prodotto.setPrice(prezzo);
        prodotto.setProductName(nome);
        prodotto.setDescription(descrizione);
        SqlProductDao mockProductDao=Mockito.mock(SqlProductDao.class);
        GestioneProdottoServiceImp gestioneProdottoServiceImp=new GestioneProdottoServiceImp(mockProductDao);
        Mockito.when(gestioneProdottoServiceImp.aggiungiProdotto(prodotto)).thenReturn(false);
        assertFalse(gestioneProdottoServiceImp.aggiungiProdotto(prodotto));
    }

    @Test
    public void DescrizioneNonCorrettaTest(){
        String descrizione="";
        Prodotto prodotto=new Prodotto();
        prodotto.setDescription(descrizione);
        SqlProductDao mockProductDao=Mockito.mock(SqlProductDao.class);
        GestioneProdottoServiceImp gestioneProdottoServiceImp=new GestioneProdottoServiceImp(mockProductDao);
        Mockito.when(gestioneProdottoServiceImp.aggiungiProdotto(prodotto)).thenReturn(false);
        assertFalse(gestioneProdottoServiceImp.aggiungiProdotto(prodotto));
    }
    @Test
    public void CopertinaNonCorrettaTest(){
        String grandezzaCopertina="2Mb";
        Prodotto prodotto=new Prodotto();
        prodotto.setCover(grandezzaCopertina);
        SqlProductDao mockProductDao=Mockito.mock(SqlProductDao.class);
        GestioneProdottoServiceImp gestioneProdottoServiceImp=new GestioneProdottoServiceImp(mockProductDao);
        Mockito.when(gestioneProdottoServiceImp.aggiungiProdotto(prodotto)).thenReturn(false);
        assertFalse(gestioneProdottoServiceImp.aggiungiProdotto(prodotto));
    }
    @Test
    public void DataUscitaNonCorrettoTest(){
        LocalDate dataOggi=LocalDate.now();
        LocalDate dataInserita=LocalDate.of(2023, Month.APRIL,2);

    }

    @Test
    public void AggiungiProdottoCorrettoTest(){

    }




}
