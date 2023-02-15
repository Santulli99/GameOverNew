package model.dao.account;

import model.entity.Account;
import model.dao.storage.SqlDao;

import java.sql.*;
import java.util.ArrayList;

public class SqlAccountDao implements AccountDao<SQLException> {


    public SqlAccountDao() {
        super();
    }

    public Account searchAccountEmail(String email) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT * FROM account AS acc WHERE  acc.email=? ";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();

                if (rs.next()) {
                    account = accountExtractor.extract(rs);
                }
                return account;
            }
        }
    }

    @Override
    public Account searchAccountId(int id) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT * FROM account AS acc WHERE (id_cliente=?);";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                Account account = null;
                AccountExtractor acc = new AccountExtractor();
                if (rs.next()) {
                    account = acc.extract(rs);
                }
                return account;
            }
        }
    }

    @Override
    public ArrayList<Account> searchAllAccount() throws SQLException {


        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT * FROM account AS acc WHERE venditore=0;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();

                ArrayList<Account> accounts = new ArrayList<>();
                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();

                while (rs.next()) {
                    account = accountExtractor.extract(rs);
                    accounts.add(account);
                }
                return accounts;
            }
        }
    }

    @Override
    public boolean createAccount(Account account) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "INSERT INTO account (email, password, venditore, username,nome,cognome,data_nascita) VALUES(?,?,?,?,?,?,?);";

            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, account.getEmail());
                ps.setString(2, account.getPassword());
                ps.setBoolean(3, account.isVenditore());
                ps.setString(4, account.getUsername());
                ps.setString(5, account.getFirstName());
                ps.setString(6, account.getLastName());
                ps.setDate(7, Date.valueOf(account.getDate()));

                int rows = ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                account.setId(id);

                return rows == 1;
            }

        }

    }

    @Override
    public boolean updateAccount(Account account) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "UPDATE account SET username=?,email=?,nome=?,cognome=? WHERE(id_cliente=?);";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, account.getUsername());
                ps.setString(2, account.getEmail());
                ps.setString(3, account.getFirstName());
                ps.setString(4, account.getLastName());
                ps.setInt(5, account.getId());

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    public boolean updatePasswordAccount(Account account) throws SQLException {
        try (Connection connection = SqlDao.getConnection()) {
            String query = "UPDATE account SET password=? WHERE(id_cliente=?);";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, account.getPassword());
                ps.setInt(2, account.getId());

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public Account  searchAccountLogin(String pass,String email) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT * FROM account AS acc WHERE acc.password=SHA1(?) AND acc.email=?;";
            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, pass);
                ps.setString(2, email);
                ResultSet rs = ps.executeQuery();
                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();
                if (rs.next()) {
                    account = accountExtractor.extract(rs);

                }
                return account;
            }
        }
    }

}
