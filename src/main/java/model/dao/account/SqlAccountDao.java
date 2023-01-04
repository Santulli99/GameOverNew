package model.dao.account;

import model.entity.Address;
import model.dao.address.AddressExtractor;
import model.entity.DataClient;
import model.dao.dataClient.DataClientExtractor;
import model.entity.Account;
import model.dao.order.OrderExtractor;

import model.dao.storage.SqlDao;

import java.sql.*;
import java.util.ArrayList;

public class SqlAccountDao implements AccountDao<SQLException> {


    public SqlAccountDao()  {
        super();
    }


    public Account searchAccountEmail(String email) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT * " +
                    "FROM account AS acc  " +
                    "WHERE  acc.email=? ";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, email);

                ResultSet rs = ps.executeQuery();

                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();

                if (rs.next()) {
                    account = accountExtractor.extract(rs);
                }
                //mi ritorno  l'model.dao.account che ha anche i dati anagrafici
                return account;
            }
        }
    }






    public Account searchAccountWithEmail(String pass,String email) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT * " +
                    "FROM account AS acc,dataclient AS dat " +
                    "WHERE acc.id_cliente=dat.id_cliente AND acc.password=SHA1(?) AND acc.email=? ";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, pass);
                ps.setString(2, email);

                ResultSet rs = ps.executeQuery();

                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();
                DataClient dataClient = null;
                DataClientExtractor dataClientExtractor = new DataClientExtractor();
                if (rs.next()) {

                    account = accountExtractor.extract(rs);
                    dataClient = dataClientExtractor.extract(rs);
                    account.setDataClient(dataClient);
                }
                //mi ritorno  l'model.dao.account che ha anche i dati anagrafici
                return account;
            }
        }
    }



    @Override
    public Account searchAccountId(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "SELECT * FROM account AS acc WHERE (id_cliente=?);";

            try(   PreparedStatement ps = connection.prepareStatement(query)) {
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


        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT * FROM account AS acc WHERE amministratore=0;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
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
    public boolean deleteAccount(int id_Account) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="DELETE FROM account WHERE (id_cliente=?);";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_Account);
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean createAccount(Account account) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="INSERT INTO account (email, password, amministratore, username) VALUES(?,?,?,?);";

            try(PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, account.getEmail());
                ps.setString(2, account.getPassword());
                ps.setBoolean(3, account.isAdmin());
                ps.setString(4, account.getUsername());

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

        try(Connection connection=SqlDao.getConnection()) {
            String query="UPDATE account SET password=?,username=?,email=? WHERE(id_cliente=?);";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, account.getPassword());
                ps.setString(2, account.getUsername());
                ps.setString(3, account.getEmail());
                ps.setInt(4, account.getId());

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public Account searchAccountIdWithOrders(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT * FROM account AS acc,orders AS ord " +
                    "WHERE acc.id_cliente=ord.id_cliente " +
                    "AND (id_cliente=?);";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                Account account = null;
                AccountExtractor acc = new AccountExtractor();

                if (rs.next()) {
                    account = acc.extract(rs); //estraggo l'model.dao.account
                    // model.dao.account.setOrders(new ArrayList<>());
                    OrderExtractor orderExtractor = new OrderExtractor();
                    account.getOrders().add(orderExtractor.extract(rs)); //inserimento primo elemento dell'array
                    while (rs.next()) {
                        account.getOrders().add(orderExtractor.extract(rs)); //inserimento del resto dell'array
                    }
                }

                return account;
            }
        }
    }

    @Override
    public Account searchAccountIdWithAddress(int id) throws SQLException {


        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT * FROM account AS acc, address AS addr " +
                    "WHERE acc.id_cliente=addr.id_cliente AND acc.id_cliente=?; ";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();
                Address address = null;
                AddressExtractor addressExtractor = new AddressExtractor();

                if (rs.next()) {

                    account = accountExtractor.extract(rs);//ESTRAI L ACCOUNT
                    address = addressExtractor.extract(rs); //ESTRAI L INDIRIZZO

                    account.setAddress(address);//INSERISCO L INDIRIZZO NELL ACCOUNT
                }

                //mi ritorno  l'model.dao.account che ha anche i dati dell'indirizzo
                return account;
            }

        }
    }

    @Override
    public Account searchAccountIdWithDataClient(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT * " +
                    "FROM account AS acc,dataclient AS dat " +
                    "WHERE acc.id_cliente=dat.id_cliente AND acc.id_cliente=?; ";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();
                DataClient dataClient = null;
                DataClientExtractor dataClientExtractor = new DataClientExtractor();
                if (rs.next()) {

                    account = accountExtractor.extract(rs);
                    dataClient = dataClientExtractor.extract(rs);
                    account.setDataClient(dataClient);
                }
                //mi ritorno  l'model.dao.account che ha anche i dati anagrafici
                return account;
            }
        }
    }

  public   ArrayList<Account> searchAllAccountIdWithDataClientandAndress()throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT * " +
                    "FROM account AS acc,dataclient AS dat, address AS addr " +
                    "WHERE acc.id_cliente=dat.id_cliente AND acc.id_cliente=addr.id_cliente ";

            try (PreparedStatement ps = connection.prepareStatement(query)) {


                ResultSet rs = ps.executeQuery();
                ArrayList<Account> accounts = new ArrayList<>();
                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();
                DataClient dataClient = null;
                DataClientExtractor dataClientExtractor = new DataClientExtractor();
                Address address = null;
                AddressExtractor addressExtractor = new AddressExtractor();


                while (rs.next()) {

                    account = accountExtractor.extract(rs);
                    dataClient = dataClientExtractor.extract(rs);
                    address = addressExtractor.extract(rs);
                    account.setDataClient(dataClient);
                    account.setAddress(address);
                    accounts.add(account);
                }

                return accounts;
            }

        }
    }


    @Override
    public Account  searchAccountIdWithDataClientandAndressLogin(String pass,String email) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT * " +
                    "FROM account AS acc,dataclient AS dat, address AS addr " +
                    "WHERE acc.id_cliente=dat.id_cliente AND acc.id_cliente=addr.id_cliente  " +
                    "AND acc.password=SHA1(?) AND acc.email=? ; ";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, pass);
                ps.setString(2, email);

                ResultSet rs = ps.executeQuery();
                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();
                DataClient dataClient = null;
                DataClientExtractor dataClientExtractor = new DataClientExtractor();
                Address address = null;

                AddressExtractor addressExtractor = new AddressExtractor();
                if (rs.next()) {
                    account = accountExtractor.extract(rs);
                    dataClient = dataClientExtractor.extract(rs);
                    address = addressExtractor.extract(rs);
                    account.setDataClient(dataClient);
                    account.setAddress(address);
                }
                //mi ritorno  l'model.dao.account che ha anche i dati anagrafici e dati fatturazione
                return account;
            }
        }
    }




    @Override
    public Account  searchAccountIdWithDataClientandAndress(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT * " +
                    "FROM account AS acc,dataclient AS dat, address AS addr " +
                    "WHERE acc.id_cliente=dat.id_cliente AND acc.id_cliente=addr.id_cliente  " +
                    "AND acc.id_cliente=? ;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);


                ResultSet rs = ps.executeQuery();
                Account account = null;
                AccountExtractor accountExtractor = new AccountExtractor();
                DataClient dataClient = null;
                DataClientExtractor dataClientExtractor = new DataClientExtractor();
                Address address = null;

                AddressExtractor addressExtractor = new AddressExtractor();
                if (rs.next()) {
                    account = accountExtractor.extract(rs);
                    dataClient = dataClientExtractor.extract(rs);
                    address = addressExtractor.extract(rs);
                    account.setDataClient(dataClient);
                    account.setAddress(address);
                }
                //mi ritorno  l'model.dao.account che ha anche i dati anagrafici e dati fatturazione
                return account;
            }
        }
    }
}
