package Servlet;

import model.dao.product.SqlProductDao;
import model.entity.Product;
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

@WebServlet(name = "CartServlet", value = "/CartServlet/*")
public class CartServlet extends HttpServlet {
    private Product product =new Product();
    private RequestDispatcher dispatcher;
    private SqlProductDao sqlProductDao=new SqlProductDao();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        HttpSession session;

        switch (path) {

            case "/":
                break;
            /**si rimuove un prodotto dal carrello**/
            case "/removeCart":

                int idProdotto2=Integer.parseInt(request.getParameter("id"));
                try {
                    product=sqlProductDao.searchProduct(idProdotto2);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                Cart cart1= (Cart) request.getSession().getAttribute("carrello");
                cart1.removeProduct(product);
                request.getSession(false).setAttribute("totale",Math.round(cart1.totalPrice()*100.0)/100.0);
                request.getSession(false).setAttribute("carrello",cart1);
                request.getSession(false).setAttribute("quantity",cart1.getCartItems().size());
                response.sendRedirect("/GameOver_war_exploded/CartServlet/showCart");
                break;

            /**aggiungere prodotto al carrello per un utente non registrato(che lo porta alla pagina di login o registrazione)**/
            case "/addCartGhost":
                try {
                    int idProdotto=Integer.parseInt(request.getParameter("id"));
                    product=sqlProductDao.searchProduct(idProdotto);

                    if(product!=null){

                        if(request.getSession(false).getAttribute("carrello")==null){

                            request.getSession(false).setAttribute("carrello",new Cart());
                        }

                        Cart cart= (Cart) request.getSession().getAttribute("carrello");


                            cart.addProduct(product);
                            request.getSession(false).setAttribute("carrello",cart);
                            double totale=cart.totalPrice();
                            request.getSession(false).setAttribute("totale",Math.round(totale*100.0)/100.0);
                            request.getSession(false).setAttribute("quantity",cart.getCartItems().size());
                            boolean acquistoFailed=true;
                            request.setAttribute("acquistoFialed",acquistoFailed);
                            dispatcher= request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                            dispatcher.forward(request,response);

                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            /**aggiunta di un prodotto nel carrello dell'utente**/
            case "/addCart":
                try {
                    int idProdotto=Integer.parseInt(request.getParameter("id"));
                    product=sqlProductDao.searchProductWithPlatformsAndCategory(idProdotto);

                    if(product!=null){

                        if(request.getSession(false).getAttribute("carrello")==null){

                            request.getSession(false).setAttribute("carrello",new Cart());
                        }

                        Cart cart= (Cart) request.getSession().getAttribute("carrello");


                        if(!cart.isPresent(product)){

                            cart.addProduct(product);
                            request.getSession(false).setAttribute("carrello",cart);
                            request.getSession(false).setAttribute("totale",Math.round(cart.totalPrice()*100.0)/100.0);
                            request.getSession(false).setAttribute("quantity",cart.getCartItems().size());
                            response.sendRedirect("/GameOver_war_exploded/CartServlet/showCart");
                        }
                        else{
                            request.getSession(false).setAttribute("prodotto2",product);
                            response.sendRedirect("/GameOver_war_exploded/ProductServlet/showProductUtente?id="+product.getId());
                        }


                    }

                    else{
                        /*pagina di errore*/
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;
            case "/deleteCart":

                break;

            case "/buyProducts":

                break;

            /**visualizza il carrello**/
            case "/showCart":


                dispatcher= request.getRequestDispatcher("/WEB-INF/views/client/carrello.jsp");
                dispatcher.forward(request,response);
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        RequestDispatcher dispatcher;

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path) {

            case "/":
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
