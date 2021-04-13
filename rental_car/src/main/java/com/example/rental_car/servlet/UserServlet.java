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

import com.example.rental_car.dao.UserDao;
import com.example.rental_car.entity.User;

@WebServlet(name = "UserServlet", urlPatterns = {"/login", "/logout", "/user", "/newUser", "/insertUser", "/editUser", "/updateUser", "/deleteUser"})
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
                    showUserForm(request, response, "insert");
                    break;
                case "/insertUser":
                    insertEditUser(request, response, "insert");
                    break;
                case "/editUser":
                    showUserForm(request, response, "update");
                    break;
                case "/updateUser":
                    insertEditUser(request, response, "update");
                    break;
                case "/deleteUser":
                    deleteUser(request, response);
                    break;
                case "/login":
                    login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                default:
                    listUsers(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response)
        throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        String msg = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.checkLogin(username, password);

        if (user == null) {
            msg = "Username o password errati.";
            session.setAttribute("msg", msg);
            response.sendRedirect("index.jsp");
        } else {
            session.setAttribute("id", user.getId());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("super", user.getSuperUser());
            response.sendRedirect("user");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();

        session.removeAttribute("id");
        session.removeAttribute("username");
        session.removeAttribute("super");
        String msg = "A presto!";
        session.setAttribute("msg", msg);
        response.sendRedirect("index.jsp");
    }

    private void showUserForm(HttpServletRequest request, HttpServletResponse response, String action)
            throws SQLException, ServletException, IOException {
        if (action == "update") {
            int idUser = Integer.parseInt(request.getParameter("id"));
            User current_user = userDao.getUserById(idUser);
            request.setAttribute("user", current_user);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEditUser(HttpServletRequest request, HttpServletResponse response, String action)
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

        if (action == "insert") {
            User new_user = new User(name, surname, sqlDate, fiscal_code, false, username, password);
            userDao.saveUser(new_user);
        } else if (action == "update") {
            int idUser = Integer.parseInt(request.getParameter("id"));
            User current_user = new User(idUser, name, surname, sqlDate, fiscal_code, false, username, password);
            userDao.updateUser(current_user);
        }

        response.sendRedirect("user");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        String msg = "";
        int idUser = Integer.parseInt(request.getParameter("id"));

        User user = userDao.getUserById(idUser);

        if (user.getRentals().isEmpty()) {
            userDao.deleteUser(idUser);
            msg="Utente eliminato con successo.";
        } else {
            msg = "Impossibile eliminare. Utente con prenotazioni approvate o in fase di approvazione.";
        }

        session.setAttribute("msg", msg);
        response.sendRedirect("user");
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUsers = userDao.getCustomerUsers();
        request.setAttribute("listUsers", listUsers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-homepage.jsp");
        dispatcher.forward(request, response);
    }
}
