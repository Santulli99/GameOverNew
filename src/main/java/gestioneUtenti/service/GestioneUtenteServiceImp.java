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

public class GestioneUtenteServiceImp  implements GestioneUtenteService{

    private SqlAccountDao accountDAO;
    private SqlProductDao productDA0;

    private SqlAddressDao addressDAO;

    private SqlDataClientDao dataClientDAO;

    public GestioneUtenteServiceImp(){
        accountDAO=new SqlAccountDao();
        productDA0=new SqlProductDao();
        addressDAO=new SqlAddressDao();
        dataClientDAO=new SqlDataClientDao();
    }
    @Override
    public boolean ModificaDatiIndirizzo(Address address) {
        try {
            addressDAO.updateAddress(address);
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public boolean ModificaDatiAnagrafici(DataClient dataClient,Account account) {
        try {
            dataClientDAO.updateDataClient(dataClient,account);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
