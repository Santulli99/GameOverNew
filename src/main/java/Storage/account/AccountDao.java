package Storage.account;

import Application_Logic.entity.Account;
import java.util.ArrayList;

public interface AccountDao<E extends Exception> {

    Account searchAccountId(int id) throws E;
    ArrayList<Account> searchAllAccount() throws E;
    Account searchAccountEmail(String email) throws E;
    boolean createAccount(Account account) throws E;
    boolean updateAccount(Account account) throws E;
    boolean updatePasswordAccount(Account account) throws E;
    public Account  searchAccountLogin(String pass,String email) throws  E;
}
