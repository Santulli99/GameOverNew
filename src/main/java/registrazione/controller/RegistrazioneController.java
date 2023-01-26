package registrazione.controller;

import model.dao.account.SqlAccountDao;
import model.entity.Account;
import model.entity.Address;
import model.entity.DataClient;
import registrazione.service.RegistrazioneServiceImp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrazioneController", value = "/RegistrazioneController/*")
public class RegistrazioneController extends HttpServlet {
    private Account account;
    private RequestDispatcher dispatcher;
    private SqlAccountDao accountDao=new SqlAccountDao();

    private RegistrazioneServiceImp registrazioneServiceImp= new RegistrazioneServiceImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";


        switch (path){
            case "/":
                break;
            case "/registrazione":
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/client/registrazione1.jsp");
                dispatcher.forward(request,response);
                break;

            /**la servlet vede se è gia presente un email con lo stesso indirizzo**/

            case "/checkEmailSign":

                String email=request.getParameter("email");

                try {
                    account= accountDao.searchAccountEmail(email);

                    if(account!=null){


                        response.setContentType("text/plain;charset=UTF-8");
                        response.getWriter().println("Hey, sembra che l’indirizzo email corrisponda ad un account già esistente.");


                    }

                }
                catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";


        switch (path){
            case "/":
                break;
            case "/registrazione":
                boolean registrazione;
                Account account=registrazioneServiceImp.registrazioneAccount(request);
                System.out.println(account);
                DataClient dataClient=registrazioneServiceImp.registrazioneDataClient(request,account);
                Address address=registrazioneServiceImp.registrazioneAddressClient(request,account);
                if(dataClient!=null && address!=null){
                    registrazione = true;
                    request.setAttribute("registrazione", registrazione);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                    dispatcher.forward(request, response);
                }
                else {
                    boolean errore_dati=true;
                    request.setAttribute("errore_dati",errore_dati);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/registrazione1.jsp");
                    dispatcher.forward(request, response);
                }
                break;
        }
    }

}
