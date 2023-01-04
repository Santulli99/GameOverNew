package model.dao.account;

import model.entity.Account;
import model.dao.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountExtractor implements ResultSetExtractor<Account> {
    @Override
    public Account extract(ResultSet resultSet) throws SQLException {
        Account account=new Account();
        account.setId(resultSet.getInt("acc.id_cliente"));
        account.setAdmin(resultSet.getBoolean("acc.amministratore"));
        account.setEmail(resultSet.getString("acc.email"));
        account.setUsername(resultSet.getString("acc.username"));
        //mettere le password criptate metodo prof lato query e lato metodo setPassword
        account.setPassword(resultSet.getString("acc.password"));
        return account;
    }
}
