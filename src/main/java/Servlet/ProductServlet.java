package Servlet;


import com.google.gson.Gson;
import listaDesideri.service.ListaDesideriServiceImp;
import model.dao.category.SqlCategoryDao;
import model.dao.platform.SqlPlatformDao;
import model.dao.product.SqlProductDao;
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

@WebServlet(name = "ProductServlet", value = "/ProductServlet/*")
@MultipartConfig
public class ProductServlet extends HttpServlet {
    SqlCategoryDao sqlCategoryDao1= new SqlCategoryDao();
    SqlProductDao productDao2= new SqlProductDao();
    SqlPlatformDao sqlPlatformDao1= new SqlPlatformDao();
    Prodotto prodotto1 =new Prodotto();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;

        SqlProductDao sqlProductDao=new SqlProductDao();
        Prodotto prodotto =new Prodotto();
        ArrayList<Prodotto> prodottos =new ArrayList<>();

        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";

        switch (path){

            /**si visualizza la pagina per modificare il prodotto**/
            case "/updateProduct":


                try {
                  prodotto =sqlProductDao.searchProductWithPlatformsAndCategory(Integer.parseInt(request.getParameter("id")));

                  request.setAttribute("product", prodotto);
                  dispatcher= request.getRequestDispatcher("/WEB-INF/views/admin/updateProduct.jsp");
                  dispatcher.forward(request,response);




                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;

            /**si visualizzano tutti i prodotti**/
            case "/showAllProduct":

                try {
                    prodottos =sqlProductDao.searchProductsByCategoryAndPlatform1();
                    request.setAttribute("products", prodottos);
                    dispatcher= request.getRequestDispatcher("/WEB-INF/views/admin/showAllProduct.jsp");
                    dispatcher.forward(request,response);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                break;

            /**ricerca prodotti con ajax(Utente)**/
            case "/searchProductUtent":
                String stringa1=request.getParameter("stringa");

                Pattern pattern1 = Pattern.compile(stringa1, Pattern.CASE_INSENSITIVE);

                ArrayList<Prodotto> prodottoSearch1 =new ArrayList<>();
                try {
                    prodottos =sqlProductDao.searchAllProducts();

                    for (int i = 0; i< prodottos.size(); i++){
                        Matcher matcher = pattern1.matcher(prodottos.get(i).getProductName());
                        if(matcher.find())
                            prodottoSearch1.add(prodottos.get(i));

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                    if(prodottoSearch1.size()==0){
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/searchProductFailed.jsp");
                        dispatcher.forward(request, response);

                     }else {

                        request.setAttribute("vetrina", prodottoSearch1);

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/searchProduct.jsp");
                        dispatcher.forward(request, response);
                    }

                break;

            /**ricerca prodotti con ajax(Guest)**/
            case "/searchProductGuest":
                String stringa2=request.getParameter("stringa");

                Pattern pattern2 = Pattern.compile(stringa2, Pattern.CASE_INSENSITIVE);

                ArrayList<Prodotto> prodottoSearch2 =new ArrayList<>();
                try {
                    prodottos =sqlProductDao.searchAllProducts();

                    for (int i = 0; i< prodottos.size(); i++){
                        Matcher matcher = pattern2.matcher(prodottos.get(i).getProductName());
                        if(matcher.find())
                            prodottoSearch2.add(prodottos.get(i));

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                if(prodottoSearch2.size()==0){
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/searchProductGuestFailed.jsp");
                    dispatcher.forward(request, response);

                }else {

                    request.setAttribute("vetrina", prodottoSearch2);

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/searchProductGuest.jsp");
                    dispatcher.forward(request, response);
                }

                break;

            case "/searchProductWithAjax":

                String stringa=request.getParameter("stringa");
                Pattern pattern = Pattern.compile(stringa, Pattern.CASE_INSENSITIVE);

                 ArrayList<Prodotto> prodottoSearches =new ArrayList<>();
                try {
                    prodottos =sqlProductDao.searchAllProducts();

                    for (int i = 0; i< prodottos.size(); i++){
                        Matcher matcher = pattern.matcher(prodottos.get(i).getProductName());
                       if(matcher.find())
                           prodottoSearches.add(prodottos.get(i));

                    }

                    String json = new Gson().toJson(prodottoSearches);
                    response.setContentType("text/plain;charset=UTF-8");
                    response.getWriter().println(json);



                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            /**si vusualizza il prodotto con descrizione,prezzo...**/
            case "/showProduct":

                try {
                    prodotto =sqlProductDao.searchProductWithCategory(Integer.parseInt(request.getParameter("id")));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ArrayList<Review> reviews=new ArrayList<>();
                RecensioneServiceImp recensioneServiceImp=new RecensioneServiceImp();
                reviews=recensioneServiceImp.cercaRecensioniPerProdotto(prodotto);
                System.out.println(reviews.size());
                request.setAttribute("recensioni1", reviews);
                request.setAttribute("prodotto", prodotto);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/guest/prodottoguest.jsp");
                dispatcher.forward(request,response);
                break;

            /**si vusualizza il prodotto con descrizione,prezzo...(UTENTE)**/
            case "/showProductUtente":
                Account account=(Account) request.getSession(false).getAttribute("account");
                try {
                    prodotto =sqlProductDao.searchProductWithCategory(Integer.parseInt(request.getParameter("id")));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                ListaDesideriServiceImp listaDesideriServiceImp=new ListaDesideriServiceImp();
                ListaDesideri listaDesideri=listaDesideriServiceImp.getListaDesideri(account);
                boolean aggiunto=false;
                if(listaDesideri.containsListaDesideri(prodotto)){
                    aggiunto=true;
                }

                RecensioneServiceImp recensioneServiceImp1=new RecensioneServiceImp();
                ArrayList<Review> reviews1=new ArrayList<>();
                reviews1=recensioneServiceImp1.cercaRecensioniPerProdotto(prodotto);
                request.setAttribute("aggiunto",aggiunto);
                request.setAttribute("recensioni", reviews1);
                request.setAttribute("prodotto", prodotto);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                dispatcher.forward(request,response);
                break;
            /**viene visualizzate la pagina con i prodotti per categoria e piattaforma **/
            case "/showProductsWithCatAndPla":

                try {
                    prodottos =sqlProductDao.searchProductsByCategoryAndPlatform(request.getParameter("category"),Integer.parseInt(request.getParameter("pla")));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                request.setAttribute("prodotti", prodottos);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/guest/prodottiCategory.jsp");
                dispatcher.forward(request,response);
                break;
            /**viene visualizzate la pagina con i prodotti per categoria e piattaforma(UTENTE)**/
            case "/showProductsWithCatAndPlaUtent":

                try {
                    prodottos =sqlProductDao.searchProductsByCategoryAndPlatform(request.getParameter("category"),Integer.parseInt(request.getParameter("pla")));
                    System.out.println(prodottos.size());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                request.setAttribute("prodotti", prodottos);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/prodottiCategoryUtent.jsp");
                dispatcher.forward(request,response);
                break;

            /**si visualizza la pagina per creare un prodotto(ADMIN)**/
            case "/showCreatProduct":

                dispatcher= request.getRequestDispatcher("/WEB-INF/views/admin/addProduct.jsp");
                dispatcher.forward(request,response);

                break;
            /**si elimina un prodotto(ADMIN)**/
            case "/deleteProduct":

                try {
                    prodotto =productDao2.searchProduct(Integer.parseInt(request.getParameter("id")));
                    boolean eliminato=productDao2.deleteProduct(Integer.parseInt(request.getParameter("id")));

                    if(eliminato){

                        File file= new File(""+ prodotto.getCover());
                        file.delete();

                        request.setAttribute("delete",eliminato);
                        dispatcher= request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                        dispatcher.forward(request,response);


                    }

                    else{

                        request.setAttribute("delete",eliminato);
                        dispatcher= request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                        dispatcher.forward(request,response);
                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                break;

            /**ricerca prodotti con ajax(ADMIN)**/
            case "/searchProductAdmin":

                String stringa3=request.getParameter("stringa");

                Pattern pattern3 = Pattern.compile(stringa3, Pattern.CASE_INSENSITIVE);

                ArrayList<Prodotto> prodottoSearch3 =new ArrayList<>();
                try {
                    prodottos =sqlProductDao.searchAllProducts();

                    for (int i = 0; i< prodottos.size(); i++){
                        Matcher matcher = pattern3.matcher(prodottos.get(i).getProductName());
                        if(matcher.find())
                            prodottoSearch3.add(prodottos.get(i));

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if(prodottoSearch3.size()==0){
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/searchProductAdminFailed.jsp");
                    dispatcher.forward(request, response);

                }else {

                    request.setAttribute("vetrina", prodottoSearch3);

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/searchProductAdmin.jsp");
                    dispatcher.forward(request, response);
                }


                break;



            default:  response.sendError(HttpServletResponse.SC_NOT_FOUND);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher;


        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";

        switch (path){

            case "/":
                break;

            /**si crea il prodotto**/
            case "/createProduct":
                SqlCategoryDao sqlCategoryDao= new SqlCategoryDao();
                SqlProductDao productDao= new SqlProductDao();
                SqlPlatformDao sqlPlatformDao= new SqlPlatformDao();
                Prodotto prodotto =new Prodotto();

                int idCategoria = Integer.parseInt(request.getParameter("categoria"));
                int idPiattaforma=Integer.parseInt(request.getParameter("piattaforma"));


                    try {
                    Platform platform = sqlPlatformDao.searchPlatform(idPiattaforma);
                    Category category = sqlCategoryDao.searchCategory(idCategoria);

                    prodotto.setProductName(request.getParameter("nome"));

                    prodotto.setCategory(category);
                    prodotto.setPlatform(platform);
                    prodotto.setDate(LocalDate.parse(request.getParameter("data")));
                    String descrizione=request.getParameter("description");
                    prodotto.setDescription(descrizione);

                    prodotto.setPrice(Double.parseDouble(request.getParameter("prezzo")));


                    Part filePart = request.getPart("cover");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    prodotto.setCover(fileName);


                    if(productDao.createProduct(prodotto)){


                        InputStream inputStream=filePart.getInputStream();
                        File file= new File("C:\\Users\\andre\\Desktop\\GameOver\\src\\main\\webapp\\cover\\"+fileName);
                        Files.copy(inputStream,file.toPath());

                        boolean success=true;
                        request.setAttribute("success",success);
                        dispatcher= request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                        dispatcher.forward(request,response);
                    }
                    else{

                        boolean success=false;
                        request.setAttribute("success",success);
                        dispatcher= request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                        dispatcher.forward(request,response);

                    }

                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }




                break;

            /**si modifica il prodotto**/
            case "/updateProduct":

                try {

                    prodotto1 =productDao2.searchProduct(Integer.parseInt(request.getParameter("id")));

                    prodotto1.setProductName(request.getParameter("nome"));

                    prodotto1.setDescription(request.getParameter("description"));

                    prodotto1.setPrice(Double.parseDouble(request.getParameter("prezzo")));

                    if(productDao2.updateProduct(prodotto1)){

                        boolean update=true;
                        request.setAttribute("update",update);
                        dispatcher= request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                        dispatcher.forward(request,response);
                    }
                    else{

                        boolean update=false;
                        request.setAttribute("update",update);
                        dispatcher= request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                        dispatcher.forward(request,response);

                    }

                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;



            case "/logout":
                break;

            default:  response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);


        }
    }
}
