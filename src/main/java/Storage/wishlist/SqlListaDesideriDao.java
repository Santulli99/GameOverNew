package Storage.wishlist;
import Application_Logic.entity.Account;
import Application_Logic.entity.ListaDesideri;
import Application_Logic.entity.Prodotto;
import Storage.product.ProductExtractor;
import Storage.storage.SqlDao;

import java.sql.*;
import java.util.ArrayList;

public class SqlListaDesideriDao implements ListaDesideriDao {
    @Override
    public boolean eliminaProdottoListaDesideri(Account account, Prodotto prodotto) throws Exception {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "DELETE FROM wishlist WHERE (id_prodotto=? AND id_cliente=?);";

            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, prodotto.getId());
                ps.setInt(2, account.getId());

                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }

    }

    @Override
    public boolean inserisciProdottoListaDesideri(Account account, Prodotto prodotto) throws Exception {
        try (Connection connection = SqlDao.getConnection()) {
            String query = "INSERT INTO wishlist (id_cliente,id_prodotto) VALUES(?,?);";

            try (PreparedStatement ps1 = connection.prepareStatement(query)) {
                ps1.setInt(1, account.getId());
                ps1.setInt(2, prodotto.getId());

                int rows = ps1.executeUpdate();

                return rows == 1;
            }
        }
    }

    @Override
    public ListaDesideri cercaListaDesideriPerUtente(Account account) throws Exception {

        try (Connection connection = SqlDao.getConnection()) {
            String query = "select * from  wishlist,product AS pro where id_cliente=?  and pro.id_prodotto=wishlist.id_prodotto";

            try (PreparedStatement ps1 = connection.prepareStatement(query)) {
                ps1.setInt(1, account.getId());

                ResultSet rs = ps1.executeQuery();
                Prodotto prodotto;
                ProductExtractor productExtractor = new ProductExtractor();
                ArrayList<Prodotto> prodotti = new ArrayList<>();
                while (rs.next()) {
                    prodotto = productExtractor.extract(rs);
                    prodotti.add(prodotto);
                }
                ListaDesideri listaDesideri = new ListaDesideri();
                listaDesideri.setProducts(prodotti);
                return listaDesideri;

            }
        }
    }



}
