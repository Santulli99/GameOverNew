package model.dao.address;

import model.entity.Account;
import model.dao.account.AccountExtractor;
import model.entity.Address;
import model.dao.storage.SqlDao;

import java.sql.*;
import java.util.ArrayList;

public class SqlAddressDao implements  AddressDao<SQLException> {

    public SqlAddressDao()  {
        super();
    }

    @Override
    public Address searchAddress(int id) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT *  FROM address AS addr WHERE id_indirizzo=?;";
            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                AddressExtractor addressExtractor = new AddressExtractor();
                Address address = null;

                if (rs.next()) {
                    address = addressExtractor.extract(rs);
                }
                return address;
            }
        }
    }

    @Override
    public ArrayList<Address> searchAllAddress() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT *  FROM address AS addr;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {

                ResultSet rs = ps.executeQuery();
                AddressExtractor addressExtractor = new AddressExtractor();
                ArrayList<Address> list_address = new ArrayList<>();
                Address address = null;
                while (rs.next()) {
                    address = addressExtractor.extract(rs);
                    list_address.add(address);
                }
                return list_address;
            }
        }
    }


    @Override
    public boolean createAddress(Address address) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="INSERT INTO address(id_cliente, via, civico, provincia, CAP, città) VALUES (?,?,?,?,?,?);";
            try(PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                //vedi bene questo passaggio per la chiave esterna
                ps.setInt(1, address.getAccount().getId());

                ps.setString(2, address.getStreet());
                ps.setInt(3, address.getStreetNumber());
                ps.setString(4, address.getProvince());
                ps.setInt(5, address.getPostalCode());
                ps.setString(6, address.getCity());

                int rows = ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                address.setId(id);
                return rows == 1;
            }
        }
    }

    @Override
    public boolean updateAddress(Address address) throws SQLException {
/* gerry*/
        try(Connection connection=SqlDao.getConnection()) {
            String query="UPDATE address SET  via=?, civico=?, provincia=?, CAP=?, città=?  WHERE id_cliente=?;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {

                ps.setString(1, address.getStreet());
                ps.setInt(2, address.getStreetNumber());
                ps.setString(3, address.getProvince());
                ps.setInt(4, address.getPostalCode());
                ps.setString(5, address.getCity());
                ps.setInt(6, address.getAccount().getId());


                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean deleteAddress(int id_account) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="DELETE FROM address WHERE  (id_cliente=?); ";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_account);

                int rows = ps.executeUpdate();

                return rows == 1;
            }
        }
    }

    @Override
    public Address searchAddressthroughIdAccount(Account account) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT *  FROM address AS addr WHERE addr.id_cliente=?;";
            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, account.getId());

                ResultSet rs = ps.executeQuery();
                AddressExtractor addressExtractor = new AddressExtractor();
                Address address = null;
                if (rs.next()) {
                    address = addressExtractor.extract(rs);
                }
                return address;
            }
        }

    }

    @Override
    public Address searchAddressWithAccount(int id_Account) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT * " +
                    "FROM address AS addr, account AS acc " +
                    "WHERE addr.id_cliente=acc.id_cliente AND addr.id_cliente=?;";
            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_Account);
                ResultSet rs = ps.executeQuery();

                Address address = null;
                Account account = null;
                AddressExtractor addressExtractor = new AddressExtractor();
                AccountExtractor accountExtractor = new AccountExtractor();
                if (rs.next()) {
                    address = addressExtractor.extract(rs);
                    account = accountExtractor.extract(rs);
                    address.setAccount(account);
                }
                return address;
            }
        }
    }
}
