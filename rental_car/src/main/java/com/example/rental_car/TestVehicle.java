package com.example.rental_car;

import com.example.rental_car.entity.Category;
import com.example.rental_car.dao.CategoryDao;
import com.example.rental_car.dao.VehicleDao;
import com.example.rental_car.entity.Vehicle;

public class TestVehicle {
    public static void main (String[] args) {
        CategoryDao cd = new CategoryDao();
        Category c = cd.getCategoryById(2);
        VehicleDao vd = new VehicleDao();

        Vehicle v = new Vehicle("Europolis", "Cacciamali", "AA123AA", 1996, c);
        vd.saveVehicle(v);
    }
}
