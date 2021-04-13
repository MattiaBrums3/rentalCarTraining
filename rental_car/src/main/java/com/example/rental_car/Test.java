package com.example.rental_car;

import com.example.rental_car.dao.CategoryDao;
import com.example.rental_car.dao.RentalDao;
import com.example.rental_car.dao.UserDao;
import com.example.rental_car.dao.VehicleDao;
import com.example.rental_car.entity.Category;
import com.example.rental_car.entity.Rental;
import com.example.rental_car.entity.User;
import com.example.rental_car.entity.Vehicle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main (String[] args) throws ParseException {
        UserDao ud = new UserDao();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");

        //ADMIN User
        String s1 = "1996/04/03";
        Date date1 = sd.parse(s1);

        User su = new User("Mattia","Brumana",date1,"BRMMTT96D03A794E",true,"m.brumana","admin");
        ud.saveUser(su);

        //CUSTOMER User
        String s2 = "1994/04/02";
        Date date2 = sd.parse(s2);

        User u = new User("Marco","Verdi",date1,"VRDMRC94D02A794G",false,"m.verdi","fbrss");
        ud.saveUser(u);

        //Categories
        CategoryDao cd = new CategoryDao();

        Category c = new Category("Automobile");
        cd.saveCategory(c);
        Category c2 = new Category("Motocicletta");
        cd.saveCategory(c2);

        //Vehicles
        VehicleDao vd = new VehicleDao();

        Vehicle v = new Vehicle("488 Pista", "Ferrari", "GA234EF", 2020, c);
        vd.saveVehicle(v);
        Vehicle v2 = new Vehicle("999", "Ducati", "BG12345", 2015, c2);
        vd.saveVehicle(v2);

        //Rentals
        String ss = "2020/04/01";
        String se = "2020/05/31";
        Date date_start = sd.parse(ss);
        Date date_end = sd.parse(se);

        RentalDao rd = new RentalDao();
        Rental r = new Rental(u, v, date_start, date_end, false);
        rd.saveRental(r);

        String ss2 = "2020/07/01";
        String se2 = "2020/09/30";
        Date date_start2 = sd.parse(ss2);
        Date date_end2 = sd.parse(se2);

        RentalDao rd2 = new RentalDao();
        Rental r2 = new Rental(u, v2, date_start2, date_end2, false);
        rd.saveRental(r2);
    }

}
