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
                    showCategoryForm(request, response, "insert");
                    break;
                case "/insertCategory":
                    insertEditCategory(request, response, "insert");
                    break;
                case "/editCategory":
                    showCategoryForm(request, response, "update");
                    break;
                case "/updateCategory":
                    insertEditCategory(request, response, "update");
                    break;
                case "/deleteCategory":
                    deleteCategory(request, response);
                    break;
                default:
                    listCategories(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showCategoryForm(HttpServletRequest request, HttpServletResponse response, String action)
            throws ServletException, IOException {
        if (action == "update") {
            int idCategory = Integer.parseInt(request.getParameter("id"));
            Category current_category = categoryDao.getCategoryById(idCategory);
            request.setAttribute("category", current_category);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEditCategory(HttpServletRequest request, HttpServletResponse response, String action)
            throws SQLException, IOException {
        String typology = request.getParameter("typology");

        if (action == "insert") {
            Category new_category = new Category(typology);
            categoryDao.saveCategory(new_category);
        } else if (action == "update") {
            int idCategory = Integer.parseInt(request.getParameter("id"));
            Category current_category = new Category(idCategory, typology);
            categoryDao.updateCategory(current_category);
        }

        response.sendRedirect("category");
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        HttpSession session = request.getSession();
        String msg = "";
        int idCategory = Integer.parseInt(request.getParameter("id"));

        Category category = categoryDao.getCategoryById(idCategory);

        if (category.getVehicles().isEmpty()) {
            categoryDao.deleteCategory(idCategory);
            msg = "Categoria eliminata con successo.";
        } else {
            msg = "Impossibile eliminare. Categoria utilizzata per uno o più veicoli.";
        }

        session.setAttribute("msg", msg);
        response.sendRedirect("category");
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Category> listCategories = categoryDao.getAllCategories();
        request.setAttribute("listCategories", listCategories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("category-list.jsp");
        dispatcher.forward(request, response);
    }
}
