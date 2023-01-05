package gestioneUtenti;

import gestioneUtenti.service.GestioneUtenteService;
import gestioneUtenti.service.GestioneUtenteServiceImp;
import model.dao.dataClient.SqlDataClientDao;
import model.entity.Account;
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

@WebServlet(name = "DataClientServlet", value = "/DataClientServlet/*")
public class DataClientServlet extends HttpServlet {
    private RequestDispatcher dispatcher;
    private SqlDataClientDao sqlDataClientDao=new SqlDataClientDao();
    private DataClient dataClient;
    private boolean modifica;
    private Account account;

    private final GestioneUtenteService gestioneUtenteService = new GestioneUtenteServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";


        switch (path) {

            case "/":
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

            /**verifica se il numero di telefono è gia presente nel database**/
            case "/checkTelSign":
                String numero=request.getParameter("numeroTel");

                try {
                    dataClient= sqlDataClientDao.searchDataClientWithTel(numero);

                    if(dataClient!=null){


                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println("Hey, sembra che il numero di telefono corrisponda a un model.dao.account già esistente.");


                    }

                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;




            /**verifica se il codice fiscale è gia presente nel database**/
            case"/checkCFSign":
                String cf=request.getParameter("cf");

                try {
                    dataClient= sqlDataClientDao.searchDataClientWithCf(cf);


                    if(dataClient!=null){


                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println("Hey, sembra che il codice fiscale corrisponda a un model.dao.account già esistente.");


                    }

                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                break;

            /**verifica se il tel è proprio quello dell model.dao.account **/


            case "/checkTel":
                String tel=request.getParameter("numeroTel");

                account= (Account) request.getSession(false).getAttribute("account");
                DataClient dataClient1;
                boolean trovato=account.getDataClient().getCell().equals(tel);


               if(!trovato){
                             try {
                                  dataClient1=sqlDataClientDao.searchDataClientWithTel(tel);
                                    if(dataClient1!=null){
                                        response.setContentType("text/plain;charset=UTF-8");
                                        response.getWriter().println("Hey, sembra che il numero di telefono corrisponda a un model.dao.account già esistente.");
                                    }
                                } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                             }

                    }


                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";


        switch (path) {

            case "/":
                break;


            /**vengono aggiornati i dati**/
            case "/updateDati":
                Account account = (Account) request.getSession(false).getAttribute("account");

                String nome = request.getParameter("nome");
                boolean a= ValidateForm.validateNome(nome);



                String cell  =request.getParameter("telefono");
                boolean b=ValidateForm.validateTelefono(cell);
                boolean c=ValidateForm.searchTelefono(cell);
                boolean esiste;


                if(a && b && (c || account.getDataClient().getCell().equals(cell)) ) {

                    dataClient = new DataClient();
                    dataClient.setAccount(account);
                    dataClient.setFirstName(nome);
                    dataClient.setLastName(account.getDataClient().getLastName());
                    dataClient.setCity(account.getDataClient().getCity());
                    dataClient.setCell(cell);
                    dataClient.setDate(account.getDataClient().getDate());
                    dataClient.setCf(account.getDataClient().getCf());

                    gestioneUtenteService.ModificaDatiAnagrafici(dataClient,account);
                    /*try {
                        sqlDataClientDao.updateDataClient(dataClient, account);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }*/
                    modifica=true;
                    request.setAttribute("modificaDati",modifica);

                    account.setDataClient(dataClient);
                    request.getSession(false).setAttribute("account", account);

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
                    modifica=false;
                    request.setAttribute("error",modifica);
                    if(account.isAdmin()==true) {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/updateAdminDati.jsp");
                        dispatcher.forward(request, response);

                    }
                    else {

                        dispatcher = request.getRequestDispatcher("/WEB-INF/views/user/updateUtentDati.jsp");
                        dispatcher.forward(request, response);
                    }

                }
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
