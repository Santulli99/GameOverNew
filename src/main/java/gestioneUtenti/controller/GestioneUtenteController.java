package gestioneUtenti.controller;

import gestioneUtenti.service.GestioneUtenteService;
import gestioneUtenti.service.GestioneUtenteServiceImp;
import model.entity.Account;
import validate.ValidateForm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * implementa il controller che si occupa  del sottosistema GestioneUtente
 *
 * @author Andrea Serpico
 * @see HttpServlet fornisce l'interfaccia per creare una servlet
 */


@WebServlet(name = "GestioneUtenteController", value = "/GestioneUtenteController/*")
public class GestioneUtenteController extends HttpServlet {
    private Account account;
    private RequestDispatcher dispatcher;
    private boolean modifica;
    private boolean modificaEmail;
    private boolean trovato;
    private boolean esiste;
    private final GestioneUtenteService gestioneUtenteService = new GestioneUtenteServiceImp();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/":
                break;
            /**si effettua la modifica dell'username**/
            case "/updateUsername":
                ValidateForm validateForm3=new ValidateForm();
                String newUsername = request.getParameter("username");
                String username2 = request.getParameter("username2");
                boolean x = validateForm3.validateUsername(newUsername);

                if (newUsername.equals(username2) && (x == true)) {
                    account = (Account) request.getSession(false).getAttribute("account");
                    System.out.println("PASSWORD:" + account.getPassword());
                    account.setUsername(newUsername);
                    gestioneUtenteService.ModificaDatiAccount(account);
                    modifica = true;
                    request.setAttribute("modificaUsername", modifica);
                    request.getSession(false).setAttribute("account", account);
                    if (account.isVenditore()) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    if (account.isVenditore()) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminUsername.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentUsername.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                break;

            /**si effettua la modifica dell'email**/
            case "/updateEmail":
                ValidateForm validateForm=new ValidateForm();

                account = (Account) request.getSession(false).getAttribute("account");
                String newEmail = request.getParameter("email");
                String newEmail2 = request.getParameter("email1");
                boolean validato_email = validateForm.validateEmail(newEmail);
                boolean validato1_email = validateForm.searchEmail(newEmail2);
                if ((validato_email == true) && (validato1_email || account.getEmail().equals(newEmail)) && (newEmail.equals(newEmail2))) {
                    account.setEmail(newEmail);
                    gestioneUtenteService.ModificaDatiAccount(account);
                    modificaEmail = true;
                    request.setAttribute("modificaEmail", modificaEmail);
                    if (account.isVenditore()) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    esiste = true;
                    request.setAttribute("esiste", esiste);
                    if (account.isVenditore()) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminEmail.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentEmail.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                break;

            /**si effettua la modifica della password**/
            case "/updatePassword":
                ValidateForm validateForm1=new ValidateForm();
                String newPassword = request.getParameter("password");
                String newPassword2 = request.getParameter("password2");
                boolean b = validateForm1.validatePassword(newPassword);

                if (newPassword.equals(newPassword2) && (b == true)) {
                    account = (Account) request.getSession(false).getAttribute("account");
                    account.setPassword(newPassword);
                    gestioneUtenteService.ModificaPasswordAccount(account);
                    modifica = true;
                    request.setAttribute("modificaPassword", modifica);
                    request.getSession(false).setAttribute("account", account);
                    if (account.isVenditore()) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    modifica = false;
                    request.setAttribute("error", modifica);
                    if (account.isVenditore()) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminPassword.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentPassword.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                break;

            /**vengono aggiornati i dati**/
            case "/updateDati":
                ValidateForm validateForm2=new ValidateForm();
                account = (Account) request.getSession(false).getAttribute("account");
                String nome = request.getParameter("nome");
                boolean a = validateForm2.validateNome(nome);

                String cognome = request.getParameter("cognome");
                boolean d = validateForm2.validateCognome(cognome);


                if (a && d) {
                    account.setFirstName(nome);
                    account.setLastName(cognome);

                    gestioneUtenteService.ModificaDatiAccount(account);
                    modifica = true;
                    request.setAttribute("modificaDati", modifica);
                    request.getSession(false).setAttribute("account", account);

                    if (account.isVenditore() == true) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    modifica = false;
                    request.setAttribute("error", modifica);
                    if (account.isVenditore() == true) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminDati.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentDati.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";
        switch (path) {
            case "/":
                break;

            /**visualizza il profilo dell'Admin**/
            case "/showAccountAdmin":
                account = (Account) request.getSession(false).getAttribute("account");
                account = gestioneUtenteService.getAccount(account.getId());
                request.setAttribute("account", account);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                dispatcher.forward(request, response);
                break;

            /**visualizza il profilo dell'Utente**/
            case "/showAccountUtent":
                account = (Account) request.getSession(false).getAttribute("account");
                account = gestioneUtenteService.getAccount(account.getId());
                modificaEmail = false;
                modifica = false;
                request.setAttribute("modificaEmail", modificaEmail);
                request.setAttribute("modifica", modifica);
                request.setAttribute("account", account);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                dispatcher.forward(request, response);
                break;


            /**visualizza la pagine per la modifica dei dati**/
            case "/showDataClient":
                account = (Account) request.getSession(false).getAttribute("account");
                if (account.isVenditore() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminDati.jsp");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentDati.jsp");
                    dispatcher.forward(request, response);
                }
                break;



            /** visualizza le pagine di modifica dei dati dell'utente e dell'admin **/

            case "/showUpdateUsername":
                account = (Account) request.getSession(false).getAttribute("account");

                if (account.isVenditore() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminUsername.jsp");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentUsername.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            case "/showUpdateEmail":

                account = (Account) request.getSession(false).getAttribute("account");

                if (account.isVenditore() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminEmail.jsp");
                    dispatcher.forward(request, response);
                } else {

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentEmail.jsp");
                    dispatcher.forward(request, response);

                }
                break;

            case "/showUpdatePassword":
                account = (Account) request.getSession(false).getAttribute("account");
                if (account.isVenditore() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminPassword.jsp");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentPassword.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**si visualizzano tutti gli utenti(ADMIN)**/
            case "/showAllUtent":

                ArrayList<Account> accounts = new ArrayList<>();
                accounts = gestioneUtenteService.getAllAccount();
                request.setAttribute("accounts", accounts);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/allUtent.jsp");
                dispatcher.forward(request, response);
                break;

            /**si visualizza il singolo utente(ADMIN)**/
            case "/showAccount":
                int id = Integer.parseInt(request.getParameter("id"));
                account = gestioneUtenteService.getAccount(id);
                request.setAttribute("accountUtent", account);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showUtent.jsp");
                dispatcher.forward(request, response);
                break;

            /**si controlla che l email sia proprio quella dell model.dao.account(controllo con ajax)**/
            case "/checkEmail":     /**nella update dai dati*/
                String email1 = request.getParameter("email");
                account = (Account) request.getSession(false).getAttribute("account");
                trovato = account.getEmail().equals(email1);
                if (!trovato) {
                    account = gestioneUtenteService.getAccountEmail(email1);
                    if (account != null) {
                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println("Hey, sembra che l'indirizzo email corrisponda ad un  account già esistente.");
                    }
                }
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}