package model.dao.account;



import model.entity.Account;

import java.util.ArrayList;

public interface AccountDao<E extends Exception> {

    // AMMMINISTRATORE

    Account searchAccountId(int id) throws E;
    ArrayList<Account> searchAllAccount() throws E;

    //come gestire l'autoincremento quando cancelliamo?
    boolean deleteAccount(int id) throws E;

    // UTENTE
    Account searchAccountEmail(String email) throws E;
    Account searchAccountWithEmail(String pass,String email) throws E;
    boolean createAccount(Account account) throws E;
    boolean updateAccount(Account account) throws E;


    //query che mappano olte all'model.dao.account anche ordini indirizzo e dati anagrafici


    Account searchAccountIdWithOrders(int id) throws E;
    Account searchAccountIdWithAddress(int id) throws  E;
    Account searchAccountIdWithDataClient(int id) throws E;
    Account searchAccountIdWithDataClientandAndress(int id)throws E;

    Account searchAccountIdWithDataClientandAndressLogin(String pass,String email)throws E;

    ArrayList<Account> searchAllAccountIdWithDataClientandAndress()throws E;



}
