package listaDesideri.controller;

import gestioneProdotto.service.GestioneProdottoServiceImp;
import listaDesideri.service.ListaDesideriServiceImp;
import model.dao.account.SqlAccountDao;
import model.dao.product.SqlProductDao;
import model.entity.Account;
import model.entity.ListaDesideri;
import model.entity.Prodotto;
import model.entity.Review;
import recensione.service.RecensioneServiceImp;

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
    private SqlProductDao sqlProdottoDao = new SqlProductDao();
    private ListaDesideriServiceImp listaDesideriServiceImp = new ListaDesideriServiceImp();
    private GestioneProdottoServiceImp gestioneProdottoServiceImp = new GestioneProdottoServiceImp();
    private RecensioneServiceImp recensioneServiceImp=new RecensioneServiceImp();
    ListaDesideri listaDesideri = new ListaDesideri();
    private Prodotto prodotto = new Prodotto();
    private Account account = new Account();
    private ArrayList<Review> reviews=new ArrayList<>();
    private boolean successo = false;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path) {
            case "/":
                break;

        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";


        switch (path) {
            case "/":
                break;
            case "/aggiungiListaDesideri":
                account = (Account) request.getSession(false).getAttribute("account");
                prodotto = gestioneProdottoServiceImp.getProdottoPerId(Integer.parseInt(request.getParameter("id")));

                if (listaDesideriServiceImp.aggiungiProdottoListaDesideri(prodotto, account)) {
                    successo = true;
                }

                dispatcher = request.getRequestDispatcher("/GestioneProdottoController/showProductUtente");
                dispatcher.forward(request, response);
                break;

            case "/visualizzaListaDesideri":
                account = (Account) request.getSession(false).getAttribute("account");
                listaDesideri = listaDesideriServiceImp.getListaDesideri(account);
                ArrayList<Prodotto> prodotti=listaDesideri.getProdotti();
                for(int i=0;i<prodotti.size();i++){
                    reviews=recensioneServiceImp.cercaRecensioniPerProdotto(prodotti.get(i));
                    double valutazioneTotale=0;
                    for(int j=0;j<reviews.size();j++){
                        valutazioneTotale+=reviews.get(j).getValutazione();
                    }
                    double valutazioneMedia=valutazioneTotale/reviews.size();
                    prodotti.get(i).setValutazioneMedia(valutazioneMedia);
                    gestioneProdottoServiceImp.modificaValutazioneMediaProdotto(prodotti.get(i));
                }

                listaDesideri.setProducts(prodotti);
                request.setAttribute("lista", listaDesideri);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/listaDesideri.jsp");
                dispatcher.forward(request, response);
                break;

            case "/rimuoviListaDesideri":
                account = (Account) request.getSession(false).getAttribute("account");
                prodotto = gestioneProdottoServiceImp.getProdottoPerId(Integer.parseInt(request.getParameter("id")));

                if (listaDesideriServiceImp.eliminaProdottoListaDesideri(prodotto, account))
                    successo = true;

                request.setAttribute("successo", successo);
                listaDesideri = listaDesideriServiceImp.getListaDesideri(account);
                request.setAttribute("lista", listaDesideri);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/listaDesideri.jsp");
                dispatcher.forward(request, response);
                break;

        }
    }
}
