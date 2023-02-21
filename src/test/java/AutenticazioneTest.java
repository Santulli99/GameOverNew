import Application_Logic.autenticazione.service.AutenticazioneServiceImp;
import Storage.account.SqlAccountDao;
import Application_Logic.entity.Account;

import org.junit.Test;

import java.sql.SQLException;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

public class AutenticazioneTest {

    @Test
    public void  verifyEmailFalse() throws SQLException {
        String email="g.esposito7studenti.unisa.it";
        String pass="Esposito95";

        SqlAccountDao accountDao= mock(SqlAccountDao.class);
        AutenticazioneServiceImp autenticazioneServiceImp=new AutenticazioneServiceImp(accountDao);

        when(accountDao.searchAccountLogin(pass,email)).thenReturn(null);
        assertNull(autenticazioneServiceImp.login(email,pass));
    }

    @Test
    public void verifyPasswordFalse() throws SQLException {
        String email="g.esposito7@studenti.unisa.it";
        String pass="esposito95";

        SqlAccountDao accountDao= mock(SqlAccountDao.class);
        AutenticazioneServiceImp autenticazioneServiceImp=new AutenticazioneServiceImp(accountDao);

        when(accountDao.searchAccountLogin(pass,email)).thenReturn(null);
        assertNull(autenticazioneServiceImp.login(email,pass));
    }

    @Test
    public void verifyLoginTrue() throws SQLException {
        String email="g.esposito7@studenti.unisa.it";
        String pass="Esposito95";
        Account account=new Account();
        account.setEmail(email);
        account.setPassword1(pass);

        SqlAccountDao accountDao= mock(SqlAccountDao.class);
        AutenticazioneServiceImp autenticazioneServiceImp=new AutenticazioneServiceImp(accountDao);

        when(accountDao.searchAccountLogin(pass,email)).thenReturn(account);
        assertEquals(email,autenticazioneServiceImp.login(email,pass).getEmail());
        assertEquals(pass,autenticazioneServiceImp.login(email,pass).getPassword());
    }

}
