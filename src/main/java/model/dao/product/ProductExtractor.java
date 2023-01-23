package model.dao.product;

import model.entity.Prodotto;
import model.dao.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductExtractor implements ResultSetExtractor<Prodotto> {
    @Override
    public Prodotto extract(ResultSet resultSet) throws SQLException {
        Prodotto prodotto =new Prodotto();
        prodotto.setId(resultSet.getInt("pro.id_prodotto"));
        prodotto.setPrice(resultSet.getDouble("pro.prezzo"));
        prodotto.setProductName(resultSet.getString("pro.nome"));
        prodotto.setDescription(resultSet.getString("pro.descrizione"));
        prodotto.setCover(resultSet.getString("pro.path_img"));
        prodotto.setDate(resultSet.getDate("pro.data_uscita").toLocalDate());
        prodotto.setValutazioneMedia(resultSet.getDouble("pro.valutazione_media"));
        return prodotto;
    }
}
