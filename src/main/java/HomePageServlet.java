import autenticazione.service.AutenticazioneService;
import autenticazione.service.AutenticazioneServiceImp;
import model.dao.account.SqlAccountDao;
import model.dao.cart.SqlCartDao;
import model.dao.order.SqlOrderDao;
import model.dao.product.SqlProductDao;
import model.dao.wishlist.SqlWishlistDao;
import model.entity.Account;
import model.entity.Order;
import model.entity.Product;
import model.entity.Wishlist;
import model.entity.cart.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "HomePageServlet", value = "/HomePageServlet/*")
public class HomePageServlet extends HttpServlet {
    private RequestDispatcher dispatcher;
    private SqlAccountDao accountDao=new SqlAccountDao();
    private SqlProductDao productDao=new SqlProductDao();

    private SqlOrderDao orderDao=new SqlOrderDao();

    private SqlCartDao cartDao=new SqlCartDao();
    private SqlWishlistDao wishlistDao=new SqlWishlistDao();
    private Account account=new Account();
    private AutenticazioneService autenticazioneService=new AutenticazioneServiceImp();

    public void init() throws ServletException {
        super.init();

        /** carico i prodotti nella vetrina nella memoria globlale **/

        try {
            ArrayList<Product> products;
            SqlProductDao sqlProductDao= new SqlProductDao();
            products=sqlProductDao.searchProductsvetrina(2);
            getServletContext().setAttribute("vetrina",products);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";
        System.out.println("IL PATH è:"+path);
        HttpSession session = request.getSession(false);

        switch (path){
            case "/":
                break;
            case "/homePageAdmin":
                try {
                    /* numero prodotti*/
                    ArrayList<Product> products=productDao.searchAllProducts();
                    request.getSession(false).setAttribute("n_products",products.size());

                    /*numero utenti*/
                    ArrayList<Account> accounts= accountDao.searchAllAccount();
                    request.getSession(false).setAttribute("n_client",accounts.size());

                    /*Numero ordini mensili*/
                    ArrayList<Order> orders=orderDao.searchAllOrderWithProducts();
                    ArrayList<Order> newOrdini=new ArrayList<>();
                    LocalDate now=LocalDate.now();

                    for(int i=0;i<orders.size();i++){
                        if(orders.get(i).getDate().getMonth().equals(now.getMonth())){
                            newOrdini.add(orders.get(i));
                        }
                    }
                    /*Totale incasso mensile*/
                    double totale=0;
                    for(int j=0;j<newOrdini.size();j++){
                        totale+=newOrdini.get(j).getTotal();
                    }
                    request.getSession(false).setAttribute("totale_incasso",Math.round(totale*100.0)/100.0);

                    request.getSession(false).setAttribute("n_ordini",newOrdini.size());

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;
            case "/homePageUtent":
                Account account= (Account) session.getAttribute("account");
                Wishlist wishlist=new Wishlist();
                try {
                    wishlist=wishlistDao.searchWishlistWithAccount(account);
                    if(wishlist!=null){
                        //deve caricare la lista dei desideri se cè
                        session.setAttribute("wishlist",wishlist);
                    }

                    //realizzare carrello
                    Cart cart=new Cart();
                    cart=cartDao.searchCartWithAccount(account);
                    if(cart !=null) {
                        request.getSession(false).setAttribute("carrello", cart);
                        request.getSession(false).setAttribute("totale", Math.round(cart.totalPrice() * 100.0) / 100.0);
                        request.getSession(false).setAttribute("quantity", cart.getCartItems().size());
                    }
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/utente.jsp");
                    dispatcher.forward(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "/homePage":
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/home.jsp");
                dispatcher.forward(request, response);
                break;

        }
    }


}


