package gestioneProdotto.controller;

import com.google.gson.Gson;
import gestioneProdotto.service.GestioneProdottoService;
import gestioneProdotto.service.GestioneProdottoServiceImp;
import listaDesideri.service.ListaDesideriServiceImp;
import model.dao.category.SqlCategoryDao;
import model.dao.platform.SqlPlatformDao;
import model.dao.product.SqlProductDao;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "GestioneProdottoController", value = "/GestioneProdottoController/*")
@MultipartConfig
public class GestioneProdottoController extends HttpServlet {

    private String path;
    private SqlProductDao sqlProductDao = new SqlProductDao();
    private SqlCategoryDao sqlCategoryDao = new SqlCategoryDao();
    private SqlPlatformDao sqlPlatformDao = new SqlPlatformDao();
    private Prodotto prodotto=new Prodotto();
    private RequestDispatcher dispatcher;
    private ArrayList<Prodotto> prodotti=new ArrayList<>();
    private int id;
    private Account account;

    private boolean eliminato;
    ArrayList<Prodotto> prodottoSearch = new ArrayList<>();

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
                request.setAttribute("product", prodotto);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateProduct.jsp");
                dispatcher.forward(request, response);
                break;

            /**si visualizzano tutti i prodotti**/
            case "/showAllProduct":
                prodotti = gestioneProdottoServiceImp.getAllProdottiConCategoriaEPiattaforma();
                request.setAttribute("products", prodotti);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showAllProduct.jsp");
                dispatcher.forward(request, response);

                break;

            /**ricerca prodotti con ajax(Utente)**/
            case "/searchProductUtent":
                String stringa1 = request.getParameter("stringa");
                Pattern pattern1 = Pattern.compile(stringa1, Pattern.CASE_INSENSITIVE);
                prodotti = gestioneProdottoServiceImp.getAllProdotti();

                for (int i = 0; i < prodotti.size(); i++) {
                    Matcher matcher = pattern1.matcher(prodotti.get(i).getProductName());
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
                String stringa2 = request.getParameter("stringa");
                Pattern pattern2 = Pattern.compile(stringa2, Pattern.CASE_INSENSITIVE);
                prodotti = gestioneProdottoServiceImp.getAllProdotti();
                for (int i = 0; i < prodotti.size(); i++) {
                    Matcher matcher = pattern2.matcher(prodotti.get(i).getProductName());
                    if (matcher.find())
                        prodottoSearch.add(prodotti.get(i));
                }

                if (prodottoSearch.size() == 0) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/searchProductGuestFailed.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("vetrina", prodottoSearch);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/searchProductGuest.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            case "/searchProductWithAjax":

                String stringa = request.getParameter("stringa");
                Pattern pattern = Pattern.compile(stringa, Pattern.CASE_INSENSITIVE);
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
                ArrayList<Review> reviews = new ArrayList<>();
                reviews = recensioneServiceImp.cercaRecensioniPerProdotto(prodotto);
                System.out.println(reviews.size());
                request.setAttribute("recensioni1", reviews);
                request.setAttribute("prodotto", prodotto);
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

                ArrayList<Review> reviews1 = new ArrayList<>();
                reviews1 = recensioneServiceImp.cercaRecensioniPerProdotto(prodotto);
                request.setAttribute("aggiunto", aggiunto);
                request.setAttribute("recensioni", reviews1);
                request.setAttribute("prodotto", prodotto);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                dispatcher.forward(request, response);
                break;

            /**viene visualizzate la pagina con i prodotti per categoria e piattaforma **/
            case "/showProductsWithCatAndPla":
                String categoria = request.getParameter("category");
                id = Integer.parseInt(request.getParameter("pla"));
                prodotti = gestioneProdottoServiceImp.getAllProdottiPerCategoriaEPiattaforma(categoria, id);
                request.setAttribute("prodotti", prodotti);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/prodottiCategory.jsp");
                dispatcher.forward(request, response);
                break;

            /**viene visualizzate la pagina con i prodotti per categoria e piattaforma(UTENTE)**/
            case "/showProductsWithCatAndPlaUtent":
                String categoria1 = request.getParameter("category");
                id = Integer.parseInt(request.getParameter("pla"));
                prodotti = gestioneProdottoServiceImp.getAllProdottiPerCategoriaEPiattaforma(categoria1, id);
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
            /**ricerca prodotti con ajax(ADMIN)**/
            case "/searchProductAdmin":

                String stringa3 = request.getParameter("stringa");
                Pattern pattern3 = Pattern.compile(stringa3, Pattern.CASE_INSENSITIVE);
                ArrayList<Prodotto> prodottoSearch = new ArrayList<>();
                prodotti = gestioneProdottoServiceImp.getAllProdotti();
                for (int i = 0; i < prodotti.size(); i++) {
                    Matcher matcher = pattern3.matcher(prodotti.get(i).getProductName());
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
                int idPiattaforma = Integer.parseInt(request.getParameter("piattaforma"));


                Platform platform = gestioneProdottoServiceImp.getPiattaforma(idPiattaforma);
                Category category = gestioneProdottoServiceImp.getCategoria(idCategoria);

                prodotto.setProductName(request.getParameter("nome"));
                prodotto.setCategory(category);
                prodotto.setPlatform(platform);
                prodotto.setDate(LocalDate.parse(request.getParameter("data")));
                prodotto.setDescription(request.getParameter("description"));
                prodotto.setPrice(Double.parseDouble(request.getParameter("prezzo")));
                prodotto.setValutazioneMedia(0);
                Part filePart = request.getPart("cover");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                prodotto.setCover(fileName);
                if (gestioneProdottoServiceImp.aggiungiProdotto(prodotto)) {
                    InputStream inputStream = filePart.getInputStream();
                    File file = new File("C:\\Users\\Gerry\\IdeaProjects\\GameOverNew\\src\\main\\webapp\\cover\\" + fileName);
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
