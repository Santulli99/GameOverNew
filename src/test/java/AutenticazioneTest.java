import autenticazione.service.AutenticazioneServiceImp;
import model.dao.account.SqlAccountDao;
import model.entity.Account;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class AutenticazioneTest {
    @Test
    void verifyEmailFalse() throws SQLException {
        String email="g.esposito7studenti.unisa.it";
        String pass="Esposito95";

        SqlAccountDao accountDao= mock(SqlAccountDao.class);
        AutenticazioneServiceImp autenticazioneServiceImp=new AutenticazioneServiceImp(accountDao);

        when(accountDao.searchAccountLogin(pass,email)).thenReturn(null);
        assertNull(autenticazioneServiceImp.login(email,pass));
    }

    @Test
    void verifyPasswordFalse() throws SQLException {
        String email="g.esposito7@studenti.unisa.it";
        String pass="esposito95";

        SqlAccountDao accountDao= mock(SqlAccountDao.class);
        AutenticazioneServiceImp autenticazioneServiceImp=new AutenticazioneServiceImp(accountDao);

        when(accountDao.searchAccountLogin(pass,email)).thenReturn(null);
        assertNull(autenticazioneServiceImp.login(email,pass));
    }

    @Test
    void verifyLoginTrue() throws SQLException {
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

    @Test
    public void testLogin() throws SQLException {
        // crea un oggetto mock per la classe SqlAccountDao
        SqlAccountDao mockDao = mock(SqlAccountDao.class);

        // crea un account di esempio
        Account expectedAccount = new Account();
        expectedAccount.setEmail("test@example.com");
        expectedAccount.setPassword("password");

        // configura il comportamento del mock per la ricerca dell'account
        when(mockDao.searchAccountLogin("password", "test@example.com")).thenReturn(expectedAccount);

        // crea un oggetto LoginService con il mock della dao
        AutenticazioneServiceImp loginService = new AutenticazioneServiceImp(mockDao);

        // chiamata al metodo login
        Account actualAccount = loginService.login("test@example.com", "password");

        // verifica che l'account restituito corrisponde all'account atteso
        assertEquals(expectedAccount.getEmail(), actualAccount.getEmail());
        assertEquals(expectedAccount.getPassword(), actualAccount.getPassword());

    }

}
