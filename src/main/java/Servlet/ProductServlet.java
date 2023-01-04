package Servlet;


import com.google.gson.Gson;
import model.dao.category.SqlCategoryDao;
import model.dao.platform.SqlPlatformDao;
import model.dao.product.SqlProductDao;
import model.entity.Category;
import model.entity.Platform;
import model.entity.Product;

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
    Product product1=new Product();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher;

        SqlProductDao sqlProductDao=new SqlProductDao();
        Product product=new Product();
        ArrayList<Product> products=new ArrayList<>();

        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";

        switch (path){

            /**si visualizza la pagina per modificare il prodotto**/
            case "/updateProduct":


                try {
                  product=sqlProductDao.searchProductWithPlatformsAndCategory(Integer.parseInt(request.getParameter("id")));

                  request.setAttribute("product",product);
                  dispatcher= request.getRequestDispatcher("/WEB-INF/views/admin/updateProduct.jsp");
                  dispatcher.forward(request,response);




                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;

            /**si visualizzano tutti i prodotti**/
            case "/showAllProduct":

                try {
                    products=sqlProductDao.searchProductsByCategoryAndPlatform1();
                    request.setAttribute("products",products);
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

                ArrayList<Product> productSearch1=new ArrayList<>();
                try {
                    products=sqlProductDao.searchAllProducts();

                    for (int i=0; i<products.size(); i++){
                        Matcher matcher = pattern1.matcher(products.get(i).getProductName());
                        if(matcher.find())
                            productSearch1.add(products.get(i));

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                    if(productSearch1.size()==0){
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/searchProductFailed.jsp");
                        dispatcher.forward(request, response);

                     }else {

                        request.setAttribute("vetrina", productSearch1);

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/searchProduct.jsp");
                        dispatcher.forward(request, response);
                    }

                break;

            /**ricerca prodotti con ajax(Guest)**/
            case "/searchProductGuest":
                String stringa2=request.getParameter("stringa");

                Pattern pattern2 = Pattern.compile(stringa2, Pattern.CASE_INSENSITIVE);

                ArrayList<Product> productSearch2=new ArrayList<>();
                try {
                    products=sqlProductDao.searchAllProducts();

                    for (int i=0; i<products.size(); i++){
                        Matcher matcher = pattern2.matcher(products.get(i).getProductName());
                        if(matcher.find())
                            productSearch2.add(products.get(i));

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                if(productSearch2.size()==0){
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/searchProductGuestFailed.jsp");
                    dispatcher.forward(request, response);

                }else {

                    request.setAttribute("vetrina", productSearch2);

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/searchProductGuest.jsp");
                    dispatcher.forward(request, response);
                }

                break;

            case "/searchProductWithAjax":

                String stringa=request.getParameter("stringa");
                Pattern pattern = Pattern.compile(stringa, Pattern.CASE_INSENSITIVE);

                 ArrayList<Product> productSearch=new ArrayList<>();
                try {
                    products=sqlProductDao.searchAllProducts();

                    for (int i=0; i<products.size(); i++){
                        Matcher matcher = pattern.matcher(products.get(i).getProductName());
                       if(matcher.find())
                           productSearch.add(products.get(i));

                    }

                    String json = new Gson().toJson(productSearch);
                    response.setContentType("text/plain;charset=UTF-8");
                    response.getWriter().println(json);



                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            /**si vusualizza il prodotto con descrizione,prezzo...**/
            case "/showProduct":

                try {
                    product=sqlProductDao.searchProductWithCategory(Integer.parseInt(request.getParameter("id")));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                request.setAttribute("prodotto",product);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/guest/prodottoguest.jsp");
                dispatcher.forward(request,response);
                break;

            /**si vusualizza il prodotto con descrizione,prezzo...(UTENTE)**/
            case "/showProductUtente":

                try {
                    product=sqlProductDao.searchProductWithCategory(Integer.parseInt(request.getParameter("id")));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                request.setAttribute("prodotto",product);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/prodottoUtente.jsp");
                dispatcher.forward(request,response);
                break;
            /**viene visualizzate la pagina con i prodotti per categoria e piattaforma **/
            case "/showProductsWithCatAndPla":

                try {
                    products=sqlProductDao.searchProductsByCategoryAndPlatform(request.getParameter("category"),Integer.parseInt(request.getParameter("pla")));

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                request.setAttribute("prodotti",products);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/guest/prodottiCategory.jsp");
                dispatcher.forward(request,response);
                break;
            /**viene visualizzate la pagina con i prodotti per categoria e piattaforma(UTENTE)**/
            case "/showProductsWithCatAndPlaUtent":

                try {
                    products=sqlProductDao.searchProductsByCategoryAndPlatform(request.getParameter("category"),Integer.parseInt(request.getParameter("pla")));
                    System.out.println(products.size());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                request.setAttribute("prodotti",products);
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
                    product=productDao2.searchProduct(Integer.parseInt(request.getParameter("id")));
                    boolean eliminato=productDao2.deleteProduct(Integer.parseInt(request.getParameter("id")));

                    if(eliminato){

                        File file= new File(""+product.getCover());
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

                ArrayList<Product> productSearch3=new ArrayList<>();
                try {
                    products=sqlProductDao.searchAllProducts();

                    for (int i=0; i<products.size(); i++){
                        Matcher matcher = pattern3.matcher(products.get(i).getProductName());
                        if(matcher.find())
                            productSearch3.add(products.get(i));

                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                if(productSearch3.size()==0){
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/searchProductAdminFailed.jsp");
                    dispatcher.forward(request, response);

                }else {

                    request.setAttribute("vetrina", productSearch3);

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
                Product product=new Product();

                int idCategoria = Integer.parseInt(request.getParameter("categoria"));
                int idPiattaforma=Integer.parseInt(request.getParameter("piattaforma"));


                    try {
                    Platform platform = sqlPlatformDao.searchPlatform(idPiattaforma);
                    Category category = sqlCategoryDao.searchCategory(idCategoria);

                    product.setProductName(request.getParameter("nome"));

                    product.setCategory(category);
                    product.setPlatform(platform);
                    product.setDate(LocalDate.parse(request.getParameter("data")));
                    String descrizione=request.getParameter("description");
                    product.setDescription(descrizione);

                    product.setPrice(Double.parseDouble(request.getParameter("prezzo")));


                    Part filePart = request.getPart("cover");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    product.setCover(fileName);


                    if(productDao.createProduct(product)){


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

                    product1=productDao2.searchProduct(Integer.parseInt(request.getParameter("id")));

                    product1.setProductName(request.getParameter("nome"));

                    product1.setDescription(request.getParameter("description"));

                    product1.setPrice(Double.parseDouble(request.getParameter("prezzo")));

                    if(productDao2.updateProduct(product1)){

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
