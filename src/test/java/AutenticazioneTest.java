import autenticazione.service.AutenticazioneServiceImp;
import model.dao.account.SqlAccountDao;
import model.entity.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AutenticazioneTest {

    @Test
    void veryLoginFalse() throws SQLException {
        String email="ciao@gmail.it";
        String pass="pass";

        SqlAccountDao accountDao= Mockito.mock(SqlAccountDao.class);
        AutenticazioneServiceImp autenticazioneServiceImp=new AutenticazioneServiceImp(accountDao);

        Mockito.when(accountDao.searchAccountLogin(pass,email)).thenReturn(null);
        assertNull(autenticazioneServiceImp.login(email,pass));
    }



    @Test
    void veryLoginTrue() throws SQLException {
        String email="a.santulli3@studenti.unisa.iy";
        String pass="Mancini90";
        Account account=new Account();
        account.setEmail(email);
        account.setPassword1(pass);

        SqlAccountDao accountDao= Mockito.mock(SqlAccountDao.class);
        AutenticazioneServiceImp autenticazioneServiceImp=new AutenticazioneServiceImp(accountDao);

        Mockito.when(accountDao.searchAccountLogin(pass,email)).thenReturn(account);
        assertEquals(email,autenticazioneServiceImp.login(email,pass).getEmail());
        assertEquals(pass,autenticazioneServiceImp.login(email,pass).getPassword());
    }

}
