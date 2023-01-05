package autenticazione.service;

import model.dao.account.SqlAccountDao;
import model.entity.Account;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AutenticazioneServiceImp implements AutenticazioneService {
    private SqlAccountDao sqlAccountDao=null;


    public AutenticazioneServiceImp (){sqlAccountDao=new SqlAccountDao();}

    public AutenticazioneServiceImp(SqlAccountDao sqlAccountDao) {
        this.sqlAccountDao = sqlAccountDao;
    }

    @Override
    public Account login(String email, String password) {

        Account account=new Account();
        try {
            account = sqlAccountDao.searchAccountIdWithDataClientandAndressLogin(password,email);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  account;
    }

    @Override
    public boolean verificaAdmin(Account account) {

        return account.isAdmin();
    }

    @Override
    public void logout(HttpSession sessione) {

         sessione.invalidate();

    }
}
