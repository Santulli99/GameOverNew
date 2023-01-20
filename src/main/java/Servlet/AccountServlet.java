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
            /** visualizza la pagina di registrazione **/

            case "/registrazione":
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/client/registrazione1.jsp");
                dispatcher.forward(request,response);
                break;

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

            /**la servlet vede se è gia presente un email con lo stesso indirizzo**/

            case "/checkEmailSign":

                String email=request.getParameter("email");

                try {
                   account= accountDao.searchAccountEmail(email);

                   if(account!=null){


                       response.setContentType("text/plain;charset=UTF-8");
                       response.getWriter().println("Hey, sembra che l’indirizzo email corrisponda a un model.dao.account già esistente.");


                   }

                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            /**si controlla che l email sia proprio quella dell model.dao.account(controllo con ajax)**/

            case"/checkEmail":     /**nella update dai dati*/
                String email1=request.getParameter("email");

                account= (Account) request.getSession(false).getAttribute("account");
                boolean trovato=account.getEmail().equals(email1);


                if(!trovato){
                    try {
                        account=sqlAccountDao2.searchAccountEmail(email1);
                        if(account!=null){
                            response.setContentType("text/plain;charset=UTF-8");
                            response.getWriter().println("Hey, sembra che l'indirizzo email corrisponda a un model.dao.account già esistente.");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

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



            /** visualizza la homepage dell'Admin **/

            case "/showAdmin":   dispatcher =request.getRequestDispatcher("/WEB-INF/views/admin/admin.jsp");
                dispatcher.forward(request,response);
                break;


            /**visualizza la pagina del login**/
            case "/login":


                dispatcher =request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                dispatcher.forward(request,response);
                break;
            /**visualizza la homepage**/
            case "/homepage":


                dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/home.jsp");
                dispatcher.forward(request, response);



                break;

            /** visualizza la homepage dell'Utente **/
            case "/utente":

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/utente.jsp");
                        dispatcher.forward(request, response);

                break;

            /**ritorna alla homepage**/
            case "/logout":
                response.sendRedirect("http://localhost:8080/GameOver_war_exploded/AccountServlet/homepage");

                break;

            /**visualizza il profilo dell'Admin**/
            case "/showAccountAdmin":

                Account account1= (Account) request.getSession(false).getAttribute("account");

                try {
                    account= accountDao.searchAccountIdWithDataClientandAndress(account1.getId());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.setAttribute("account",account);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/admin/showProfileAdmin.jsp");
                dispatcher.forward(request,response);
                break;

            /**visualizza il profilo dell'Utente**/
            case "/showAccountUtent":

                Account account2= (Account) request.getSession(false).getAttribute("account");

                try {
                    account= accountDao.searchAccountIdWithDataClientandAndress(account2.getId());
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



            default:  response.sendError(HttpServletResponse.SC_NOT_FOUND);

        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";

        switch (path){

            case "/":
                break;

            /**si effettua la registrazione**/
            case "/registrazione":

                Account newaccount=new Account();

                boolean registrazione;

                /*model.dao.account*/
                String email1=request.getParameter("email");
                String emailConferma=request.getParameter("email1");
                boolean validato1=ValidateForm.validateEmail(email1);
                boolean validato1_1=ValidateForm.searchEmail(email1);
                if((validato1==true) && (validato1_1==true))
                    request.setAttribute("email",email1);


                boolean validato2=ValidateForm.confermaEmail(email1,emailConferma);
                if(validato2==true)
                    request.setAttribute("email1",emailConferma);


                String password1=request.getParameter("password");
                String passwordConferma=request.getParameter("password1");
                boolean validato3=ValidateForm.validatePassword(password1);
                if(validato3==true)
                    request.setAttribute("password",password1);
                boolean validato4=ValidateForm.confermaPassword(passwordConferma,password1);
                if(validato4==true)
                    request.setAttribute("password1",passwordConferma);

                String usernam=request.getParameter("username");
                boolean validato5=ValidateForm.validateUsername(usernam);
                if(validato5==true)
                    request.setAttribute("username",usernam);


                /*model.dao.dataClient*/
                String nome=request.getParameter("nome");
                boolean validato6=ValidateForm.validateNome(nome);
                if(validato6==true)
                    request.setAttribute("nome",nome);

                String cognome=request.getParameter("cognome");
                boolean validato7=ValidateForm.validateCognome(cognome);
                if(validato7==true)
                    request.setAttribute("cognome",cognome);  /*mi serviva per non perdere i campi gia immessi dopo aver premuto il tasto registrazione*/

                String telefono=request.getParameter("telefono");
                boolean validato8=ValidateForm.validateTelefono(telefono);
                boolean validato8_1=ValidateForm.searchTelefono(telefono);
                if((validato8==true)&& (validato8_1==true))
                    request.setAttribute("telefono",telefono);


                String cittaNascita=request.getParameter("cittaNascita");
                boolean validato9=ValidateForm.validateluogo_nascita(cittaNascita);
                if(validato9==true)
                    request.setAttribute("cittaNascita",cittaNascita);



                LocalDate dataNascita= LocalDate.parse(request.getParameter("dataNascita"));
                boolean validato10=ValidateForm.validateDateNascita(dataNascita);
                if(validato10==true)
                    request.setAttribute("dataNascita",dataNascita);



                String cf=request.getParameter("codiceFiscale");
                boolean validato11=ValidateForm.validateCf(cf);
                boolean validato11_1=ValidateForm.searchCf(cf);
                if((validato11==true) && (validato11_1==true))
                    request.setAttribute("codiceFiscale",cf);


                /*model.dao.address*/

                String citta=request.getParameter("citta");
                boolean validato12=ValidateForm.validateResidenza(citta);
                if(validato12==true)
                    request.setAttribute("citta",citta);

                String provincia=request.getParameter("provincia");
                boolean validato13=ValidateForm.validateProvincia(provincia);
                if(validato13==true)
                    request.setAttribute("provincia",provincia);



                String via=request.getParameter("via");
                boolean validato14=ValidateForm.validateVia(via);
                if(validato14==true)
                    request.setAttribute("via",via);


                int civico= Integer.parseInt(request.getParameter("civico"));
                boolean validato15=ValidateForm.validateCivico(civico);
                if(validato15==true)
                    request.setAttribute("civico",civico);


                int cap= Integer.parseInt(request.getParameter("cap"));
                boolean validato16=ValidateForm.validateCap(String.valueOf(cap));
                if(validato16==true)
                    request.setAttribute("cap",cap);


                if(validato1 && validato2 && validato3 && validato4 && validato5 && validato6 && validato7
                        && validato8 && validato9 && validato10 && validato11 && validato12 && validato13
                        && validato14 && validato15 && validato16 &&(validato1_1) && (validato8_1)
                && (validato11_1)) {


                    newaccount.setEmail(email1);
                    newaccount.setPassword(password1);
                    newaccount.setUsername(usernam);
                    newaccount.setAdmin(false);

                    try {
                        accountDao.createAccount(newaccount);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    dataClient.setAccount(newaccount);
                        dataClient.setFirstName(nome);
                        dataClient.setLastName(cognome);
                        dataClient.setCell(telefono);
                        dataClient.setCity(cittaNascita);
                        dataClient.setDate(dataNascita);
                        dataClient.setCf(cf);

                    try {
                        sqlDataClientDao.createDataClient(dataClient);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    address.setAccount(newaccount);
                        address.setCity(citta);
                        address.setProvince(provincia);
                        address.setStreet(via);
                        address.setStreetNumber(civico);
                        address.setPostalCode(cap);

                    try {
                        sqlAddressDao.createAddress(address);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    registrazione = true;
                    request.setAttribute("registrazione", registrazione);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                    dispatcher.forward(request, response);
                }
                else{
                    boolean errore_dati=true;
                    request.setAttribute("errore_dati",errore_dati);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/registrazione1.jsp");
                    dispatcher.forward(request, response);
                    }



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
