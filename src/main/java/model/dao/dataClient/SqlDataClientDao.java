package model.dao.dataClient;

import model.entity.Account;
import model.dao.account.AccountExtractor;

import model.entity.DataClient;
import model.dao.storage.SqlDao;

import java.sql.*;
import java.util.ArrayList;

public class  SqlDataClientDao implements DataClientDao<SQLException> {

    public SqlDataClientDao()  {
        super();
    }


    @Override
    public DataClient searchDataClient(int id_account) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT *  FROM dataclient AS dat WHERE (id_cliente=?);";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_account);
                ResultSet rs = ps.executeQuery();

                DataClient dataClient = null;
                DataClientExtractor dct = new DataClientExtractor();

                if (rs.next()) {
                    dataClient = dct.extract(rs);
                }
                return dataClient;
            }
        }
    }

   public DataClient searchDataClientWithTel(String num) throws SQLException{
       try(Connection connection=SqlDao.getConnection()) {
           String query="SELECT *  FROM dataclient AS dat WHERE num_telefono=?;";

           try(PreparedStatement ps = connection.prepareStatement(query)) {
               ps.setString(1,num);
               ResultSet rs = ps.executeQuery();

               DataClient dataClient = null;
               DataClientExtractor dct = new DataClientExtractor();

               if (rs.next()) {
                   dataClient = dct.extract(rs);
               }
               return dataClient;
           }
       }

    }

    @Override
    public DataClient searchDataClientWithCf(String cf) throws SQLException {
        try(Connection connection=SqlDao.getConnection()) {
            String query="SELECT *  FROM dataclient AS dat WHERE codice_fiscale=?;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1,cf);
                ResultSet rs = ps.executeQuery();

                DataClient dataClient = null;
                DataClientExtractor dct = new DataClientExtractor();

                if (rs.next()) {
                    dataClient = dct.extract(rs);
                }
                return dataClient;
            }
        }
    }

    @Override
    public ArrayList<DataClient> searchAllDataClient() throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
        String query="SELECT *  FROM dataclient AS dat;";

            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ResultSet rs = ps.executeQuery();

                ArrayList<DataClient> dataClients = new ArrayList<>();
                DataClient dataClient = null;
                DataClientExtractor dct = new DataClientExtractor();

                while (rs.next()) {
                    dataClient = dct.extract(rs);
                    dataClients.add(dataClient);
                }
                return dataClients;
            }
        }
    }

    @Override
    public boolean createDataClient(DataClient dataClient) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
        String query="INSERT INTO dataclient(codice_fiscale, id_cliente, nome, cognome, data_nascita, num_telefono,città_nascita) VALUES (?,?,?,?,?,?,?);";

            try(PreparedStatement ps = connection.prepareStatement(query)) {

                ps.setString(1, dataClient.getCf());
                ps.setInt(2, dataClient.getAccount().getId());
                ps.setString(3, dataClient.getFirstName());
                ps.setString(4, dataClient.getLastName());
                ps.setDate(5, Date.valueOf(dataClient.getDate()));
                ps.setString(6, dataClient.getCell());
                ps.setString(7, dataClient.getCity());

                int rows = ps.executeUpdate();

                return rows == 1;
            }
        }
    }

    @Override
    public boolean updateDataClient(DataClient dataClient,Account account) throws SQLException {

        try(Connection connection=SqlDao.getConnection()) {
            String query = "UPDATE dataclient SET codice_fiscale=?, nome=?, cognome=? , data_nascita=? , num_telefono=? , città_nascita=?  WHERE id_cliente=?";

            try(PreparedStatement ps = connection.prepareStatement(query)){

            ps.setString(1,dataClient.getCf());
                ps.setString(2,dataClient.getFirstName());
                ps.setString(3,dataClient.getLastName());
            ps.setDate(4, Date.valueOf(dataClient.getDate()));
            ps.setString(5, dataClient.getCell());
            ps.setString(6,dataClient.getCity());
            ps.setInt(7,account.getId());

            int rows = ps.executeUpdate();
            return rows == 1;
            }
        }
    }


    @Override
    public boolean deleteDataClient(DataClient dataClient) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "DELETE FROM dataclient WHERE  (codice_fiscale=?);";

            try (PreparedStatement ps = connection.prepareStatement(query)) {

                ps.setString(1, dataClient.getCf());

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }


    @Override
    public DataClient searchDataClientWithClient ( int id_Account) throws SQLException {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "SELECT * " +
                    "FROM dataclient AS dat, account AS acc " +
                    "WHERE dat.id_cliente=acc.id_cliente AND dat.id_cliente=?;";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, id_Account);
                ResultSet rs = ps.executeQuery();
                DataClient dataClient = null;
                Account account = null;
                DataClientExtractor dataClientExtractor = new DataClientExtractor();
                AccountExtractor accountExtractor = new AccountExtractor();
                if (rs.next()) {
                    dataClient = dataClientExtractor.extract(rs);
                    account = accountExtractor.extract(rs);
                    dataClient.setAccount(account);
                }
                return dataClient;
            }
        }
    }

}

