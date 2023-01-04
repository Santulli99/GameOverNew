package model.dao.dataClient;

import model.entity.DataClient;
import model.dao.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataClientExtractor  implements ResultSetExtractor<DataClient> {
    @Override
    public DataClient extract(ResultSet resultSet) throws SQLException {
        DataClient dataClient=new DataClient();
        dataClient.setCf(resultSet.getString("dat.codice_fiscale"));
        dataClient.setFirstName(resultSet.getString("dat.nome"));
        dataClient.setLastName(resultSet.getString("dat.cognome"));
        dataClient.setCell(resultSet.getString("dat.num_telefono"));
        dataClient.setCity(resultSet.getString("dat.città_nascita"));
        dataClient.setDate(resultSet.getDate("dat.data_nascita").toLocalDate());
        return dataClient;
    }
}
