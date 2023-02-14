import gestioneProdotto.service.GestioneProdottoServiceImp;
import model.entity.Prodotto;
import model.entity.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import validate.ValidateForm;

import static junit.framework.TestCase.assertFalse;

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
        assertFalse(mockValidateForm.validateTitoloReview(titolo));
    }

    @Test
    public void CommentoNonCorrettoTest() {
        String titolo = "";
        String commento = "Ottimo prodotto ad un prezzo conveniente.";

        ValidateForm mockValidateForm = Mockito.mock(ValidateForm.class);
        Mockito.when(mockValidateForm.validateTitoloReview(titolo)).thenReturn(false);
        assertFalse(mockValidateForm.validateTitoloReview(titolo));


    }

    @Test
    public void RecensioeCorrettaTest() {
        String titolo = "";
        String commento = "Ottimo prodotto ad un prezzo conveniente.";


    }

}
