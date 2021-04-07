package com.example.rental_car;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import com.example.rental_car.entity.User;
import com.example.rental_car.dao.UserDao;

public class TestUser {
    public static void main (String[] args) throws ParseException {
        UserDao ud = new UserDao();

        String s = "1994/04/02";
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = sd.parse(s);

        User u = new User("Marco","Verdi",date1,"VRDMRC94D02A794G",false,"m.verdi","fbrss");
        ud.saveUser(u);

    }
}
