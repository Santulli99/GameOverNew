package listaDesideri.controller;

import listaDesideri.service.ListaDesideriServiceImp;
import model.dao.account.SqlAccountDao;
import model.dao.product.SqlProductDao;
import model.entity.Account;
import model.entity.ListaDesideri;
import model.entity.Prodotto;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ListaDesideriController", value = "/ListaDesideriController/*")
public class ListaDesideriController extends HttpServlet {
    private RequestDispatcher dispatcher;
    private SqlProductDao sqlProdottoDao=new SqlProductDao();
    private ListaDesideriServiceImp listaDesideriServiceImp=new ListaDesideriServiceImp();
    ListaDesideri listaDesideri=new ListaDesideri();
   private Prodotto prodotto=new Prodotto();
   private Account account=new Account();
   private boolean successo=false;
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";

        switch (path){
            case "/":
                break;

        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";


        switch (path){
            case "/":
                break;
            case "/aggiungiListaDesideri":
                account=(Account) request.getSession(false).getAttribute("account");
                try {
                    prodotto =sqlProdottoDao.searchProduct(Integer.parseInt(request.getParameter("id"))); /*implementare con servizio*/
                    if(listaDesideriServiceImp.aggiungiProdottoListaDesideri(prodotto,account)) {
                        successo = true;
                    }
                    request.setAttribute("successo", successo);
                    request.setAttribute("prodotto", prodotto);
                    //dispatcher =request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                    dispatcher =request.getRequestDispatcher("/ProductServlet/showProductUtente");
                    dispatcher.forward(request,response);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "/visualizzaListaDesideri":

                account=(Account) request.getSession(false).getAttribute("account");
                listaDesideri=listaDesideriServiceImp.getListaDesideri(account);
                request.setAttribute("lista",listaDesideri);
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/user/listaDesideri.jsp");
                dispatcher.forward(request,response);
                break;

            case "/rimuoviListaDesideri":
                account=(Account) request.getSession(false).getAttribute("account");
                try {
                    System.out.println(Integer.parseInt(request.getParameter("id")));
                    prodotto =sqlProdottoDao.searchProduct(Integer.parseInt(request.getParameter("id"))); /*implementare con servizio*/
                    if(listaDesideriServiceImp.eliminaProdottoListaDesideri(prodotto,account)) {
                        successo = true;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                request.setAttribute("successo", successo);
                listaDesideri=listaDesideriServiceImp.getListaDesideri(account);
                request.setAttribute("lista",listaDesideri);
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/user/listaDesideri.jsp");
                dispatcher.forward(request,response);
                break;

            case "/login":
                break;
        }
    }
}
