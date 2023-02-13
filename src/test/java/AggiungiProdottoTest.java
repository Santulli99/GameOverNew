import gestioneProdotto.controller.GestioneProdottoController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import validate.ValidateForm;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class AggiungiProdottoTest {
    GestioneProdottoController controller;

    @Test
    public void NomeCorrettoTest(){
        ValidateForm vali=Mockito.mock(ValidateForm.class);
        String nome="Gerardo";
        Mockito.when(vali.validateNome(nome)).thenReturn(true);

      //  assertTrue(a);
    }

    @Test
    public void NomeNonCorrettoTest(){

    }
}
