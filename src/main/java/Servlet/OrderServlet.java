package Servlet;

import com.google.gson.Gson;
import model.dao.order.SqlOrderDao;
import model.dao.product.SqlProductDao;
import model.entity.Account;
import model.entity.Order;
import model.entity.Product;
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
import java.util.Collections;

@WebServlet(name = "OrderServlet", value = "/OrderServlet/*")
public class OrderServlet extends HttpServlet {
    SqlOrderDao orderDao = new SqlOrderDao();
    ArrayList<Order> orders = new ArrayList<>();
    Account account = new Account();
    Order order = new Order();
    RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {

            case "/":
                break;

            /**vengono visualizzati gli ordini in base a data e user del cliente(ADMIN con Ajax)**/
            case "/showOrdersWithAjax":

                String valore =request.getParameter("valore");
                try {

                    orders = orderDao.searchAllOrdersWithAccount();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

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


            /**si crea l'ordine**/
            case "/createOrder":

                Cart cart= (Cart) request.getSession(false).getAttribute("carrello");

                if(cart==null){
                    boolean carrellovuoto=true;
                    request.setAttribute("carrelloVuoto",carrellovuoto);

                    dispatcher= request.getRequestDispatcher("/WEB-INF/views/client/carrello.jsp");
                    dispatcher.forward(request,response);

                }else {

                    LocalDate data = LocalDate.now();
                    int prodotti = cart.getCartItems().size();
                    Account account1 = (Account) request.getSession(false).getAttribute("account");
                    order.setDate(data);
                    order.setNum_product(prodotti);
                    order.setAccount(account1);
                    order.setCart(cart);
                    try {
                        orderDao.createOrder(order);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    cart.getCartItems().clear();

                    request.getSession(false).setAttribute("carrello", cart);
                    request.getSession(false).setAttribute("totale", Math.round(cart.totalPrice()*100.0)/100.0);
                    request.getSession(false).setAttribute("quantity", cart.getCartItems().size());

                    boolean successo = true;
                    request.setAttribute("successo", successo);

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/utente.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**si visualizzano tutti gli ordini(ADMIN)**/
            case "/showOrders":

                try {
                    orders=orderDao.searchAllOrdersWithAccount();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                request.setAttribute("orders",orders);


                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/allOrdersAdmin.jsp");
                dispatcher.forward(request, response);
                break;

            /**si visualizzano tutti gli ordini(UTENTE)**/
            case "/showOrdersUtent":
                Account account= (Account) request.getSession(false).getAttribute("account");
                int id=account.getId();
                try {
                    orders=orderDao.searchAllOrdersByAccount(id);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                if(orders.size()==0){
                    boolean ordini=false;
                    request.setAttribute("ordini",ordini);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/utente.jsp");
                    dispatcher.forward(request, response);
                }
                else {
                    request.setAttribute("orders", orders);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/allOrdersUtent.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**si visualizza il singolo ordine(UTENTE)**/
            case "/showOrderUtent":
               /* model.dao.account= (Account) request.getSession(false).getAttribute("model.dao.account");
                int id1=model.dao.account.getId();*/
                try {
                    order=orderDao.searchOrderWithProducts(Integer.parseInt(request.getParameter("id")));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                double totale=0;
                for(int i=0;i<order.getProducts().size();i++) {
                    SqlProductDao sqlProductDao=new SqlProductDao();
                    Product product=null;
                    try {
                        product=sqlProductDao.searchProductWithPlatformsAndCategory(order.getProducts().get(i).getId());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    order.getProducts().get(i).setPlatform(product.getPlatform());
                    order.getProducts().get(i).setCategory(product.getCategory());
                    totale += order.getProducts().get(i).getPrice();
                }


                request.setAttribute("order",order);
                request.setAttribute("totale",Math.round(totale*100.0)/100.0);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/orderUtent.jsp");
                dispatcher.forward(request, response);

                break;

            /**si visualizza il singolo ordine(ADMIN)**/
            case "/showOrderAdmin":
                try {
                    order=orderDao.searchOrderWithProducts(Integer.parseInt(request.getParameter("id")));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                double total=0;
                for(int i=0;i<order.getProducts().size();i++) {
                    SqlProductDao sqlProductDao=new SqlProductDao();
                    Product product=null;
                    try {
                        product=sqlProductDao.searchProductWithPlatformsAndCategory(order.getProducts().get(i).getId());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    order.getProducts().get(i).setPlatform(product.getPlatform());
                    order.getProducts().get(i).setCategory(product.getCategory());
                    total += order.getProducts().get(i).getPrice();
                }
                request.setAttribute("orderAdmin",order);
                request.setAttribute("totale",Math.round(total*100.0)/100.0);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showOrder.jsp");
                dispatcher.forward(request, response);
                break;

                /**si rimuove l'ordine da parte dell'utente**/
                case "/removeOrderUtent":
                    Order ordine=null;
                    boolean elimina;
                    try {
                         ordine=orderDao.searchOrderId(Integer.parseInt(request.getParameter("id")));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    LocalDate dataOggi=LocalDate.now();
                    LocalDate dataOrdine=ordine.getDate();
                    LocalDate dataReso=dataOrdine.plusDays(15);

                    if(dataOggi.isBefore(dataReso)){
                        try {
                        orderDao.deleteOrder(ordine);
                        } catch (SQLException throwables) {
                        throwables.printStackTrace();
                        }
                            elimina=true;

                            Account account2= (Account) request.getSession(false).getAttribute("account");
                            int id1=account2.getId();
                            try {
                                orders=orderDao.searchAllOrdersByAccount(id1);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                            request.setAttribute("orders",orders);
                            request.setAttribute("elimina",elimina);

                            dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/allOrdersUtent.jsp");
                            dispatcher.forward(request, response);

                    }

                    else {
                            elimina=false;
                            Account account2= (Account) request.getSession(false).getAttribute("account");
                            int id1=account2.getId();
                            try {
                                orders=orderDao.searchAllOrdersByAccount(id1);
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }

                            request.setAttribute("elimina",elimina);
                            request.setAttribute("orders",orders);

                            dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/allOrdersUtent.jsp");
                            dispatcher.forward(request, response);
                    }
                break;

            default:
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";
        switch (path) {

            case "/":
                break;

            case "/registrazione":

                dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/registrazione1.jsp");
                dispatcher.forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }

    }
}
