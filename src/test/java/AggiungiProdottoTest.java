import gestioneProdotto.controller.GestioneProdottoController;
import model.entity.Account;
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

        Account account=new Account();
        account.setFirstName(nome);



      //  assertTrue(a);
    }

    @Test
    public void NomeNonCorrettoTest(){

    }
}
