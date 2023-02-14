package gestioneProdotto.controller;

import com.google.gson.Gson;
import gestioneAcquisti.service.GestioneAcquistiServiceImp;
import gestioneProdotto.service.GestioneProdottoServiceImp;
import listaDesideri.service.ListaDesideriServiceImp;
import model.entity.*;
import recensione.service.RecensioneServiceImp;
import validate.ValidateForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Implementa il controller che si occupa  del sottosistema GestioneProdotto
 *
 * @author Gerardo Esposito
 * @see HttpServlet fornisce l'interfaccia per creare una servlet
 */

@WebServlet(name = "GestioneProdottoController", value = "/GestioneProdottoController/*")
@MultipartConfig
public class GestioneProdottoController extends HttpServlet {

    private String path;
    private Prodotto prodotto = new Prodotto();
    private RequestDispatcher dispatcher;
    private ArrayList<Prodotto> prodotti = new ArrayList<>();
    private int id;
    private Account account = new Account();
    private boolean success;
    private boolean eliminato;
    private String stringa;
    private Pattern pattern;
    private String categoria;
    private String piattaforma;
    private ArrayList<Prodotto> prodottoSearch;
    private ArrayList<Review> reviews = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    private GestioneAcquistiServiceImp gestioneAcquistiServiceImp = new GestioneAcquistiServiceImp();
    private GestioneProdottoServiceImp gestioneProdottoServiceImp = new GestioneProdottoServiceImp();
    private ListaDesideriServiceImp listaDesideriServiceImp = new ListaDesideriServiceImp();
    private RecensioneServiceImp recensioneServiceImp = new RecensioneServiceImp();

    public GestioneProdottoController(){

    }
    public GestioneProdottoController(GestioneProdottoServiceImp mockGestioneProdotto) {
        this.gestioneProdottoServiceImp=mockGestioneProdotto;
    }


    public boolean aggiungiProdotto(String nome, String prezzo, String descrizione, String cover, LocalDate data, String categoria, String piattaforma) {

        ValidateForm validateForm = new ValidateForm();
        boolean nome1 = validateForm.validateNomeProdotto(nome);
        boolean prezzo1 = validateForm.validatePrezzoProdotto(prezzo);
        boolean descrizione1 = validateForm.validateDescrizioneProdotto(descrizione);
        boolean dataUscita = validateForm.validateDataUscitaProdotto(data);


        if (nome1 && prezzo1 && dataUscita && descrizione1) {
            prodotto = new Prodotto();
            prodotto.setProductName(nome);
            prodotto.setCategoryName(categoria);
            prodotto.setPlatformName(piattaforma);
            prodotto.setDate(data);
            prodotto.setDescription(descrizione);
            prodotto.setPrice(Double.parseDouble(prezzo));
            prodotto.setValutazioneMedia(0);
            prodotto.setCover(cover);

            gestioneProdottoServiceImp.aggiungiProdotto(prodotto);
            return true;
        } else {
            return false;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/":
                break;
            /**si visualizza la pagina per modificare il prodotto**/
            case "/updateProduct":
                id = Integer.parseInt(request.getParameter("id"));
                prodotto = gestioneProdottoServiceImp.getProdotto(id);
                request.setAttribute("prodotto", prodotto);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateProduct.jsp");
                dispatcher.forward(request, response);
                break;

            /**si visualizzano tutti i prodotti**/
            case "/showAllProduct":
                //prodotti = gestioneProdottoServiceImp.getAllProdottiConCategoriaEPiattaforma();
                prodotti = gestioneProdottoServiceImp.getAllProdotti();
                request.setAttribute("prodotti", prodotti);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showAllProduct.jsp");
                dispatcher.forward(request, response);
                break;

            /**ricerca prodotti con ajax(Utente)**/
            case "/searchProductUtent":
                prodottoSearch = new ArrayList<>();
                stringa = request.getParameter("stringa");
                pattern = Pattern.compile(stringa, Pattern.CASE_INSENSITIVE);
                prodotti = gestioneProdottoServiceImp.getAllProdotti();

                for (int i = 0; i < prodotti.size(); i++) {
                    Matcher matcher = pattern.matcher(prodotti.get(i).getProductName());
                    if (matcher.find())
                        prodottoSearch.add(prodotti.get(i));
                }

                if (prodottoSearch.size() == 0) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/searchProductFailed.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("vetrina", prodottoSearch);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/searchProduct.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**ricerca prodotti con ajax(Guest)**/
            case "/searchProductGuest":
                prodottoSearch = new ArrayList<>();
                stringa = request.getParameter("stringa");
                pattern = Pattern.compile(stringa, Pattern.CASE_INSENSITIVE);
                prodotti = gestioneProdottoServiceImp.getAllProdotti();
                for (int i = 0; i < prodotti.size(); i++) {
                    Matcher matcher = pattern.matcher(prodotti.get(i).getProductName());
                    if (matcher.find())
                        prodottoSearch.add(prodotti.get(i));
                }
                System.out.println("SIZE:" + prodottoSearch.size());
                if (prodottoSearch.size() == 0) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/searchProductGuestFailed.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("vetrina", prodottoSearch);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/searchProductGuest.jsp");
                    dispatcher.forward(request, response);
                }
                break;
            /**ricerca prodotti con ajax(ADMIN,UTENTE,GUEST)**/
            case "/searchProductWithAjax":
                prodottoSearch = new ArrayList<>();
                stringa = request.getParameter("stringa");
                pattern = Pattern.compile(stringa, Pattern.CASE_INSENSITIVE);
                prodotti = gestioneProdottoServiceImp.getAllProdotti();

                for (int i = 0; i < prodotti.size(); i++) {
                    Matcher matcher = pattern.matcher(prodotti.get(i).getProductName());
                    if (matcher.find())
                        prodottoSearch.add(prodotti.get(i));
                }
                String json = new Gson().toJson(prodottoSearch);
                response.setContentType("text/plain;charset=UTF-8");
                response.getWriter().println(json);
                break;

            /**si vusualizza il prodotto con descrizione,prezzo...**/
            case "/showProduct":
                id = Integer.parseInt(request.getParameter("id"));
                // prodotto = gestioneProdottoServiceImp.getProdottoConCategoria(id);
                prodotto = gestioneProdottoServiceImp.getProdotto(id);
                reviews = recensioneServiceImp.cercaRecensioniPerProdotto(prodotto);

                request.setAttribute("recensioni1", reviews);
                request.setAttribute("prodotto", prodotto);
                request.setAttribute("size", reviews.size());
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/prodottoguest.jsp");
                dispatcher.forward(request, response);
                break;

            /**si vusualizza il prodotto con descrizione,prezzo...(UTENTE)**/
            case "/showProductUtente":
                account = (Account) request.getSession(false).getAttribute("account");
                id = Integer.parseInt(request.getParameter("id"));
                prodotto = gestioneProdottoServiceImp.getProdotto(id);
                ListaDesideri listaDesideri = listaDesideriServiceImp.getListaDesideri(account);
                orders = gestioneAcquistiServiceImp.searchAllOrderWithProductsbyAccount(account);
                boolean presente = false;
                for (int j = 0; j < orders.size(); j++) {
                    ArrayList<Prodotto> prodottiArrayList = orders.get(j).getProducts();
                    System.out.println(prodottiArrayList.size());
                    for (int z = 0; z < prodottiArrayList.size(); z++) {
                        if (prodottiArrayList.get(z).getId() == prodotto.getId())
                            presente = true;
                    }
                }

                boolean aggiunto = false;
                if (listaDesideri.containsListaDesideri(prodotto)) {
                    aggiunto = true;
                }

                reviews = recensioneServiceImp.cercaRecensioniPerProdotto(prodotto);
                boolean controllo = false;
                for (int i = 0; i < reviews.size(); i++) {
                    if (account.getId() == reviews.get(i).getAccount().getId())
                        controllo = true;
                }
                request.setAttribute("presente", presente);
                request.setAttribute("controllo", controllo);
                request.setAttribute("aggiunto", aggiunto);
                request.setAttribute("recensioni", reviews);
                request.setAttribute("prodotto", prodotto);
                request.setAttribute("size", reviews.size());
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                dispatcher.forward(request, response);
                break;

            /**viene visualizzate la pagina con i prodotti per categoria e piattaforma **/
            case "/showProductsWithCatAndPla":
                categoria = request.getParameter("category");
                piattaforma = request.getParameter("pla");
                prodotti = gestioneProdottoServiceImp.getAllProdottiPerCategoriaEPiattaforma(categoria, piattaforma);
                request.setAttribute("prodotti", prodotti);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/prodottiCategory.jsp");
                dispatcher.forward(request, response);
                break;

            /**viene visualizzate la pagina con i prodotti per categoria e piattaforma(UTENTE)**/
            case "/showProductsWithCatAndPlaUtent":
                categoria = request.getParameter("category");
                piattaforma = request.getParameter("pla");
                prodotti = gestioneProdottoServiceImp.getAllProdottiPerCategoriaEPiattaforma(categoria, piattaforma);
                request.setAttribute("prodotti", prodotti);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/prodottiCategoryUtent.jsp");
                dispatcher.forward(request, response);
                break;


            /**si visualizza la pagina per creare un prodotto(ADMIN)**/
            case "/showCreatProduct":
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/addProduct.jsp");
                dispatcher.forward(request, response);
                break;

            /**si elimina un prodotto(ADMIN)**/

            case "/deleteProduct":
                id = Integer.parseInt(request.getParameter("id"));
                prodotto = gestioneProdottoServiceImp.getProdotto(id);
                eliminato = gestioneProdottoServiceImp.rimuoviProdotto(id);
                if (eliminato) {
                    File file = new File("C:\\Users\\PC\\IdeaProjects\\GameOverNew\\src\\main\\webapp\\cover\\" + prodotto.getCover());
                    file.delete();
                }
                request.setAttribute("delete", eliminato);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                dispatcher.forward(request, response);
                break;

            case "/searchProductAdmin":
                prodottoSearch = new ArrayList<>();
                stringa = request.getParameter("stringa");
                pattern = Pattern.compile(stringa, Pattern.CASE_INSENSITIVE);
                prodotti = gestioneProdottoServiceImp.getAllProdotti();
                for (int i = 0; i < prodotti.size(); i++) {
                    Matcher matcher = pattern.matcher(prodotti.get(i).getProductName());
                    if (matcher.find())
                        prodottoSearch.add(prodotti.get(i));
                }
                if (prodottoSearch.size() == 0) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/searchProductAdminFailed.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("vetrina", prodottoSearch);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/searchProductAdmin.jsp");
                    dispatcher.forward(request, response);
                }
                break;

        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/":
                break;
            /**si crea il prodotto**/
            case "/createProduct":
                String nome = request.getParameter("nome");
                String prezzo = (request.getParameter("prezzo"));
                String descrizione = request.getParameter("description");
                Part cover = request.getPart("cover");
                LocalDate dataUscita = LocalDate.parse(request.getParameter("data"));
                String categoria = request.getParameter("categoria");
                String piattaforma = request.getParameter("piattaforma");


                ValidateForm validateForm=new ValidateForm();
                if(validateForm.validateCoverProdotto(cover)){
                    String fileName = Paths.get(cover.getSubmittedFileName()).getFileName().toString();
                    success = aggiungiProdotto(nome, prezzo, descrizione, fileName, dataUscita, categoria, piattaforma);
                }

                if (success) {
                    String fileName = Paths.get(cover.getSubmittedFileName()).getFileName().toString();
                    InputStream inputStream = cover.getInputStream();
                    File file = new File("C:\\Users\\PC\\IdeaProjects\\GameOverNew\\src\\main\\webapp\\cover\\" + fileName);
                    Files.copy(inputStream, file.toPath());
                    request.setAttribute("success", success);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("success", success);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/addProduct.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**si modifica il prodotto**/
            case "/updateProduct":
                ValidateForm validateForm1 = new ValidateForm();
                id = Integer.parseInt(request.getParameter("id"));
                prodotto = gestioneProdottoServiceImp.getProdotto(id);

                boolean nomeUpdate = validateForm1.validateNomeProdotto(request.getParameter("nome"));
                boolean prezzoUpdate = validateForm1.validatePrezzoProdotto(request.getParameter("prezzo"));
                boolean descrizioneUpdate = validateForm1.validateDescrizioneProdotto(request.getParameter("description"));
                boolean update = false;
                if (nomeUpdate && prezzoUpdate && descrizioneUpdate) {
                    prodotto.setProductName(request.getParameter("nome"));
                    prodotto.setDescription(request.getParameter("description"));
                    prodotto.setPrice(Double.parseDouble(request.getParameter("prezzo")));
                    update = gestioneProdottoServiceImp.modificaProdotto(prodotto);
                    request.setAttribute("update", update);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                    dispatcher.forward(request, response);
                } else {
                    update = false;
                    request.setAttribute("update", update);
                    request.setAttribute("prodotto", prodotto);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateProduct.jsp");
                    dispatcher.forward(request, response);
                }
                break;
        }
    }
}
