package Application_Logic.registrazione.controller;

import Application_Logic.gestioneUtenti.service.GestioneUtenteServiceImp;
import Application_Logic.registrazione.service.RegistrazioneServiceImp;
import Storage.account.SqlAccountDao;
import Application_Logic.entity.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * implementa il controller che si occupa  del sottosistema Registrazione
 *
 * @author Gerardo Esposito
 * @see HttpServlet fornisce l'interfaccia per creare una servlet
 */

@WebServlet(name = "RegistrazioneController", value = "/RegistrazioneController/*")
public class RegistrazioneController extends HttpServlet {
    private Account account;
    private RequestDispatcher dispatcher;
    private SqlAccountDao accountDao = new SqlAccountDao();

    private RegistrazioneServiceImp registrazioneServiceImp = new RegistrazioneServiceImp();

    private GestioneUtenteServiceImp gestioneUtenteServiceImp = new GestioneUtenteServiceImp();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";


        switch (path) {
            case "/":
                break;
            case "/Application_Logic/registrazione":
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/registrazione1.jsp");
                dispatcher.forward(request, response);
                break;
            case "/checkEmailSign":
                String email = request.getParameter("email");
                account = gestioneUtenteServiceImp.getAccountEmail(email);
                if (account != null) {
                    response.setContentType("text/plain;charset=UTF-8");
                    response.getWriter().println("Hey, sembra che l’indirizzo email corrisponda ad un account già esistente.");
                }
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";


        switch (path) {
            case "/":
                break;
            case "/Application_Logic/registrazione":
                boolean registrazione;
                Account account = registrazioneServiceImp.registrazioneAccount(request);
                if (account != null) {
                    registrazione = true;
                    request.setAttribute("registrazione", registrazione);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                    dispatcher.forward(request, response);
                } else {
                    boolean errore_dati = true;
                    request.setAttribute("errore_dati", errore_dati);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/registrazione1.jsp");
                    dispatcher.forward(request, response);
                }
                break;
        }
    }

}
