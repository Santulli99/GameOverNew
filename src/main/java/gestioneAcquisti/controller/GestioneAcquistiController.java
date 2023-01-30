package gestioneAcquisti.controller;

import gestioneAcquisti.service.GestioneAcquistiService;
import gestioneAcquisti.service.GestioneAcquistiServiceImp;
import gestioneProdotto.service.GestioneProdottoServiceImp;
import model.dao.category.SqlCategoryDao;
import model.entity.Account;
import model.entity.Category;
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
import java.util.ArrayList;

@WebServlet(name = "CartServlet", value = "/CartServlet/*")
public class GestioneAcquistiController extends HttpServlet {

    private GestioneProdottoServiceImp gestioneProdottoServiceImp = new GestioneProdottoServiceImp();
    private GestioneAcquistiServiceImp gestioneAcquistiServiceImp=new GestioneAcquistiServiceImp();
    private Prodotto prodotto;
    private Account account;
    private Cart cart;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path) {

            case "/":
                break;
            /**si rimuove un prodotto dal carrello**/
            case "/removeCart":
                account = (Account) request.getSession(false).getAttribute("account");
                int idProdotto2 = Integer.parseInt(request.getParameter("id"));
                boolean successo=gestioneAcquistiServiceImp.rimuoviProdottoDalCarrello(account.getId(),idProdotto2);
                cart=gestioneAcquistiServiceImp.getCart(account);

                Cart cart1 = (Cart) request.getSession().getAttribute("carrello");
                cart1.removeProduct(prodotto);
                request.getSession(false).setAttribute("totale", Math.round(cart1.totalPrice() * 100.0) / 100.0);
                request.getSession(false).setAttribute("carrello", cart);
                request.getSession(false).setAttribute("quantity", cart.getCartItems().size());
                response.sendRedirect("/GameOver_war_exploded/CartServlet/showCart");
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
