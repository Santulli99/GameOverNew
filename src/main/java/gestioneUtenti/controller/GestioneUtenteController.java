package gestioneUtenti.controller;

import gestioneUtenti.service.GestioneUtenteService;
import gestioneUtenti.service.GestioneUtenteServiceImp;
import model.dao.account.SqlAccountDao;
import model.dao.address.SqlAddressDao;
import model.dao.dataClient.SqlDataClientDao;
import model.entity.Account;
import model.entity.Address;
import model.entity.DataClient;
import validate.ValidateForm;

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
    private Account account;
    private RequestDispatcher dispatcher;
    private SqlAddressDao sqlAddressDao = new SqlAddressDao();
    private SqlDataClientDao sqlDataClientDao = new SqlDataClientDao();
    private Address address;
    private DataClient dataClient;
    private boolean modifica;
    private SqlAccountDao accountDao = new SqlAccountDao();
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
                String newUsername = request.getParameter("username");
                String username2 = request.getParameter("username2");
                boolean x = ValidateForm.validateUsername(newUsername);

                if (newUsername.equals(username2) && (x == true)) {
                    account = (Account) request.getSession(false).getAttribute("account");
                    System.out.println("PASSWORD:"+account.getPassword());
                    account.setUsername(newUsername);
                    gestioneUtenteService.ModificaDatiAccount(account);
                    modifica = true;
                    request.setAttribute("modificaUsername", modifica);
                    request.getSession(false).setAttribute("account", account);
                    if (account.isAdmin()) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    if (account.isAdmin()) {
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
                account = (Account) request.getSession(false).getAttribute("account");
                String newEmail = request.getParameter("email");
                String newEmail2 = request.getParameter("email1");
                boolean validato_email = ValidateForm.validateEmail(newEmail);
                boolean validato1_email = ValidateForm.searchEmail(newEmail2);
                if ((validato_email == true) && (validato1_email || account.getEmail().equals(newEmail)) && (newEmail.equals(newEmail2))) {
                    account.setEmail(newEmail);
                    gestioneUtenteService.ModificaDatiAccount(account);
                    modificaEmail = true;
                    request.setAttribute("modificaEmail", modificaEmail);
                    if (account.isAdmin()) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    esiste = true;
                    request.setAttribute("esiste", esiste);
                    if (account.isAdmin()) {
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
                String newPassword = request.getParameter("password");
                String newPassword2 = request.getParameter("password2");
                boolean b = ValidateForm.validatePassword(newPassword);

                if (newPassword.equals(newPassword2) && (b == true)) {
                    account = (Account) request.getSession(false).getAttribute("account");
                    account.setPassword(newPassword);
                    gestioneUtenteService.ModificaPasswordAccount(account);
                    modifica = true;
                    request.setAttribute("modificaPassword", modifica);
                    request.getSession(false).setAttribute("account", account);
                    if (account.isAdmin()) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    modifica = false;
                    request.setAttribute("error", modifica);
                    if (account.isAdmin()) {
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
                account = (Account) request.getSession(false).getAttribute("account");
                String nome = request.getParameter("nome");
                boolean a = ValidateForm.validateNome(nome);

                String cell = request.getParameter("telefono");
                boolean d = ValidateForm.validateTelefono(cell);
                boolean c = ValidateForm.searchTelefono(cell);

                if (a && d && (c || account.getDataClient().getCell().equals(cell))) {
                    dataClient = new DataClient();
                    dataClient.setAccount(account);
                    dataClient.setFirstName(nome);
                    dataClient.setLastName(account.getDataClient().getLastName());
                    dataClient.setCity(account.getDataClient().getCity());
                    dataClient.setCell(cell);
                    dataClient.setDate(account.getDataClient().getDate());
                    dataClient.setCf(account.getDataClient().getCf());

                    gestioneUtenteService.ModificaDatiAnagrafici(dataClient, account);

                    modifica = true;
                    request.setAttribute("modificaDati", modifica);
                    account.setDataClient(dataClient);
                    request.getSession(false).setAttribute("account", account);

                    if (account.isAdmin() == true) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    esiste = true;
                    request.setAttribute("esiste", esiste);
                    modifica = false;
                    request.setAttribute("error", modifica);
                    if (account.isAdmin() == true) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminDati.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentDati.jsp");
                        dispatcher.forward(request, response);
                    }
                }
                break;

            /**si effettua la modifica dell'indirizzo**/
            case "/updateAddress":
                String citta = request.getParameter("citta");
                boolean a1 = ValidateForm.validateResidenza(citta);
                String provincia = request.getParameter("provincia");
                boolean b1 = ValidateForm.validateProvincia(provincia);
                String via = request.getParameter("via");
                boolean c1 = ValidateForm.validateVia(via);
                int civico = Integer.parseInt(request.getParameter("civico"));
                boolean d1 = ValidateForm.validateCivico(civico);
                int cap = Integer.parseInt(request.getParameter("cap"));
                boolean e = ValidateForm.validateCap(String.valueOf(cap));
                if (a1 && b1 && c1 && d1 && e) {
                    Account account = (Account) request.getSession(false).getAttribute("account");
                    address = new Address();
                    address.setAccount(account);
                    address.setCity(citta);
                    address.setPostalCode(cap);
                    address.setProvince(provincia);
                    address.setStreet(via);
                    address.setStreetNumber(civico);

                    gestioneUtenteService.ModificaDatiIndirizzo(address);

                    modifica = true;
                    request.setAttribute("modificaAddress", modifica);
                    account.setAddress(address);
                    request.getSession(false).setAttribute("account", account);
                    if (account.isAdmin() == true) {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                        dispatcher.forward(request, response);
                    }
                } else {
                    if (account.isAdmin() == true) {
                        modifica = false;
                        request.setAttribute("error", modifica);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminAddress.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        modifica = false;
                        request.setAttribute("error", modifica);
                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentAddress.jsp");
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
                account = gestioneUtenteService.getAccountDati(account);
                request.setAttribute("account", account);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                dispatcher.forward(request, response);
                break;

            /**visualizza il profilo dell'Utente**/
            case "/showAccountUtent":
                account = (Account) request.getSession(false).getAttribute("account");
                account = gestioneUtenteService.getAccountDati(account);
                modificaEmail = false;
                modifica = false;
                request.setAttribute("modificaEmail", modificaEmail);
                request.setAttribute("modifica", modifica);
                request.setAttribute("account", account);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/showProfileUtent.jsp");
                dispatcher.forward(request, response);
                break;

            /**viene visualizzata la pagina per la modifica dell'indirizzo**/
            case "/showUpdateAddress":
                account = (Account) request.getSession(false).getAttribute("account");
                if (account.isAdmin() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminAddress.jsp");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentAddress.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**visualizza la pagine per la modifica dei dati**/
            case "/showDataClient":
                account = (Account) request.getSession(false).getAttribute("account");
                if (account.isAdmin() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminDati.jsp");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentDati.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            /**visualizza la pagina di modifica dati dell'admin*//**probabile stronzata */
            case "/updateAdmin":
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdmin.jsp");
                dispatcher.forward(request, response);
                break;


            /** visualizza le pagine di modifica dei dati dell'utente e dell'admin **/

            case "/showUpdateUsername":
                account = (Account) request.getSession(false).getAttribute("account");

                if (account.isAdmin() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminEmail.jsp");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentUsername.jsp");
                    dispatcher.forward(request, response);
                }
                break;

            case "/showUpdateEmail":

                account = (Account) request.getSession(false).getAttribute("account");

                if (account.isAdmin() == true) {
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminEmail.jsp");
                    dispatcher.forward(request, response);
                } else {

                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentEmail.jsp");
                    dispatcher.forward(request, response);

                }
                break;

            case "/showUpdatePassword":
                account = (Account) request.getSession(false).getAttribute("account");
                if (account.isAdmin() == true) {
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
                account = gestioneUtenteService.getAccountDati(account);
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

            /**verifica se il numero di telefono è gia presente nel database**/
            case "/checkTelSign":
                String numero = request.getParameter("numeroTel");
                try {
                    dataClient = sqlDataClientDao.searchDataClientWithTel(numero);
                    if (dataClient != null) {
                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println("Hey, sembra che il numero di telefono corrisponda a un model.dao.account già esistente.");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            /**verifica se il codice fiscale è gia presente nel database**/
            case "/checkCFSign":
                String cf = request.getParameter("cf");
                dataClient = gestioneUtenteService.getDataClientCf(cf);
                if (dataClient != null) {
                    response.setContentType("text/plain;charset=UTF-8");
                    response.getWriter().println("Hey, sembra che il codice fiscale corrisponda a un model.dao.account già esistente.");
                }
                break;

            /**verifica se il tel è proprio quello dell model.dao.account **/
            case "/checkTel":
                String tel = request.getParameter("numeroTel");
                account = (Account) request.getSession(false).getAttribute("account");
                trovato = account.getDataClient().getCell().equals(tel);
                if (!trovato) {
                    dataClient = gestioneUtenteService.getDataClientTel(tel);
                    if (dataClient != null) {
                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println("Hey, sembra che il numero di telefono corrisponda a un model.dao.account già esistente.");
                    }
                }
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
//gestioneutente