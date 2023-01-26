package Servlet;

import model.dao.account.SqlAccountDao;
import model.dao.address.SqlAddressDao;
import model.dao.dataClient.SqlDataClientDao;
import model.dao.order.SqlOrderDao;
import model.dao.product.SqlProductDao;
import model.entity.*;
import validate.ValidateForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet(name = "AccountServlet", value = "/AccountServlet/*")
public class AccountServlet extends HttpServlet {

    private  HttpSession session;
    private  RequestDispatcher dispatcher;
    private SqlAccountDao accountDao=new SqlAccountDao();
    private Account account=new Account();
    private   boolean modifica;
    private   boolean modificaEmail;

    SqlDataClientDao sqlDataClientDao=new SqlDataClientDao();
    SqlAddressDao sqlAddressDao=new SqlAddressDao();
    DataClient dataClient=new DataClient();
    Address address=new Address();
    SqlProductDao sqlProductDao = new SqlProductDao();
    SqlAccountDao sqlAccountDao2= new SqlAccountDao();
    SqlOrderDao sqlOrderDao =new SqlOrderDao();


    @Override
    public void init() throws ServletException {
        super.init();

        /** carico i prodotti nella vetrina nella memoria globlale **/

        try {
            ArrayList<Prodotto> prodottos;
            SqlProductDao sqlProductDao= new SqlProductDao();
            prodottos =sqlProductDao.searchProductsvetrina(2);
            getServletContext().setAttribute("vetrina", prodottos);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";


        switch (path){

            case "/":
                break;












            default:  response.sendError(HttpServletResponse.SC_NOT_FOUND);

        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";

        switch (path){

            case "/":
                break;


            /**si effettua la modifica dell'username**/
            case "/updateUsername":


                String newUsername= request.getParameter("username");
                String username2= request.getParameter("username2");
                boolean x=ValidateForm.validateUsername(newUsername);

                if(newUsername.equals(username2) && (x==true)) {

                    Account newAccount = new Account();
                    newAccount = (Account) request.getSession(false).getAttribute("account");

                    newAccount.setUsername(newUsername);

                    try {
                        accountDao.updateAccount(newAccount);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    modifica=true;
                    request.setAttribute("modificaUsername",modifica);
                    request.getSession(false).setAttribute("account", newAccount);

                    if(account.isAdmin()==true) {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);

                    }else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                }

                else{
                    if(account.isAdmin()==true) {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminUsername.jsp");
                        dispatcher.forward(request, response);

                    }else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentUsername.jsp");
                        dispatcher.forward(request, response);
                    }
                }

                break;

            /**si effettua la modifica dell'email**/
            case "/updateEmail":
                account = (Account) request.getSession(false).getAttribute("account");
                SqlAccountDao  sqlAccountDao1= new SqlAccountDao();
                String newEmail= request.getParameter("email");
                String newEmail2= request.getParameter("email1");
                boolean esiste;
                boolean validato_email=ValidateForm.validateEmail(newEmail);
                boolean validato1_email=ValidateForm.searchEmail(newEmail2);
                if((validato_email==true) && (validato1_email || account.getEmail().equals(newEmail)) && (newEmail.equals(newEmail2))){

                    account.setEmail(newEmail);
                    try {
                        sqlAccountDao1.updateAccount(account);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    modificaEmail=true;
                    request.setAttribute("modificaEmail",modificaEmail);

                    if(account.isAdmin()==true) {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);

                    }else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                }

                else{
                    esiste=true;
                    request.setAttribute("esiste",esiste);
                    if(account.isAdmin()==true) {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminEmail.jsp");
                        dispatcher.forward(request, response);
                    }
                    else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentEmail.jsp");
                        dispatcher.forward(request, response);
                    }
                }

                break;

            /**si effettua la modifica della password**/
            case "/updatePassword":
                SqlAccountDao  sqlAccountDao= new SqlAccountDao();
                String newPassword= request.getParameter("password");
                String newPassword2= request.getParameter("password2");

                boolean b=ValidateForm.validatePassword(newPassword);

                if(newPassword.equals(newPassword2) && (b==true)) {

                    Account newAccount1 = new Account();
                    newAccount1 = (Account) request.getSession(false).getAttribute("account");

                    newAccount1.setPassword(newPassword);

                    try {
                        sqlAccountDao.updateAccount(newAccount1);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    modifica=true;
                    request.setAttribute("modificaPassword",modifica);

                    request.getSession(false).setAttribute("account", newAccount1);

                    if(account.isAdmin()==true) {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);

                    }else {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                }

                else{
                    modifica=false;
                    request.setAttribute("error",modifica);
                    if(account.isAdmin()==true) {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminPassword.jsp");
                        dispatcher.forward(request, response);

                    }
                    else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentPassword.jsp");
                        dispatcher.forward(request, response);
                    }
                }

                break;

            case "/update":
                break;
            case "/delete":
                break;

            /**visualizza pagina amministratore**/
            case "/updateAdmin":
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/admin/updateAdmin.jsp");
                dispatcher.forward(request,response);
                break;

            /**si effettua il login**/
            case "/login":

                String password = request.getParameter("password");
                String email = request.getParameter("email");

                /** se rientriamo nel sito con un altro model.dao.account dobbiamo prima distruggere la sessione precendete **/

                Account accountprova=new Account();

                accountprova = (Account) request.getSession().getAttribute("account");
                if(accountprova!=null) {
                    String pass=accountprova.getPassword();
                    String em=accountprova.getEmail();
                    if(!(pass.equals(password) && em.equals(email))) {
                        request.getSession(false).invalidate();
                    }
                }




                    session = request.getSession(true);
                    try {
                        account = accountDao.searchAccountIdWithDataClientandAndressLogin(password, email);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    if (account == null) {
                        boolean login=false;
                        request.setAttribute("login",login);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                        dispatcher.forward(request, response);

                    }


                    if (account.isAdmin()) {
                        try {
                            /* numero prodotti*/
                            ArrayList<Prodotto> prodottos =sqlProductDao.searchAllProducts();
                            request.getSession(false).setAttribute("n_products", prodottos.size());

                            /*numero utenti*/
                            ArrayList<Account> accounts= sqlAccountDao2.searchAllAccount();
                            request.getSession(false).setAttribute("n_client",accounts.size());

                            /*Totale incasso mensile*/
                           ArrayList<Order> orders=sqlOrderDao.searchAllOrderWithProducts();
                           ArrayList<Order> newOrdini=new ArrayList<>();
                           LocalDate now=LocalDate.now();

                           for(int i=0;i<orders.size();i++){
                              if(orders.get(i).getDate().getMonth().equals(now.getMonth())){
                              newOrdini.add(orders.get(i));
                              }
                            }

                           double totale=0;
                           for(int j=0;j<newOrdini.size();j++){
                               totale+=newOrdini.get(j).getTotal();
                           }
                           request.getSession(false).setAttribute("totale_incasso",Math.round(totale*100.0)/100.0);

                           /*Numero ordini mensili*/
                            request.getSession(false).setAttribute("n_ordini",newOrdini.size());



                        session.setAttribute("account", account);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                        dispatcher.forward(request, response);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }

                    }

                    else {
                        session.setAttribute("account", account);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/utente.jsp");
                        dispatcher.forward(request, response);
                    }


                    break;

            case "/logout":
                break;



            default:  response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);

        }
    }
}
