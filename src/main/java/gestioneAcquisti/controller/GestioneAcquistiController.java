package gestioneAcquisti.controller;

import gestioneAcquisti.OrdinareCliente;
import gestioneAcquisti.OrdinareDataCrescente;
import gestioneAcquisti.OrdinareDataDecrescente;
import com.google.gson.Gson;
import gestioneAcquisti.RandomString;
import gestioneAcquisti.service.GestioneAcquistiServiceImp;
import gestioneProdotto.service.GestioneProdottoServiceImp;
import listaDesideri.service.ListaDesideriServiceImp;
import model.entity.Account;
import model.entity.ListaDesideri;
import model.entity.Order;
import model.entity.Prodotto;
import model.entity.cart.Cart;
import model.entity.cart.CartItem;


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

    private GestioneProdottoServiceImp gestioneProdottoServiceImp = new GestioneProdottoServiceImp();
    private GestioneAcquistiServiceImp gestioneAcquistiServiceImp = new GestioneAcquistiServiceImp();

    private ListaDesideri listaDesideri = new ListaDesideri();
    private ListaDesideriServiceImp listaDesideriServiceImp = new ListaDesideriServiceImp();
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
                gestioneAcquistiServiceImp.rimuoviProdottoDalCarrello(account.getId(), idProdotto);
                cart = gestioneAcquistiServiceImp.getCart(account);

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
                prodotto = gestioneProdottoServiceImp.getProdotto(idProdotto);
                if (prodotto != null) {

                    if (request.getSession(false).getAttribute("carrello") == null) {
                        request.getSession(false).setAttribute("carrello", new Cart());
                    }

                    cart = (Cart) request.getSession().getAttribute("carrello");


                    if (!cart.isPresent(prodotto)) {

                        gestioneAcquistiServiceImp.aggiungiProdottoAlCarrello(account.getId(), idProdotto);
                        cart = gestioneAcquistiServiceImp.getCart(account);
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
                int quantità = (int) request.getSession(false).getAttribute("quantity");
                if (quantità == 0) {
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
                orders = gestioneAcquistiServiceImp.getAllOrdiniConAccount();
                request.setAttribute("orders", orders);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/allOrdersAdmin.jsp");
                dispatcher.forward(request, response);
                break;

            /**si visualizzano tutti gli ordini(UTENTE)**/
            case "/showOrdersUtent":
                account = (Account) request.getSession(false).getAttribute("account");
                orders = gestioneAcquistiServiceImp.getAllOrdiniDiUnAccount(account.getId());
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
                order = gestioneAcquistiServiceImp.getOrdineConProdotti(Integer.parseInt(request.getParameter("id")));
                double totale = 0;
                for (int i = 0; i < order.getProducts().size(); i++) {
                    prodotto = gestioneProdottoServiceImp.getProdotto(order.getProducts().get(i).getId());
                    order.getProducts().get(i).setPlatform(prodotto.getPlatform());
                    order.getProducts().get(i).setCategory(prodotto.getCategory());
                    totale += order.getProducts().get(i).getPrice();
                }
                request.setAttribute("order", order);
                request.setAttribute("totale", Math.round(totale * 100.0) / 100.0);
                if (account.isAdmin()) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showOrder.jsp");
                    dispatcher.forward(request, response);
                }
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/orderUtent.jsp");
                dispatcher.forward(request, response);
                break;

            /**si visualizza il singolo ordine(ADMIN)**/
            case "/showOrderAdmin":
                order = gestioneAcquistiServiceImp.getOrdineConProdotti(Integer.parseInt(request.getParameter("id")));
                double total = 0;
                for (int i = 0; i < order.getProducts().size(); i++) {
                    prodotto = gestioneProdottoServiceImp.getProdotto(order.getProducts().get(i).getId());
                    order.getProducts().get(i).setPlatform(prodotto.getPlatform());
                    order.getProducts().get(i).setCategory(prodotto.getCategory());
                    total += order.getProducts().get(i).getPrice();
                }
                request.setAttribute("orderAdmin", order);
                request.setAttribute("totale", Math.round(total * 100.0) / 100.0);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showOrder.jsp");
                dispatcher.forward(request, response);
                break;

            /**si rimuove l'ordine da parte dell'utente**/
            case "/removeOrderUtent":
                account = (Account) request.getSession(false).getAttribute("account");
                boolean elimina = false;
                order = gestioneAcquistiServiceImp.getOrdine(Integer.parseInt(request.getParameter("id")));

                LocalDate dataOggi = LocalDate.now();
                LocalDate dataOrdine = order.getDate();
                LocalDate dataReso = dataOrdine.plusDays(15);
                if (dataOggi.isBefore(dataReso)) {
                    elimina = gestioneAcquistiServiceImp.rimuoviOrdine(order);
                }

                orders = gestioneAcquistiServiceImp.getAllOrdiniDiUnAccount(account.getId());
                request.setAttribute("orders", orders);
                request.setAttribute("elimina", elimina);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/allOrdersUtent.jsp");
                dispatcher.forward(request, response);
                break;
            /**vengono visualizzati gli ordini in base a data e user del cliente(ADMIN con Ajax)**/
            case "/showOrdersWithAjax":

                String valore = request.getParameter("valore");
                orders = gestioneAcquistiServiceImp.getAllOrdiniConAccount();

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

                LocalDate data = LocalDate.now().plusDays(1);
                order = new Order();
                order.setDate(data);
                order.setNum_product(prodotti);
                order.setAccount(account);
                order.setCart(cart);

                boolean successo = gestioneAcquistiServiceImp.creaOrdine(order);
                listaDesideri = listaDesideriServiceImp.getListaDesideri(account);
                ArrayList<CartItem> prodottoArrayList = cart.getCartItems();
                for (int i = 0; i < prodottoArrayList.size(); i++) {
                    for (int j = 0; j < listaDesideri.getProdotti().size(); j++) {
                        if (listaDesideri.getProdotti().get(j).getId() == (prodottoArrayList.get(i).getItem().getId())) {
                            listaDesideriServiceImp.eliminaProdottoListaDesideri(listaDesideri.getProdotti().get(j), account);
                        }
                    }
                }

                RandomString randomString=new RandomString();
                ArrayList<String> codiciSeriali=new ArrayList<>();
                for(int i=0;i<prodottoArrayList.size();i++){
                    codiciSeriali.add(randomString.nextString());
                }

                gestioneAcquistiServiceImp.rimuoviAllProdottiDalCarrello(account.getId());
                cart = gestioneAcquistiServiceImp.getCart(account);
                request.getSession(false).setAttribute("carrello", cart);
                request.getSession(false).setAttribute("totale", Math.round(cart.totalPrice() * 100.0) / 100.0);
                request.getSession(false).setAttribute("quantity", cart.getCartItems().size());
                request.setAttribute("successo", successo);
                request.setAttribute("codiciSeriali",codiciSeriali);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/utente.jsp");
                dispatcher.forward(request, response);

                break;

        }
    }
}

