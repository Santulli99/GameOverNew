package autenticazione.controller;

import autenticazione.service.AutenticazioneService;
import autenticazione.service.AutenticazioneServiceImp;
import model.entity.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * implementa il controller che si occupa  del sottosistema autenticazione
 *
 * @author Andrea Santulli
 * @see HttpServlet fornisce l'interfaccia per creare una servlet
 */
//ciao
@WebServlet(name = "AutenticazioneController", value = "/AutenticazioneController/*")
public class AutenticazioneController extends HttpServlet {


    private HttpSession session;
    private RequestDispatcher dispatcher;
    private Account account = new Account();
    private AutenticazioneService autenticazioneService = new AutenticazioneServiceImp();


    /**
     * Il metodo doPost  seleziona in base allo switch quale istruzioni eseguire
     *
     * @param request  oggetto della servlet, che contiene i parametri inviati e la sessione corrente
     * @param response oggetto della servlet, che contiene i parametri della risposta
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     * @throws IOException      Un'eccezione lanciata quando si verifica un errore I/O
     */

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";

        switch (path) {
            case "/":
                break;
            case "/login":
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                session = request.getSession(true);
                account = autenticazioneService.login(email, password);

                if (account == null) {
                    boolean login = false;
                    request.setAttribute("login", login);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                    dispatcher.forward(request, response);
                }
                session.setAttribute("account", account);

                if (autenticazioneService.verificaVenditore(account)) {
                    dispatcher = request.getRequestDispatcher("/HomePageController/homePageAdmin");
                    dispatcher.forward(request, response);
                } else {
                    dispatcher = request.getRequestDispatcher("/HomePageController/homePageUtent");
                    dispatcher.forward(request, response);

                }
                break;
        }
    }

    /**
     * Il metodo doGet  seleziona in base allo switch quale istruzioni eseguire
     *
     * @param request  Oggetto della servlet, che contiene i parametri inviati e la sessione corrente
     * @param response Oggetto della servlet, che contiene i parametri della risposta
     * @throws ServletException Un'eccezione lanciata quando si verifica un errore nella servlet
     * @throws IOException      Un'eccezione lanciata quando si verifica un errore I/O
     */

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/";


        switch (path) {
            case "/":
                break;
            case "/login":
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                dispatcher.forward(request, response);
                break;
            case "/logout":
                autenticazioneService.logout(session);
                dispatcher = request.getRequestDispatcher("/WEB-INF/views/guest/home.jsp");
                dispatcher.forward(request, response);
                break;
        }
    }
}
