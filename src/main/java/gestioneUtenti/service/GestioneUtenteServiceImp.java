package gestioneUtenti.service;

import model.dao.account.SqlAccountDao;
import model.dao.address.SqlAddressDao;
import model.dao.dataClient.SqlDataClientDao;
import model.dao.product.ProductDao;
import model.dao.product.SqlProductDao;
import model.entity.Account;
import model.entity.Address;
import model.entity.DataClient;
import validate.ValidateForm;

import java.sql.SQLException;
import java.util.ArrayList;

public class GestioneUtenteServiceImp implements GestioneUtenteService {

    private SqlAccountDao accountDAO;
    private SqlProductDao productDA0;

    private SqlAddressDao addressDAO;

    private SqlDataClientDao dataClientDAO;
    private Account account;
    private DataClient dataClient;

    public GestioneUtenteServiceImp() {
        accountDAO = new SqlAccountDao();
        productDA0 = new SqlProductDao();
        addressDAO = new SqlAddressDao();
        dataClientDAO = new SqlDataClientDao();
    }

    @Override
    public boolean ModificaDatiIndirizzo(Address address) {
        try {
            if (addressDAO.updateAddress(address))
                return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }


    @Override
    public boolean ModificaDatiAnagrafici(DataClient dataClient, Account account) {
        try {
            if (dataClientDAO.updateDataClient(dataClient, account))
                return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean ModificaDatiAccount(Account account) {
        try {
            if (accountDAO.updateAccount(account))
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean ModificaPasswordAccount(Account account) {
        try {
            if (accountDAO.updatePasswordAccount(account))
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public Account getAccountDati(Account account) {
        int id = account.getId();
        try {
            if ((account = accountDAO.searchAccountIdWithDataClientandAndress(id)) != null)
                return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Account getAccount(int id) {
        try {
            if ((account = accountDAO.searchAccountId(id)) != null)
                return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            if ((accounts = accountDAO.searchAllAccount()) != null)
                return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account getAccountEmail(String email) {
        try {
            if ((account = accountDAO.searchAccountEmail(email)) != null)
                return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public DataClient getDataClientTel(String numero) {
        try {
            if ((dataClientDAO.searchDataClientWithTel(numero)) != null)
                return dataClient;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public DataClient getDataClientCf(String cf) {
        try {
            if ((dataClient = dataClientDAO.searchDataClientWithCf(cf)) != null)
                return dataClient;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
