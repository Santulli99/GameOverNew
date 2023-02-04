package gestioneProdotto.controller;

import com.google.gson.Gson;
import gestioneProdotto.service.GestioneProdottoServiceImp;
import listaDesideri.service.ListaDesideriServiceImp;
import model.entity.*;
import recensione.service.RecensioneServiceImp;

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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "GestioneProdottoController", value = "/GestioneProdottoController/*")
@MultipartConfig
public class GestioneProdottoController extends HttpServlet {

    private String path;
    private Prodotto prodotto = new Prodotto();
    private RequestDispatcher dispatcher;
    private ArrayList<Prodotto> prodotti = new ArrayList<>();
    private int id;
    private Account account = new Account();

    private boolean eliminato;
    private String stringa;
    private Pattern pattern;
    private String categoria;
    private ArrayList<Prodotto> prodottoSearch;
    private ArrayList<Review> reviews = new ArrayList<>();

    private GestioneProdottoServiceImp gestioneProdottoServiceImp = new GestioneProdottoServiceImp();
    private ListaDesideriServiceImp listaDesideriServiceImp = new ListaDesideriServiceImp();
    private RecensioneServiceImp recensioneServiceImp = new RecensioneServiceImp();

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
                prodotti = gestioneProdottoServiceImp.getAllProdottiConCategoriaEPiattaforma();
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
                System.out.println("VALORE:" + stringa);
                pattern = Pattern.compile(stringa, Pattern.CASE_INSENSITIVE);
                System.out.println("VALORE pattern:" + pattern);
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
                prodotto = gestioneProdottoServiceImp.getProdottoConCategoria(id);
                reviews = recensioneServiceImp.cercaRecensioniPerProdotto(prodotto);

                request.setAttribute("recensioni1", reviews);
                request.setAttribute("prodotto", prodotto);
                request.setAttribute("size",reviews.size());
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/prodottoguest.jsp");
                dispatcher.forward(request, response);
                break;

            /**si vusualizza il prodotto con descrizione,prezzo...(UTENTE)**/
            case "/showProductUtente":
                account = (Account) request.getSession(false).getAttribute("account");
                id = Integer.parseInt(request.getParameter("id"));
                prodotto = gestioneProdottoServiceImp.getProdottoConCategoria(id);
                ListaDesideri listaDesideri = listaDesideriServiceImp.getListaDesideri(account);

                boolean aggiunto = false;
                if (listaDesideri.containsListaDesideri(prodotto)) {
                    aggiunto = true;
                }

                reviews = recensioneServiceImp.cercaRecensioniPerProdotto(prodotto);
                boolean controllo=false;
                for(int i =0;i<reviews.size();i++){
                    if(account.getId()==reviews.get(i).getAccount().getId())
                        controllo=true;
                }
                request.setAttribute("controllo",controllo);
                request.setAttribute("aggiunto", aggiunto);
                request.setAttribute("recensioni", reviews);
                request.setAttribute("prodotto", prodotto);
                request.setAttribute("size",reviews.size());
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                dispatcher.forward(request, response);
                break;

            /**viene visualizzate la pagina con i prodotti per categoria e piattaforma **/
            case "/showProductsWithCatAndPla":
                categoria = request.getParameter("category");
                id = Integer.parseInt(request.getParameter("pla"));
                prodotti = gestioneProdottoServiceImp.getAllProdottiPerCategoriaEPiattaforma(categoria, id);
                request.setAttribute("prodotti", prodotti);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/prodottiCategory.jsp");
                dispatcher.forward(request, response);
                break;

            /**viene visualizzate la pagina con i prodotti per categoria e piattaforma(UTENTE)**/
            case "/showProductsWithCatAndPlaUtent":
                categoria = request.getParameter("category");
                id = Integer.parseInt(request.getParameter("pla"));
                prodotti = gestioneProdottoServiceImp.getAllProdottiPerCategoriaEPiattaforma(categoria, id);
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
                prodotto = gestioneProdottoServiceImp.getProdottoPerId(id);
                eliminato = gestioneProdottoServiceImp.rimuoviProdotto(id);
                if (eliminato) {
                    File file = new File("" + prodotto.getCover());
                    file.delete();
                    request.setAttribute("delete", eliminato);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("delete", eliminato);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                    dispatcher.forward(request, response);
                }

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
                int idCategoria = Integer.parseInt(request.getParameter("categoria"));
                System.out.println(idCategoria);
                int idPiattaforma = Integer.parseInt(request.getParameter("piattaforma"));
                System.out.println(idPiattaforma);

                Platform platform = gestioneProdottoServiceImp.getPiattaforma(idPiattaforma);
                Category category = gestioneProdottoServiceImp.getCategoria(idCategoria);

                prodotto.setProductName(request.getParameter("nome"));
                prodotto.setCategory(category);
                prodotto.setPlatform(platform);
                prodotto.setDate(LocalDate.parse(request.getParameter("data")).plusDays(1));
                prodotto.setDescription(request.getParameter("description"));
                prodotto.setPrice(Double.parseDouble(request.getParameter("prezzo")));
                prodotto.setValutazioneMedia(0);
                Part filePart = request.getPart("cover");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                prodotto.setCover(fileName);
                if (gestioneProdottoServiceImp.aggiungiProdotto(prodotto)) {
                    InputStream inputStream = filePart.getInputStream();
                    File file = new File("C:\\Users\\PC\\IdeaProjects\\GameOverNew\\src\\main\\webapp\\cover\\" + fileName);
                    Files.copy(inputStream, file.toPath());
                    boolean success = true;
                    request.setAttribute("success", success);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                    dispatcher.forward(request, response);
                } else {
                    boolean success = false;
                    request.setAttribute("success", success);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                    dispatcher.forward(request, response);
                }

                break;
            /**si modifica il prodotto**/
            case "/updateProduct":

                id = Integer.parseInt(request.getParameter("id"));
                prodotto = gestioneProdottoServiceImp.getProdottoPerId(id);

                prodotto.setProductName(request.getParameter("nome"));
                prodotto.setDescription(request.getParameter("description"));
                prodotto.setPrice(Double.parseDouble(request.getParameter("prezzo")));

                if (gestioneProdottoServiceImp.modificaProdotto(prodotto)) {
                    boolean update = true;
                    request.setAttribute("update", update);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                    dispatcher.forward(request, response);
                } else {
                    boolean update = false;
                    request.setAttribute("update", update);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                    dispatcher.forward(request, response);
                }
                break;
        }
    }
}
