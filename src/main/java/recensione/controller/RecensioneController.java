package recensione.controller;

import gestioneProdotto.service.GestioneProdottoServiceImp;
import model.dao.product.SqlProductDao;
import model.entity.Account;
import model.entity.Prodotto;
import model.entity.Review;
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
import java.util.ArrayList;

@WebServlet(name = "RecensioneController", value = "/RecensioneController/*")
public class RecensioneController extends HttpServlet {
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private Account account;
    private Prodotto prodotto;
    private Review review;
    private ArrayList<Review> reviews = new ArrayList<>();
    boolean successo=false;
    private SqlProductDao productDao=new SqlProductDao();
    private GestioneProdottoServiceImp gestioneProdottoServiceImp=new GestioneProdottoServiceImp();

    private RecensioneServiceImp recensioneServiceImp=new RecensioneServiceImp();
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";

        switch (path){
            case "/":
                break;
            case "/creaRecensione":
                account=(Account) request.getSession(false).getAttribute("account");
                prodotto=gestioneProdottoServiceImp.getProdottoConCategoria(Integer.parseInt(request.getParameter("id")));
                String titolo=request.getParameter("titoloRecensione");
                String descrizione = request.getParameter("descrizione");
                double valutazione = Double.parseDouble(request.getParameter("valutazione"));
                review=new Review();
                review.setAccount(account);
                review.setProdotto(prodotto);
                review.setDescrizione(descrizione);
                review.setValutazione(valutazione);
                review.setTitolo(titolo);

                successo=recensioneServiceImp.aggiungiRecensione(review);
                reviews = recensioneServiceImp.cercaRecensioniPerProdotto(prodotto);
                boolean controllo=false;
                for(int i =0;i<reviews.size();i++){
                    if(account.getId()==reviews.get(i).getAccount().getId())
                        controllo=true;
                }
                request.setAttribute("controllo",controllo);
                request.setAttribute("successo", successo);
                request.setAttribute("prodotto",prodotto);
                request.setAttribute("recensioni", reviews);
                if(successo){
                    dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                    dispatcher.forward(request,response);
                }else{
                    dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/recensione.jsp");
                    dispatcher.forward(request,response);
                }
                break;

            case "/modificaRecensione":
                account=(Account) request.getSession(false).getAttribute("account");
                prodotto=gestioneProdottoServiceImp.getProdottoConCategoria(Integer.parseInt(request.getParameter("id")));
                String titolo1=request.getParameter("titoloRecensione");
                String desc=request.getParameter("descrizione");
                double val= Double.parseDouble(request.getParameter("valutazione"));

                review=new Review();
                review.setTitolo(titolo1);
                review.setDescrizione(desc);
                review.setValutazione(val);
                review.setAccount(account);
                review.setProdotto(prodotto);
                successo=recensioneServiceImp.modificaRecensione(review);
                reviews = recensioneServiceImp.cercaRecensioniPerProdotto(prodotto);
                boolean controllo1=false;
                for(int i =0;i<reviews.size();i++){
                    if(account.getId()==reviews.get(i).getAccount().getId())
                        controllo1=true;
                }
                request.setAttribute("controllo",controllo1);
                request.setAttribute("recensioni", reviews);
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
                prodotto=gestioneProdottoServiceImp.getProdottoConCategoria(Integer.parseInt(request.getParameter("id")));
                successo=recensioneServiceImp.rimuoviRecensione(account,prodotto);
                request.setAttribute("successo",successo);
                request.setAttribute("prodotto",prodotto);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                dispatcher.forward(request,response);

                break;
            case "/scriviRecensione":
                prodotto=gestioneProdottoServiceImp.getProdottoConCategoria(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("prodotto",prodotto);
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/user/recensione.jsp");
                dispatcher.forward(request,response);
                break;
            case "/modificaRecensione":
                account=(Account) request.getSession(false).getAttribute("account");
                int id=Integer.parseInt(request.getParameter("id"));
                prodotto = gestioneProdottoServiceImp.getProdottoPerId(id);
                review=new Review();
                review=recensioneServiceImp.cercaRecensionePerProdotto(prodotto,account);
                request.setAttribute("recensione",review);
                request.setAttribute("prodotto",prodotto);
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/user/updateRecensione.jsp");
                dispatcher.forward(request,response);
                break;
        }
    }
}
