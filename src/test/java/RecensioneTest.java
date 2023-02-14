import gestioneProdotto.service.GestioneProdottoServiceImp;
import model.dao.review.SqlReviewDao;
import model.entity.Prodotto;
import model.entity.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import validate.ValidateForm;

import java.sql.SQLException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class RecensioneTest {

    @BeforeEach
    public void CercaProdotto() {
        int idProdotto = 1;
        Prodotto prodotto = new Prodotto();
        GestioneProdottoServiceImp mockProdotto = Mockito.mock(GestioneProdottoServiceImp.class);
        Mockito.when(mockProdotto.getProdotto(idProdotto)).thenReturn(prodotto);
    }

    @Test
    public void TitoloNonCorrettoTest() {
        String titolo = "";
        String commento = "Ottimo prodotto ad un prezzo conveniente.";

        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(false);
        Mockito.when(mockValidateForm.validateCommentoReview(commento)).thenReturn(true);
        boolean titoloError=mockValidateForm.validateTitoloReview(titolo);
        boolean commetoSuccess=mockValidateForm.validateCommentoReview(commento);
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
        boolean titoloSuccess=mockValidateForm.validateTitoloReview(titolo);
        boolean commentoError=mockValidateForm.validateCommentoReview(commento);
        assertTrue(titoloSuccess);
        assertFalse(commentoError);
    }

    @Test
    public void RecensioeCorrettaTest() throws SQLException {
        String titolo = "Ottimo";
        String commento = "Ottimo prodotto ad un prezzo conveniente.";


        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(true);
        Mockito.when(mockValidateForm.validateCommentoReview(commento)).thenReturn(true);

        Review  review=new Review();
        review.setTitolo(titolo);
        review.setDescrizione(commento);


        SqlReviewDao mockReviewDao=Mockito.mock(SqlReviewDao.class);
        Mockito.when(mockReviewDao.createReview(review)).thenReturn(true);
        boolean reviewSuccess=mockReviewDao.createReview(review);
        assertTrue(reviewSuccess);

    }

}
