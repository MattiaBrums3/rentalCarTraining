package com.example.rental_car;

import com.example.rental_car.entity.Category;
import com.example.rental_car.dao.CategoryDao;
import com.example.rental_car.dao.VehicleDao;
import com.example.rental_car.entity.Vehicle;

public class TestVehicle {
    public static void main (String[] args) {
        CategoryDao cd = new CategoryDao();
        Category c = cd.getCategoryById(1);
        VehicleDao vd = new VehicleDao();

        Vehicle v = new Vehicle("488 Pista", "Ferrari", "GA234EF", 2020, c);
        vd.saveVehicle(v);
    }
}
