package recensione.controller;

import model.dao.product.SqlProductDao;
import model.entity.Account;
import model.entity.Prodotto;
import recensione.service.RecensioneServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RecensioneController", value = "/RecensioneController/*")
public class RecensioneController extends HttpServlet {
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private Account account;
    private Prodotto prodotto;

    boolean successo=false;
    private SqlProductDao productDao=new SqlProductDao();

    private RecensioneServiceImp recensioneServiceImp=new RecensioneServiceImp();
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";

        switch (path){
            case "/":
                break;
            case "/creaRecensione":
                account=(Account) request.getSession(false).getAttribute("account");
                try {
                    prodotto=productDao.searchProductWithCategory(Integer.parseInt(request.getParameter("id")));/*implementare con servizio*/
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String descrizione = request.getParameter("descrizione");
                double valutazione = Double.parseDouble(request.getParameter("valutazione"));
                successo=recensioneServiceImp.aggiungiRecensione(account,prodotto,descrizione,valutazione);
                request.setAttribute("successo", successo);
                request.setAttribute("prodotto",prodotto);
                if(successo){
                    dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                    dispatcher.forward(request,response);
                }else{
                    dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/recensione.jsp");
                    dispatcher.forward(request,response);
                }
                break;

            case "/modificaRecensione":
                try {
                    prodotto=productDao.searchProductWithCategory(Integer.parseInt(request.getParameter("id")));/*implementare con servizio*/
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String desc=request.getParameter("descrizione");
                double val= Double.parseDouble(request.getParameter("valutazione"));
                successo=recensioneServiceImp.modificaRecensione(desc,val);
                request.setAttribute("successo", successo);
                request.setAttribute("prodotto",prodotto);
                if(successo){
                    dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                    dispatcher.forward(request,response);
                }else{
                    dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/recensione.jsp");
                    dispatcher.forward(request,response);
                }
                break;
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";


        switch (path){
            case "/":
                break;
            case "/eliminaRecensione":
                account=(Account) request.getSession(false).getAttribute("account");
                try {
                    prodotto=productDao.searchProductWithCategory(Integer.parseInt(request.getParameter("id")));/*implementare con servizio*/
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                successo=recensioneServiceImp.rimuoviRecensione(account,prodotto);
                request.setAttribute("successo",successo);
                request.setAttribute("prodotto",prodotto);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                dispatcher.forward(request,response);

                break;
            case "/login":
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                dispatcher.forward(request,response);
                break;
        }
    }
}
