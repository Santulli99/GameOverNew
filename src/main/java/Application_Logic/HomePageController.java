package Application_Logic;

import Application_Logic.gestioneAcquisti.service.GestioneAcquistiServiceImp;
import Application_Logic.gestioneProdotto.service.GestioneProdottoServiceImp;
import Application_Logic.gestioneUtenti.service.GestioneUtenteServiceImp;
import Storage.product.SqlProductDao;
import Application_Logic.entity.Account;
import Application_Logic.entity.Order;
import Application_Logic.entity.Prodotto;
import Application_Logic.entity.cart.Cart;
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
    private Cart cart = new Cart();
    private Account account = new Account();

    private GestioneAcquistiServiceImp gestioneAcquistiServiceImp = new GestioneAcquistiServiceImp();

    private GestioneProdottoServiceImp gestioneProdottoServiceImp = new GestioneProdottoServiceImp();
    private GestioneUtenteServiceImp gestioneUtenteServiceImp = new GestioneUtenteServiceImp();

    public void init() throws ServletException {
        super.init();
        try {
            ArrayList<Prodotto> products;
            SqlProductDao sqlProductDao= new SqlProductDao();
            products=sqlProductDao.searchProductsvetrina("PS4");
            getServletContext().setAttribute("vetrina",products);
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
                ArrayList<Prodotto> prodottos = gestioneProdottoServiceImp.getAllProdotti();
                request.getSession(false).setAttribute("n_products", prodottos.size());
                ArrayList<Account> accounts = gestioneUtenteServiceImp.getAllAccount();
                request.getSession(false).setAttribute("n_client", accounts.size());
                ArrayList<Order> orders = gestioneAcquistiServiceImp.getAllOrdiniConProdotti();
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




