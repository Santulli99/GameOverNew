import gestioneProdotto.service.GestioneProdottoServiceImp;
import model.dao.review.SqlReviewDao;
import model.entity.Account;
import model.entity.Prodotto;
import model.entity.Review;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import validate.ValidateForm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class RecensioneTest {


    @Test
    public void TitoloNonCorrettoTest() {
        String titolo = "";
        String commento = "Ottimo prodotto ad un prezzo conveniente.";

        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(false);
        Mockito.when(mockValidateForm.validateCommentoReview(commento)).thenReturn(true);
        boolean titoloError = mockValidateForm.validateTitoloReview(titolo);
        boolean commetoSuccess = mockValidateForm.validateCommentoReview(commento);
        assertTrue(commetoSuccess);
        assertFalse(titoloError);
    }

    @Test
    public void CommentoNonCorrettoTest() {
        String titolo = "Ottimo";
        String commento = "";

        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(true);
        Mockito.when(mockValidateForm.validateCommentoReview(commento)).thenReturn(false);
        boolean titoloSuccess = mockValidateForm.validateTitoloReview(titolo);
        boolean commentoError = mockValidateForm.validateCommentoReview(commento);
        assertTrue(titoloSuccess);
        assertFalse(commentoError);
    }

    @Test
    public void RecensioneCorrettaTest() throws SQLException {
        String titolo = "Ottimo";
        String commento = "Ottimo prodotto ad un prezzo conveniente.";


        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(true);
        Mockito.when(mockValidateForm.validateCommentoReview(commento)).thenReturn(true);

        Account account = new Account();
        account.setEmail("a.serpico7@studenti.unisa.it");
        account.setFirstName("Andrea");
        account.setLastName("Serpico");
        account.setVenditore(false);
        account.setId(5);
        LocalDate date = LocalDate.of(2000, Month.FEBRUARY, 15);
        account.setDate(date);
        account.setPassword("Mancini99");
        account.setUsername("andrea7");

        int idProdotto = 1;
        Prodotto prodotto = new Prodotto();
        GestioneProdottoServiceImp mockProdotto = Mockito.mock(GestioneProdottoServiceImp.class);
        Mockito.when(mockProdotto.getProdotto(idProdotto)).thenReturn(prodotto);

        Review review = new Review();
        review.setTitolo(titolo);
        review.setDescrizione(commento);
        review.setProdotto(prodotto);
        review.setAccount(account);
        review.setValutazione(3);

        SqlReviewDao mockReviewDao = Mockito.mock(SqlReviewDao.class);
        Mockito.when(mockReviewDao.createReview(review)).thenReturn(true);
        boolean reviewSuccess = mockReviewDao.createReview(review);
        assertTrue(reviewSuccess);

    }

}
