package gestioneUtenti.controller;

import model.dao.account.AccountDao;
import model.dao.account.SqlAccountDao;
import model.dao.address.SqlAddressDao;
import model.entity.Account;
import model.entity.Address;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "GestioneUtenteController", value = "/GestioneUtenteController/*")
public class GestioneUtenteController extends HttpServlet {

   private  Account account;
    private RequestDispatcher dispatcher;
    private SqlAddressDao sqlAddressDao=new SqlAddressDao();

    private Address address;
    private boolean modifica;

    private SqlAccountDao accountDao=new SqlAccountDao();
    private   boolean modificaEmail;


    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";


        switch (path){
            case "/":
                break;
            case "/login":
                break;

        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";


        switch (path){
            case "/":
                break;

            /**visualizza il profilo dell'Admin**/
            case "/showAccountAdmin":

                account= (Account) request.getSession(false).getAttribute("account");
                try {
                    account= accountDao.searchAccountIdWithDataClientandAndress(account.getId());/** implementare servizi*/
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.setAttribute("account",account);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                dispatcher.forward(request,response);
                break;

            /**visualizza il profilo dell'Utente**/
            case "/showAccountUtent":

                account= (Account) request.getSession(false).getAttribute("account");

                try {
                    account= accountDao.searchAccountIdWithDataClientandAndress(account.getId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                modificaEmail=false;
                modifica=false;
                request.setAttribute("modificaEmail",modificaEmail);
                request.setAttribute("modifica",modifica);
                request.setAttribute("account",account);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                dispatcher.forward(request,response);
                break;

            /**viene visualizzata la pagina per la modifica dell'indirizzo**/
            case "/showUpdateAddress":

                account= (Account) request.getSession(false).getAttribute("account");

                if(account.isAdmin()==true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminAddress.jsp");
                    dispatcher.forward(request, response);
                }
                else {

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentAddress.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**visualizza la pagine per la modifica dei dati**/
            case "/showDataClient":
                account= (Account) request.getSession(false).getAttribute("account");

                if(account.isAdmin()==true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminDati.jsp");
                    dispatcher.forward(request, response);
                }
                else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentDati.jsp");
                    dispatcher.forward(request, response);
                }
                break;

                /**visualizza la pagina di modifica dati dell'admin*//**probabile stronzata */
            case "/updateAdmin":
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/admin/updateAdmin.jsp");
                dispatcher.forward(request,response);
                break;


            /** visualizza le pagine di modifica dei dati dell'utente e dell'admin **/

            case "/showUpdateUsername":
                account= (Account) request.getSession(false).getAttribute("account");

                if(account.isAdmin()==true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminEmail.jsp");
                    dispatcher.forward(request, response);
                }
                else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentUsername.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            case"/showUpdateEmail":

                account= (Account) request.getSession(false).getAttribute("account");

                if(account.isAdmin()==true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminEmail.jsp");
                    dispatcher.forward(request, response);
                }
                else{

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentEmail.jsp");
                    dispatcher.forward(request, response);

                }
                break;

            case "/showUpdatePassword":

                account= (Account) request.getSession(false).getAttribute("account");
                if(account.isAdmin()==true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminPassword.jsp");
                    dispatcher.forward(request, response);
                }

                else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentPassword.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**si visualizzano tutti gli utenti(ADMIN)**/
            case "/showAllUtent":

                ArrayList<Account> accounts=new ArrayList<>();
                try {
                    accounts=accountDao.searchAllAccount();
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("accounts",accounts);
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/admin/allUtent.jsp");
                dispatcher.forward(request,response);

                break;

            /**si visualizza il singolo utente(ADMIN)**/
            case "/showAccount" :
                int id=Integer.parseInt(request.getParameter("id"));
                try {
                    account=accountDao.searchAccountIdWithDataClientandAndress(id);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.setAttribute("accountUtent",account);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/admin/showUtent.jsp");
                dispatcher.forward(request,response);
                break;


            /**si controlla che l email sia proprio quella dell model.dao.account(controllo con ajax)**/

            case"/checkEmail":     /**nella update dai dati*/
                String email1=request.getParameter("email");

                account= (Account) request.getSession(false).getAttribute("account");
                boolean trovato=account.getEmail().equals(email1);

                if(!trovato){
                    try {
                        account=accountDao.searchAccountEmail(email1);
                        if(account!=null){
                            response.setContentType("text/plain;charset=UTF-8");
                            response.getWriter().println("Hey, sembra che l'indirizzo email corrisponda ad un  account già esistente.");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
                break;
            default:  response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        }
}
//gestioneutente