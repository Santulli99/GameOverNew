package Storage.account;

import Application_Logic.entity.Account;
import Storage.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountExtractor implements ResultSetExtractor<Account> {
    @Override
    public Account extract(ResultSet resultSet) throws SQLException {
        Account account=new Account();
        account.setId(resultSet.getInt("acc.id_cliente"));
        account.setVenditore(resultSet.getBoolean("acc.venditore"));
        account.setEmail(resultSet.getString("acc.email"));
        account.setUsername(resultSet.getString("acc.username"));
        account.setPassword(resultSet.getString("acc.password"));
        account.setFirstName(resultSet.getString("acc.nome"));
        account.setLastName(resultSet.getString("acc.cognome"));
        account.setDate(resultSet.getDate("acc.data_nascita").toLocalDate());
        return account;
    }
}
