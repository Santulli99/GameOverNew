package Application_Logic.gestioneUtenti.service;

import Storage.account.SqlAccountDao;

import Application_Logic.entity.Account;

import java.sql.SQLException;
import java.util.ArrayList;


/**
 * implementa la classe che esplicita i metodi definiti nell'interfaccia di GestioneUtenteService
 *
 * @author Andrea Santulli
 */


public class GestioneUtenteServiceImp implements GestioneUtenteService {

    private SqlAccountDao accountDAO;


    private Account account;

    public GestioneUtenteServiceImp() {
        accountDAO = new SqlAccountDao();
    }


    /**
     * Implementa la funzionalit√† per la modifica dei dati account di un utente
     *
     * @param account oggetto della classe Account che contiene le modifiche da applicare
     * @return un boolean indica la riuscita dell'operazione
     */

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

    /**
     * Implementa la funzionalit√† per la modifica della password dell'account di un utente
     *
     * @param account oggetto della classe Account che contiene le modifiche da applicare
     * @return un boolean indica la riuscita dell'operazione
     */

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


    /**
     * Implementa la funzionalit√†  che restituisce i dati dell'account
     *
     * @param id_account int che contiene l'id dell'account da prendere
     * @return Account con uno specifico id
     */

    public Account getAccount(int id_account) {
        try {
            if ((account = accountDAO.searchAccountId(id_account)) != null)
                return account;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Implementa la funzionalit√†  che restituisce tutti gli account
     *
     * @return ArrayList di account
     */

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

    /**
     * Implementa la funzionalit√† che restituisce gli account data l'email
     *
     * @param email String che contiene l'email dell'account da estrarre
     * @return oggetto della classe Account
     */

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

}
