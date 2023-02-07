import autenticazione.service.AutenticazioneService;
import autenticazione.service.AutenticazioneServiceImp;
import gestioneAcquisti.service.GestioneAcquistiServiceImp;
import model.dao.account.SqlAccountDao;
import model.dao.cart.SqlCartDao;
import model.dao.order.SqlOrderDao;
import model.dao.product.SqlProductDao;
import model.dao.wishlist.SqlListaDesideriDao;
import model.entity.Account;
import model.entity.Order;
import model.entity.Prodotto;
import model.entity.cart.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * classe principale che si occupa di recuperare tutti i prodotti dal DB di una determinata piattaforma
 * e caricarli  nella vetrina del sito web in modo tale da visualizzarli sulla HomePage
 * Implementa il controller per l'HomePage
 *
 * @author Andrea Serpico,Andrea Santulli,Gerardo Esposito
 */
@WebServlet(name = "HomePageController", value = "/HomePageController/*")
public class HomePageController extends HttpServlet {
    private RequestDispatcher dispatcher;
    private SqlAccountDao accountDao = new SqlAccountDao();
    private SqlProductDao productDao = new SqlProductDao();

    private SqlOrderDao orderDao = new SqlOrderDao();
    private Cart cart = new Cart();

    private SqlCartDao cartDao = new SqlCartDao();
    private SqlListaDesideriDao wishlistDao = new SqlListaDesideriDao();
    private Account account = new Account();
    private AutenticazioneService autenticazioneService = new AutenticazioneServiceImp();
    private GestioneAcquistiServiceImp gestioneAcquistiServiceImp = new GestioneAcquistiServiceImp();

    public void init() throws ServletException {
        super.init();
        try {
            ArrayList<Prodotto> prodottos;
            SqlProductDao sqlProductDao = new SqlProductDao();
            prodottos = sqlProductDao.searchProductsvetrina(2);
            getServletContext().setAttribute("vetrina", prodottos);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path) {
            case "/":
                break;
            case "/homePageUtent":
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/utente.jsp");
                dispatcher.forward(request, response);
                break;
            case "/homePage":
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/home.jsp");
                dispatcher.forward(request, response);
                break;
            case "/homePageAdmin":
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                dispatcher.forward(request, response);
                break;

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path) {
            case "/":
                break;
            case "/homePageAdmin":
                try {
                    ArrayList<Prodotto> prodottos = productDao.searchAllProducts();
                    request.getSession(false).setAttribute("n_products", prodottos.size());
                    ArrayList<Account> accounts = accountDao.searchAllAccount();
                    request.getSession(false).setAttribute("n_client", accounts.size());
                    ArrayList<Order> orders = orderDao.searchAllOrderWithProducts();
                    ArrayList<Order> newOrdini = new ArrayList<>();
                    LocalDate now = LocalDate.now();

                    for (int i = 0; i < orders.size(); i++) {
                        if (orders.get(i).getDate().getMonth().equals(now.getMonth())) {
                            newOrdini.add(orders.get(i));
                        }
                    }
                    double totale = 0;
                    for (int j = 0; j < newOrdini.size(); j++) {
                        totale += newOrdini.get(j).getTotal();
                    }
                    request.getSession(false).setAttribute("totale_incasso", Math.round(totale * 100.0) / 100.0);

                    request.getSession(false).setAttribute("n_ordini", newOrdini.size());

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;
            case "/homePageUtent":
                account = (Account) request.getSession(false).getAttribute("account");
                cart = gestioneAcquistiServiceImp.getCart(account);
                if (cart != null) {
                    request.getSession(false).setAttribute("carrello", cart);
                    request.getSession(false).setAttribute("totale", Math.round(cart.totalPrice() * 100.0) / 100.0);
                    request.getSession(false).setAttribute("quantity", cart.getCartItems().size());
                }
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/utente.jsp");
                dispatcher.forward(request, response);
                break;
        }

    }
}




