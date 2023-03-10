package Application_Logic.gestioneAcquisti.controller;

import Application_Logic.gestioneAcquisti.OrdinareCliente;
import Application_Logic.gestioneAcquisti.OrdinareDataCrescente;
import Application_Logic.gestioneAcquisti.OrdinareDataDecrescente;
import Application_Logic.gestioneAcquisti.RandomString;
import Application_Logic.gestioneAcquisti.service.GestioneAcquistiService;
import Application_Logic.gestioneAcquisti.service.GestioneAcquistiServiceImp;
import Application_Logic.gestioneProdotto.service.GestioneProdottoService;
import Application_Logic.listaDesideri.service.ListaDesideriService;
import com.google.gson.Gson;
import Application_Logic.gestioneProdotto.service.GestioneProdottoServiceImp;
import Application_Logic.listaDesideri.service.ListaDesideriServiceImp;
import Application_Logic.entity.Account;
import Application_Logic.entity.ListaDesideri;
import Application_Logic.entity.Order;
import Application_Logic.entity.Prodotto;
import Application_Logic.entity.cart.Cart;
import Application_Logic.entity.cart.CartItem;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;


/**
 * implementa il controller che si occupa  del sottosistema GestioneAcquisti
 *
 * @author Andrea Serpico
 * @see HttpServlet fornisce l'interfaccia per creare una servlet
 */

@WebServlet(name = "GestioneAcquistiController", value = "/GestioneAcquistiController/*")
public class GestioneAcquistiController extends HttpServlet {

    private GestioneProdottoService gestioneProdottoService = new GestioneProdottoServiceImp();
    private GestioneAcquistiService gestioneAcquistiService = new GestioneAcquistiServiceImp();

    private ListaDesideri listaDesideri = new ListaDesideri();
    private ListaDesideriService listaDesideriService = new ListaDesideriServiceImp();
    private Prodotto prodotto;
    private Account account;
    private Cart cart;
    private int idProdotto;
    private Order order = new Order();

    private RequestDispatcher dispatcher;
    private ArrayList<Order> orders = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path) {

            case "/":
                break;
            /**si rimuove un prodotto dal carrello**/
            case "/removeCart":
                account = (Account) request.getSession(false).getAttribute("account");
                idProdotto = Integer.parseInt(request.getParameter("id"));
                gestioneAcquistiService.rimuoviProdottoDalCarrello(account.getId(), idProdotto);
                cart = gestioneAcquistiService.getCart(account);

                request.getSession(false).setAttribute("totale", Math.round(cart.totalPrice() * 100.0) / 100.0);
                request.getSession(false).setAttribute("carrello", cart);
                request.getSession(false).setAttribute("quantity", cart.getCartItems().size());
                response.sendRedirect("/GameOverNew_war_exploded/GestioneAcquistiController/showCart");
                break;

            /**aggiungere prodotto al carrello per un utente non registrato(che lo porta alla pagina di login o registrazione)**/
            case "/addCartGhost":

                boolean acquistoFailed = false;
                request.setAttribute("acquistoFialed", acquistoFailed);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                dispatcher.forward(request, response);

                break;
            /**aggiunta di un prodotto nel carrello dell'utente**/
            case "/addCart":
                account = (Account) request.getSession(false).getAttribute("account");
                int idProdotto = Integer.parseInt(request.getParameter("id"));
                prodotto = gestioneProdottoService.getProdotto(idProdotto);
                if (prodotto != null) {

                    if (request.getSession(false).getAttribute("carrello") == null) {
                        request.getSession(false).setAttribute("carrello", new Cart());
                    }

                    cart = (Cart) request.getSession().getAttribute("carrello");


                    if (!cart.isPresent(prodotto)) {

                        gestioneAcquistiService.aggiungiProdottoAlCarrello(account.getId(), idProdotto);
                        cart = gestioneAcquistiService.getCart(account);
                        request.getSession(false).setAttribute("carrello", cart);
                        request.getSession(false).setAttribute("totale", Math.round(cart.totalPrice() * 100.0) / 100.0);
                        request.getSession(false).setAttribute("quantity", cart.getCartItems().size());
                        response.sendRedirect("/GameOverNew_war_exploded/GestioneAcquistiController/showCart");
                    } else {
                        response.sendRedirect("/GameOverNew_war_exploded/GestioneProdottoController/showProductUtente?id=" + prodotto.getId());
                    }
                } else {
                    /*pagina di errore*/
                }
                break;

            /**visualizza il carrello**/
            case "/showCart":
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/carrello.jsp");
                dispatcher.forward(request, response);
                break;
            case "/showAcquisto":
                cart = (Cart) request.getSession(false).getAttribute("carrello");
                int quantity = (int) request.getSession(false).getAttribute("quantity");
                if (quantity == 0) {
                    boolean carrellovuoto = true;
                    request.setAttribute("carrelloVuoto", carrellovuoto);

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/carrello.jsp");
                    dispatcher.forward(request, response);

                }
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/pagamento.jsp");
                dispatcher.forward(request, response);
                break;

            /**si visualizzano tutti gli ordini(ADMIN)**/
            case "/showOrders":
                orders = gestioneAcquistiService.getAllOrdiniConAccount();
                request.setAttribute("orders", orders);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/allOrdersAdmin.jsp");
                dispatcher.forward(request, response);
                break;

            /**si visualizzano tutti gli ordini(UTENTE)**/
            case "/showOrdersUtent":
                account = (Account) request.getSession(false).getAttribute("account");
                orders = gestioneAcquistiService.getAllOrdiniDiUnAccount(account.getId());
                if (orders.size() == 0) {
                    boolean ordini = false;
                    request.setAttribute("ordini", ordini);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/utente.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("orders", orders);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/allOrdersUtent.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**si visualizza il singolo ordine(UTENTE,ADMIN)**/
            case "/showOrder":
                account = (Account) request.getSession(false).getAttribute("account");
                order = gestioneAcquistiService.getOrdineConProdotti(Integer.parseInt(request.getParameter("id")));
                double totale = 0;
                for (int i = 0; i < order.getProducts().size(); i++) {
                    totale += order.getProducts().get(i).getPrice();
                }
                request.setAttribute("order", order);
                request.setAttribute("totale", Math.round(totale * 100.0) / 100.0);
                if (account.isVenditore()) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showOrder.jsp");
                    dispatcher.forward(request, response);
                }
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/orderUtent.jsp");
                dispatcher.forward(request, response);
                break;

            /**si visualizza il singolo ordine(ADMIN)**/
            case "/showOrderAdmin":
                order = gestioneAcquistiService.getOrdineConProdotti(Integer.parseInt(request.getParameter("id")));
                double total = 0;
                for (int i = 0; i < order.getProducts().size(); i++) {
                    total += order.getProducts().get(i).getPrice();
                }
                request.setAttribute("orderAdmin", order);
                request.setAttribute("totale", Math.round(total * 100.0) / 100.0);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showOrder.jsp");
                dispatcher.forward(request, response);
                break;

            /**vengono visualizzati gli ordini in base a data e user del cliente(ADMIN con Ajax)**/
            case "/showOrdersWithAjax":

                String valore = request.getParameter("valore");
                orders = gestioneAcquistiService.getAllOrdiniConAccount();

                switch (valore) {

                    case "data crescente":
                        Collections.sort(orders, new OrdinareDataCrescente());
                        String json = new Gson().toJson(orders);
                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println(json);
                        break;

                    case "data decrescente":
                        Collections.sort(orders, new OrdinareDataDecrescente());
                        String json1 = new Gson().toJson(orders);
                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println(json1);
                        break;

                    case "cliente":
                        Collections.sort(orders, new OrdinareCliente());
                        String json2 = new Gson().toJson(orders);
                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println(json2);
                        break;
                }
                break;

            default:  response.sendError(HttpServletResponse.SC_NOT_FOUND);

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {

            case "/":
                break;
            /**si crea l'ordine**/
            case "/createOrder":
                cart = (Cart) request.getSession(false).getAttribute("carrello");
                account = (Account) request.getSession(false).getAttribute("account");
                int prodotti = (int) request.getSession(false).getAttribute("quantity");

                LocalDate data = LocalDate.now();
                order = new Order();
                order.setDate(data);
                order.setNum_product(prodotti);
                order.setAccount(account);
                order.setCart(cart);

                boolean successo = gestioneAcquistiService.creaOrdine(order);
                listaDesideri = listaDesideriService.getListaDesideri(account);
                ArrayList<CartItem> prodottoArrayList = cart.getCartItems();
                for (int i = 0; i < prodottoArrayList.size(); i++) {
                    for (int j = 0; j < listaDesideri.getProdotti().size(); j++) {
                        if (listaDesideri.getProdotti().get(j).getId() == (prodottoArrayList.get(i).getItem().getId())) {
                            listaDesideriService.eliminaProdottoListaDesideri(listaDesideri.getProdotti().get(j), account);
                        }
                    }
                }

                RandomString randomString=new RandomString();
                ArrayList<String> codiciSeriali=new ArrayList<>();
                for(int i=0;i<prodottoArrayList.size();i++){
                    codiciSeriali.add(randomString.nextString());
                }

                gestioneAcquistiService.rimuoviAllProdottiDalCarrello(account.getId());
                cart = gestioneAcquistiService.getCart(account);
                request.getSession(false).setAttribute("carrello", cart);
                request.getSession(false).setAttribute("totale", Math.round(cart.totalPrice() * 100.0) / 100.0);
                request.getSession(false).setAttribute("quantity", cart.getCartItems().size());
                request.setAttribute("successo", successo);
                request.setAttribute("codiciSeriali",codiciSeriali);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/utente.jsp");
                dispatcher.forward(request, response);

                break;
            default:  response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

