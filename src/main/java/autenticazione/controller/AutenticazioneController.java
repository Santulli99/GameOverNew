package autenticazione.controller;

import autenticazione.service.AutenticazioneService;
import autenticazione.service.AutenticazioneServiceImp;
import model.dao.account.SqlAccountDao;

import model.entity.Account;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
//prova223

@WebServlet(name = "AutenticazioneController", value = "/AutenticazioneController/*")
public class AutenticazioneController extends HttpServlet {
    private HttpSession session;
    private RequestDispatcher dispatcher;
    private SqlAccountDao accountDao=new SqlAccountDao();
    private Account account=new Account();
    private AutenticazioneService autenticazioneService=new AutenticazioneServiceImp();





    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";

        switch (path){
            case "/":
                break;
            case "/login":
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                session = request.getSession(true);
                account=autenticazioneService.login(email,password);
                //se sbaglia
                if (account == null) {
                    boolean login=false;
                    request.setAttribute("login",login);
                    dispatcher = request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                    dispatcher.forward(request, response);
                }
                session.setAttribute("account", account);
                //va alla pagina dell amministratore
                if (autenticazioneService.verificaAdmin(account)) {
                    dispatcher=request.getRequestDispatcher("/HomePageController/homePageAdmin");
                    dispatcher.forward(request,response);
                   //response.sendRedirect("/HomePageController/homePageAdmin");//aggiungere alla home page servlet
                }else {
                    //va alla pagina del cliente
                    dispatcher=request.getRequestDispatcher("/HomePageController/homePageUtent");
                    dispatcher.forward(request,response);
                    //response.sendRedirect("/HomePageController/homePageUtent");//aggiungere alla home page servlet
                }
                break;

        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";


        switch (path){
            case "/":
                break;
            case "/login":
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/client/login1.jsp");
                dispatcher.forward(request,response);
                break;
            case "/logout":
                autenticazioneService.logout(session);
                dispatcher=request.getRequestDispatcher("/WEB-INF/views/guest/home.jsp");
                dispatcher.forward(request,response);
                break;
        }
    }
}
