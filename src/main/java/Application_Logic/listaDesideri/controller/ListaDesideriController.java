package Application_Logic.listaDesideri.controller;

import Application_Logic.gestioneProdotto.service.GestioneProdottoService;
import Application_Logic.gestioneProdotto.service.GestioneProdottoServiceImp;
import Application_Logic.listaDesideri.service.ListaDesideriService;
import Application_Logic.listaDesideri.service.ListaDesideriServiceImp;
import Application_Logic.entity.Account;
import Application_Logic.entity.ListaDesideri;
import Application_Logic.entity.Prodotto;
import Application_Logic.entity.Review;
import Application_Logic.entity.cart.Cart;
import Application_Logic.recensione.service.RecensioneService;
import Application_Logic.recensione.service.RecensioneServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


/**
 * implementa il controller che si occupa  del sottosistema ListaDesideri
 *
 * @author Andrea Santulli
 * @see HttpServlet fornisce l'interfaccia per creare una servlet
 */


@WebServlet(name = "ListaDesideriController", value = "/ListaDesideriController/*")
public class ListaDesideriController extends HttpServlet {
    private RequestDispatcher dispatcher;
    private ListaDesideriService listaDesideriService = new ListaDesideriServiceImp();
    private GestioneProdottoService gestioneProdottoService = new GestioneProdottoServiceImp();
    private RecensioneService recensioneService = new RecensioneServiceImp();
    ListaDesideri listaDesideri = new ListaDesideri();
    private Prodotto prodotto = new Prodotto();
    private Account account = new Account();
    private ArrayList<Review> reviews = new ArrayList<>();
    private Cart cart;
    private boolean successo = false;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path) {
            case "/":
                break;
            default:  response.sendError(HttpServletResponse.SC_NOT_FOUND);

        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";


        switch (path) {
            case "/":
                break;
            case "/aggiungiListaDesideri":
                account = (Account) request.getSession(false).getAttribute("account");
                prodotto = gestioneProdottoService.getProdotto(Integer.parseInt(request.getParameter("id")));

                if (listaDesideriService.aggiungiProdottoListaDesideri(prodotto, account)) {
                    successo = true;
                }

                dispatcher = request.getRequestDispatcher("/GestioneProdottoController/showProductUtente");
                dispatcher.forward(request, response);
                break;

            case "/visualizzaListaDesideri":
                account = (Account) request.getSession(false).getAttribute("account");
                listaDesideri = listaDesideriService.getListaDesideri(account);
                ArrayList<Prodotto> prodotti = listaDesideri.getProdotti();
                ArrayList<Prodotto> prodottiNelCarrello = new ArrayList<>();
                cart = (Cart) request.getSession(false).getAttribute("carrello");
                for (int i = 0; i < prodotti.size(); i++) {
                    if (cart.isPresent(prodotti.get(i))) {
                        prodottiNelCarrello.add(prodotti.get(i));
                    }
                    reviews = recensioneService.cercaRecensioniPerProdotto(prodotti.get(i));
                    if (reviews.size() > 0) {
                        double valutazioneTotale = 0;
                        for (int j = 0; j < reviews.size(); j++) {
                            valutazioneTotale += reviews.get(j).getValutazione();
                        }
                        double valutazioneMedia = valutazioneTotale / reviews.size();
                        prodotti.get(i).setValutazioneMedia(valutazioneMedia);
                        gestioneProdottoService.modificaValutazioneMediaProdotto(prodotti.get(i));
                    }
                }

                cart.isPresent(prodotto);
                listaDesideri.setProducts(prodotti);
                request.setAttribute("prodottiNelCarrello", prodottiNelCarrello);
                request.setAttribute("lista", listaDesideri);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/listaDesideri.jsp");
                dispatcher.forward(request, response);
                break;

            case "/rimuoviListaDesideri":
                account = (Account) request.getSession(false).getAttribute("account");
                prodotto = gestioneProdottoService.getProdotto(Integer.parseInt(request.getParameter("id")));

                if (listaDesideriService.eliminaProdottoListaDesideri(prodotto, account))
                    successo = true;

                request.setAttribute("successo", successo);
                listaDesideri = listaDesideriService.getListaDesideri(account);
                request.setAttribute("lista", listaDesideri);
                dispatcher = request.getRequestDispatcher("/ListaDesideriController/visualizzaListaDesideri");
                dispatcher.forward(request, response);
                break;
            default:  response.sendError(HttpServletResponse.SC_NOT_FOUND);

        }
    }
}
