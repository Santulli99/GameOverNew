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
    void verifyEmailFalse() throws SQLException {
        String email="g.esposito7studenti.unisa.it";
        String pass="Esposito95";

        SqlAccountDao accountDao= Mockito.mock(SqlAccountDao.class);
        AutenticazioneServiceImp autenticazioneServiceImp=new AutenticazioneServiceImp(accountDao);

        Mockito.when(accountDao.searchAccountLogin(pass,email)).thenReturn(null);
        assertNull(autenticazioneServiceImp.login(email,pass));
    }

    @Test
    void verifyPasswordFalse() throws SQLException {
        String email="g.esposito7@studenti.unisa.it";
        String pass="esposito95";

        SqlAccountDao accountDao= Mockito.mock(SqlAccountDao.class);
        AutenticazioneServiceImp autenticazioneServiceImp=new AutenticazioneServiceImp(accountDao);

        Mockito.when(accountDao.searchAccountLogin(pass,email)).thenReturn(null);
        assertNull(autenticazioneServiceImp.login(email,pass));
    }

    @Test
    void verifyLoginTrue() throws SQLException {
        String email="g.esposito7@studenti.unisa.it";
        String pass="Esposito95";
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
