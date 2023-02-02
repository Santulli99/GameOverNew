package gestioneAcquisti.controller;

import gestioneAcquisti.service.GestioneAcquistiService;
import gestioneAcquisti.service.GestioneAcquistiServiceImp;
import gestioneProdotto.service.GestioneProdottoServiceImp;
import model.entity.Account;
import model.entity.Prodotto;
import model.entity.cart.Cart;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "GestioneAcquistiController", value = "/GestioneAcquistiController/*")
public class GestioneAcquistiController extends HttpServlet {

    private GestioneProdottoServiceImp gestioneProdottoServiceImp = new GestioneProdottoServiceImp();
    private GestioneAcquistiServiceImp gestioneAcquistiServiceImp = new GestioneAcquistiServiceImp();
    private Prodotto prodotto;
    private Account account;
    private Cart cart;
    private int idProdotto;

    private RequestDispatcher dispatcher;

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
                        cart=gestioneAcquistiServiceImp.getCart(account);
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
                dispatcher= request.getRequestDispatcher("/WEB-INF/views/client/carrello.jsp");
                dispatcher.forward(request,response);
                break;
            case "/showAcquisto":

                dispatcher= request.getRequestDispatcher("/WEB-INF/views/client/acquisto.jsp");
                dispatcher.forward(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {

            case "/":
                break;

        }
    }
}
