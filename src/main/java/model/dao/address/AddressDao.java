package model.dao.address;

import model.entity.Account;
import model.entity.Address;

import java.util.ArrayList;

public interface AddressDao<E extends Exception > {

    //?
    Address searchAddress(int id ) throws E;
    ArrayList<Address> searchAllAddress() throws E;
    boolean createAddress(Address address) throws E;
    boolean updateAddress(Address address) throws E;
    boolean deleteAddress(int id_Account) throws E;

    //ritorno indirizzo tramite ID  cliente

    Address searchAddressthroughIdAccount(Account account) throws E;
    Address searchAddressWithAccount(int id_Account) throws E;

}
