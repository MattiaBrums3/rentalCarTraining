package com.example.rental_car.servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

import com.example.rental_car.dao.UserDao;
import com.example.rental_car.dao.VehicleDao;
import com.example.rental_car.dao.RentalDao;
import com.example.rental_car.entity.Category;
import com.example.rental_car.entity.User;
import com.example.rental_car.entity.Vehicle;
import com.example.rental_car.entity.Rental;

@WebServlet(name = "RentalServlet", urlPatterns = {"/rental", "/rentals", "/newRental", "/insertRental", "/editRental", "/updateRental", "/approveRental", "/deleteRental"})
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
                    showRentalForm(request, response, "insert");
                    break;
                case "/insertRental":
                    insertEditRental(request, response, "insert");
                    break;
                case "/editRental":
                    showRentalForm(request, response, "update");
                    break;
                case "/updateRental":
                    insertEditRental(request, response, "update");
                    break;
                case "/approveRental":
                    insertEditRental(request, response, "approve");
                    break;
                case "/deleteRental":
                    deleteRental(request, response);
                    break;
                case "/rentals":
                    listUserRentals(request, response);
                    break;
                default:
                    listRentals(request, response);
                    break;
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }

    private void showRentalForm(HttpServletRequest request, HttpServletResponse response, String action)
            throws ServletException, IOException {
        if (action == "update") {
            int idRental = Integer.parseInt(request.getParameter("id"));
            Rental current_rental = rentalDao.getRentalById(idRental);
            request.setAttribute("rental", current_rental);
        }

        int idVehicle = Integer.parseInt(request.getParameter("idV"));
        Vehicle vehicle = vehicleDao.getVehicleById(idVehicle);
        request.setAttribute("vehicle", vehicle);

        List<Vehicle> listVehicles = vehicleDao.getAllVehicles();
        request.setAttribute("listVehicles", listVehicles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("rental-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEditRental(HttpServletRequest request, HttpServletResponse response, String action)
            throws SQLException, IOException, ParseException {
        if (action == "approve") {
            int idRental = Integer.parseInt(request.getParameter("id"));
            Boolean approved = Boolean.parseBoolean(request.getParameter("approved"));

            Rental rental = rentalDao.getRentalById(idRental);

            if (approved == true) {
                Rental updated_rental = new Rental(rental.getId(), rental.getUser(), rental.getVehicle(), rental.getDateOfStart(), rental.getDateOfEnd(), true);
                rentalDao.updateRental(updated_rental);
            } else {
                rentalDao.deleteRental(idRental);
            }
        } else {
            HttpSession session = request.getSession();
            String msg = "";
            String date_of_start = request.getParameter("date_of_start");
            String date_of_end = request.getParameter("date_of_end");
            int idVehicle = Integer.parseInt(request.getParameter("vehicle"));

            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            Date date_s = sd.parse(date_of_start);
            java.sql.Date sqlDate_s = new java.sql.Date(date_s.getTime());
            Date date_e = sd.parse(date_of_end);
            java.sql.Date sqlDate_e = new java.sql.Date(date_e.getTime());
            System.out.println("prova1");
            if (sqlDate_s.getTime() > sqlDate_e.getTime()) {
                msg="Data di Inizio maggiore di Data di Fine.";
                System.out.println("prova2");

                session.setAttribute("msg", msg);
                response.sendRedirect("user");
                return;
            }

            User user = userDao.getUserById((int)session.getAttribute("id"));
            Vehicle vehicle = vehicleDao.getVehicleById(idVehicle);

            if (action == "insert") {
                Rental new_rental = new Rental(user, vehicle, sqlDate_s, sqlDate_e, false);
                rentalDao.saveRental(new_rental);
            } else if (action == "update") {
                int idRental = Integer.parseInt(request.getParameter("id"));
                Rental current_rental = new Rental(idRental, user, vehicle, sqlDate_s, sqlDate_e, false);
                rentalDao.updateRental(current_rental);
            }
        }

        response.sendRedirect("user");
    }

    private void deleteRental(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        String msg = "";
        int idRental = Integer.parseInt(request.getParameter("id"));

        rentalDao.deleteRental(idRental);
        msg="Prenotazione eliminata con successo.";

        session.setAttribute("msg", msg);
        response.sendRedirect("user");
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
