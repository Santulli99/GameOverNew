package model.dao.account;



import model.entity.Account;

import java.util.ArrayList;

public interface AccountDao<E extends Exception> {

    // AMMMINISTRATORE

    Account searchAccountId(int id) throws E;
    ArrayList<Account> searchAllAccount() throws E;

    // UTENTE
    Account searchAccountEmail(String email) throws E;

    boolean createAccount(Account account) throws E;
    boolean updateAccount(Account account) throws E;

    boolean updatePasswordAccount(Account account) throws E;

    public Account  searchAccountLogin(String pass,String email) throws  E;
}
