package com.example.rental_car.servlet;

import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import com.example.rental_car.dao.UserDao;
import com.example.rental_car.dao.VehicleDao;
import com.example.rental_car.dao.RentalDao;
import com.example.rental_car.entity.User;
import com.example.rental_car.entity.Vehicle;
import com.example.rental_car.entity.Rental;

@WebServlet(name = "RentalServlet", urlPatterns = {"/rental", "/rentals", "/newRental", "/insertRental", "/editRental", "/updateRental", "/deleteRental"})
public class RentalServlet extends HttpServlet {
    private VehicleDao vehicleDao;
    private UserDao userDao;
    private RentalDao rentalDao;

    public void init() {
        vehicleDao = new VehicleDao();
        userDao = new UserDao();
        rentalDao = new RentalDao();
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
                case "/newRental":
                    //showRentalForm(request, response, "insert");
                    break;
                case "/insertRental":
                    //insertEditRental(request, response, "insert");
                    break;
                case "/editRental":
                    //showRentalForm(request, response, "update");
                    break;
                case "/updateRental":
                    //insertEditRental(request, response, "update");
                    break;
                case "/deleteRental":
                    //deleteRental(request, response);
                    break;
                case "/rentals":
                    listUserRentals(request, response);
                    break;
                default:
                    listRentals(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUserRentals(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int idUser = Integer.parseInt(request.getParameter("id"));
        List<Rental> listRentals = rentalDao.getRentalByUserId(idUser);
        request.setAttribute("listRentals", listRentals);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rental-list.jsp");
        dispatcher.forward(request, response);
    }

    private void listRentals(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Rental> listRentals = rentalDao.getAllRentals();
        request.setAttribute("listRentals", listRentals);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rental-list.jsp");
        dispatcher.forward(request, response);
    }
}
