package listaDesideri.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ListaDesideriController", value = "/ListaDesideriController/*")
public class ListaDesideriController extends HttpServlet {
    private RequestDispatcher dispatcher;
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";

        switch (path){
            case "/":
                break;
            case "/visualizzaListaDesideri":
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/user/listaDesideri.jsp");
                dispatcher.forward(request,response);
                break;
            case "/logout":
        }
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  path=(request.getPathInfo()!=null) ? request.getPathInfo():"/";


        switch (path){
            case "/":
                break;
            case "/visualizzaListaDesideri":
                dispatcher =request.getRequestDispatcher("/WEB-INF/views/user/listaDesideri.jsp");
                dispatcher.forward(request,response);
                break;
            case "/login":
                break;
        }
    }
}
