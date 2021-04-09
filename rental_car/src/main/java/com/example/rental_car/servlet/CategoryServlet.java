package com.example.rental_car.servlet;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import com.example.rental_car.dao.CategoryDao;
import com.example.rental_car.entity.Category;

@WebServlet(name = "CategoryServlet", urlPatterns = {"/category", "/newCategory", "/insertCategory", "/editCategory", "/updateCategory", "/deleteCategory"})
public class CategoryServlet extends HttpServlet {
    private CategoryDao categoryDao;

    public void init() { categoryDao = new CategoryDao(); }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        try {
            switch (path) {
                case "/newCategory":
                    //ripartire da qui
                    break;
                default:
                    listCategories(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Category> listCategories = categoryDao.getAllCategories();
        request.setAttribute("listCategories", listCategories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category-list.jsp");
        dispatcher.forward(request, response);
    }
}
