package Application_Logic.recensione.controller;

import Application_Logic.gestioneProdotto.service.GestioneProdottoService;
import Application_Logic.gestioneProdotto.service.GestioneProdottoServiceImp;
import Application_Logic.listaDesideri.service.ListaDesideriService;
import Application_Logic.listaDesideri.service.ListaDesideriServiceImp;
import Application_Logic.recensione.service.RecensioneService;
import Application_Logic.recensione.service.RecensioneServiceImp;
import Storage.product.SqlProductDao;
import Application_Logic.entity.Account;
import Application_Logic.entity.ListaDesideri;
import Application_Logic.entity.Prodotto;
import Application_Logic.entity.Review;
import Application_Logic.ValidateForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;


/**
 * implementa il controller che si occupa  del sottosistema Recensione
 *
 * @author Andrea Santulli
 * @see HttpServlet fornisce l'interfaccia per creare una servlet
 */

@WebServlet(name = "RecensioneController", value = "/RecensioneController/*")
public class RecensioneController extends HttpServlet {
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private Account account;
    private Prodotto prodotto;
    private Review review;
    private ArrayList<Review> reviews = new ArrayList<>();
    boolean successo = false;
    private GestioneProdottoService gestioneProdottoService = new GestioneProdottoServiceImp();
    private ListaDesideriService listaDesideriService = new ListaDesideriServiceImp();
    private RecensioneService recensioneService = new RecensioneServiceImp();
    private ValidateForm validateForm = new ValidateForm();

    public RecensioneController() {

    }

    public RecensioneController(RecensioneServiceImp recensioneService) {
        this.recensioneService = recensioneService;
    }


    public boolean aggiungiRecensione(String titolo, String descrizione, double valutazione, Account account, Prodotto prodotto) {

        boolean titRecensione = validateForm.validateTitoloReview(titolo);
        boolean desRecensione = validateForm.validateDescrizioneProdotto(descrizione);
        if (titRecensione && desRecensione) {
            review = new Review();
            review.setAccount(account);
            review.setProdotto(prodotto);
            review.setDescrizione(descrizione);
            review.setValutazione(valutazione);
            review.setTitolo(titolo);
            successo = recensioneService.aggiungiRecensione(review);
            return true;
        } else {
            return false;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path) {
            case "/":
                break;
            case "/creaRecensione":
                account = (Account) request.getSession(false).getAttribute("account");
                prodotto = gestioneProdottoService.getProdotto(Integer.parseInt(request.getParameter("id")));
                String titolo = request.getParameter("titoloRecensione");
                String descrizione = request.getParameter("descrizione");
                double valutazione = Double.parseDouble(request.getParameter("valutazione"));

                successo = aggiungiRecensione(titolo, descrizione, valutazione, account, prodotto);
                reviews = recensioneService.cercaRecensioniPerProdotto(prodotto);
                boolean controllo = false;
                for (int i = 0; i < reviews.size(); i++) {
                    if (account.getId() == reviews.get(i).getAccount().getId())
                        controllo = true;
                }
                boolean aggiunto = false;
                ListaDesideri listaDesideri = listaDesideriService.getListaDesideri(account);
                if (listaDesideri.containsListaDesideri(prodotto)) {
                    aggiunto = true;
                }
                boolean presente = true;
                request.setAttribute("aggiunto", aggiunto);
                request.setAttribute("controllo", controllo);
                request.setAttribute("successo", successo);
                request.setAttribute("prodotto", prodotto);
                request.setAttribute("recensioni", reviews);
                request.setAttribute("presente", presente);
                if (successo) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/recensione.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            case "/modificaRecensione":
                account = (Account) request.getSession(false).getAttribute("account");
                prodotto = gestioneProdottoService.getProdotto(Integer.parseInt(request.getParameter("id")));
                String titolo1 = request.getParameter("titoloRecensione");
                String desc = request.getParameter("descrizione");
                double val = Double.parseDouble(request.getParameter("valutazione"));

                review = new Review();
                review.setTitolo(titolo1);
                review.setDescrizione(desc);
                review.setValutazione(val);
                review.setAccount(account);
                review.setProdotto(prodotto);
                boolean modifica = recensioneService.modificaRecensione(review);
                reviews = recensioneService.cercaRecensioniPerProdotto(prodotto);
                boolean controllo1 = false;
                for (int i = 0; i < reviews.size(); i++) {
                    if (account.getId() == reviews.get(i).getAccount().getId())
                        controllo1 = true;
                }
                boolean aggiunto1 = false;
                ListaDesideri listaDesideri1 = listaDesideriService.getListaDesideri(account);
                if (listaDesideri1.containsListaDesideri(prodotto)) {
                    aggiunto1 = true;
                }
                request.setAttribute("presente", true);
                request.setAttribute("aggiunto", aggiunto1);
                request.setAttribute("controllo", controllo1);
                request.setAttribute("recensioni", reviews);
                request.setAttribute("modifica", modifica);
                request.setAttribute("prodotto", prodotto);
                if (modifica) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/recensione.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";


        switch (path) {
            case "/":
                break;
            case "/eliminaRecensione":
                account = (Account) request.getSession(false).getAttribute("account");
                prodotto = gestioneProdottoService.getProdotto(Integer.parseInt(request.getParameter("id")));
                successo = recensioneService.rimuoviRecensione(account, prodotto);
                request.setAttribute("successo", successo);
                request.setAttribute("prodotto", prodotto);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                dispatcher.forward(request, response);

                break;
            case "/scriviRecensione":
                prodotto = gestioneProdottoService.getProdotto(Integer.parseInt(request.getParameter("id")));
                request.setAttribute("prodotto", prodotto);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/recensione.jsp");
                dispatcher.forward(request, response);
                break;
            case "/modificaRecensione":
                account = (Account) request.getSession(false).getAttribute("account");
                int id = Integer.parseInt(request.getParameter("id"));
                prodotto = gestioneProdottoService.getProdotto(id);
                review = new Review();
                review = recensioneService.cercaRecensionePerProdotto(prodotto, account);
                request.setAttribute("recensione", review);
                request.setAttribute("prodotto", prodotto);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateRecensione.jsp");
                dispatcher.forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
