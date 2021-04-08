package com.example.rental_car.servlet;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Locale;

import com.example.rental_car.dao.UserDao;
import com.example.rental_car.entity.User;

@WebServlet(name = "UserServlet", value = "/")
public class UserServlet extends HttpServlet {
    private UserDao userDao;

    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        try {
            switch (path) {
                case "/newUser":
                    showNewForm(request, response);
                    break;
                case "/insertUser":
                    insertUser(request, response);
                    break;
                case "/editUser":
                    showEditForm(request, response);
                    break;
                default:
                    listUsers(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ServletException {
        List<User> listUsers = userDao.getAllUsers();
        request.setAttribute("listUsers", listUsers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, IOException, ParseException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String date_of_birth = request.getParameter("date_of_birth");
        String fiscal_code = request.getParameter("fiscal_code");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        fiscal_code = fiscal_code.toUpperCase();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sd.parse(date_of_birth);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

        User new_user = new User(name, surname, sqlDate, fiscal_code, false, username, password);

        userDao.saveUser(new_user);
        response.sendRedirect("admin-homepage");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int idUser = Integer.parseInt(request.getParameter("id"));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }
}
