package model.dao.platform;

import model.entity.Platform;
import model.dao.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlatformExtractor implements ResultSetExtractor<Platform> {
    @Override
    public Platform extract(ResultSet resultSet) throws SQLException {
        Platform platform = new Platform();
        platform.setId(resultSet.getInt("pla.id"));
        platform.setPlatformName(resultSet.getString("pla.nome"));
        return platform;
    }
}
