package model.dao.address;

import model.entity.Address;
import model.dao.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressExtractor implements ResultSetExtractor<Address> {
    @Override
    public Address extract(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setId(resultSet.getInt("addr.id_indirizzo"));
        address.setStreet(resultSet.getString("addr.via"));
        address.setCity(resultSet.getString("addr.città"));
        address.setProvince(resultSet.getString("addr.provincia"));
        address.setStreetNumber(resultSet.getInt("addr.civico"));
        address.setPostalCode(resultSet.getInt("addr.CAP"));
        return address;
    }
}
