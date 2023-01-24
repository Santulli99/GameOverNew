package model.dao.wishlist;

import model.dao.category.CategoryExtractor;
import model.dao.platform.PlatformExtractor;
import model.dao.product.ProductExtractor;
import model.dao.storage.SqlDao;
import model.entity.*;

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
            String query = "select * from  wishlist,product AS pro,platform AS pla,category AS cat  where id_cliente=?  and pro.id_prodotto=wishlist.id_prodotto " +
                           "and pro.id_piattaforma=pla.id and pro.id_categoria=cat.id_categoria";

            try (PreparedStatement ps1 = connection.prepareStatement(query)) {
                ps1.setInt(1, account.getId());

                ResultSet rs = ps1.executeQuery();
                Prodotto prodotto;
                ProductExtractor productExtractor = new ProductExtractor();
                CategoryExtractor categoryExtractor=new CategoryExtractor();
                PlatformExtractor platformExtractor=new PlatformExtractor();
                ArrayList<Prodotto> prodotti = new ArrayList<>();
                Platform platform=new Platform();
                Category category=new Category();
                while (rs.next()) {
                    category=categoryExtractor.extract(rs);
                    platform=platformExtractor.extract(rs);
                    prodotto = productExtractor.extract(rs);

                    prodotto.setPlatform(platform);
                    prodotto.setCategory(category);
                    prodotti.add(prodotto);
                }
                ListaDesideri listaDesideri = new ListaDesideri();
                listaDesideri.setProducts(prodotti);
                return listaDesideri;

            }
        }
    }



}