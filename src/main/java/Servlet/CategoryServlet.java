package Servlet;

import model.dao.category.SqlCategoryDao;
import model.entity.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "CategoryServlet", value = "/CategoryServlet")
public class CategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SqlCategoryDao categoryDao=new SqlCategoryDao();
        ArrayList<Category> categories=new ArrayList<>();

        try {
            categories=categoryDao.searchAllCategoryWithProducts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Category x:categories) {
            System.out.println(x);
        }
        request.setAttribute("categories",categories);
        String path="/WEB-INF/result/model.dao.category.jsp";
        RequestDispatcher dispatcher= request.getRequestDispatcher(path);
        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
