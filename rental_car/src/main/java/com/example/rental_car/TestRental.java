package com.example.rental_car;

import com.example.rental_car.dao.VehicleDao;
import com.example.rental_car.entity.Vehicle;
import com.example.rental_car.entity.User;
import com.example.rental_car.dao.UserDao;
import com.example.rental_car.entity.Rental;
import com.example.rental_car.dao.RentalDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestRental {
    public static void main (String[] args) throws ParseException {
        VehicleDao vd = new VehicleDao();
        Vehicle v = vd.getVehicleById(1);
        UserDao ud = new UserDao();
        User u = ud.getUserById(1);

        String s1 = "2020/04/01";
        String s2 = "2020/05/31";
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        Date date_start = sd.parse(s1);
        Date date_end = sd.parse(s2);

        RentalDao rd = new RentalDao();
        Rental r = new Rental(u, v, date_start, date_end, false);
        rd.saveRental(r);
    }
}
