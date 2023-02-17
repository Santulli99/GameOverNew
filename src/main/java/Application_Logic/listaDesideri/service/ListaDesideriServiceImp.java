package Application_Logic.listaDesideri.service;

import Storage.product.SqlProductDao;
import Storage.wishlist.SqlListaDesideriDao;
import Application_Logic.entity.Account;
import Application_Logic.entity.ListaDesideri;
import Application_Logic.entity.Prodotto;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * implementa la classe che esplicita i metodi definiti nell'interfaccia di ListaDesideri
 *
 * @author Andrea Santulli
 */

public class ListaDesideriServiceImp implements ListaDesideriService {
    private SqlProductDao prodottoDao=new SqlProductDao();
    ListaDesideri listaDesideri=new ListaDesideri();
    private SqlListaDesideriDao listaDesideriDao=new SqlListaDesideriDao();

    /**
     * firma del metodo che implementa l'aggiunta di un prodotto alla lista desideri
     *
     * @param account  oggetto della classe Account usato  per aggiungere un prodotto alla lista desideri dell'account loggato in quel momento
     * @param prodotto oggetto della classe Prodotto che  contiene i dati del prodotto da aggiungere alla lista desideri
     * @return un boolean indica la riuscita dell'operazione
     */

    @Override
    public boolean aggiungiProdottoListaDesideri(Prodotto prodotto, Account account) {
        try {
            listaDesideri=listaDesideriDao.cercaListaDesideriPerUtente(account);
            if(!listaDesideri.containsListaDesideri(prodotto)){
                listaDesideriDao.inserisciProdottoListaDesideri(account,prodotto);
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * firma del metodo che implementa la rimozione di un prodotto dalla lista desideri dell'account loggato in quel momento
     *
     * @param account  oggetto della classe Account usato  per rimuovere un prodotto dalla lista desideri dell'account loggato in quel momento
     * @param prodotto oggetto della classe Prodotto che  contiene i dati del prodotto da rimuovere dalla lista desideri
     * @return un boolean indica la riuscita dell'operazione
     */

    @Override
    public boolean eliminaProdottoListaDesideri(Prodotto prodotto, Account account) {
        try {
            listaDesideri=listaDesideriDao.cercaListaDesideriPerUtente(account);
            if(listaDesideri.containsListaDesideri(prodotto)){
                listaDesideriDao.eliminaProdottoListaDesideri(account,prodotto);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * firma del metodo che restituisce la lista desideri relativa all'account loggato in quel momento
     *
     * @param account oggetto della classe Account usato per farmi restituire la lista desideri
     * @return oggetto della classe ListaDesideri contenente i dati relativi ai prodotti desiderati dall'account loggato in quel momento
     */

    @Override
    public ListaDesideri getListaDesideri(Account account) {
        ArrayList<Prodotto> prodotti=new ArrayList<>();
        try {
            listaDesideri=listaDesideriDao.cercaListaDesideriPerUtente(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return listaDesideri;
    }
}
