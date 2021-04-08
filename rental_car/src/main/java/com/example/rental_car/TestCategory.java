package com.example.rental_car;

import com.example.rental_car.entity.Category;
import com.example.rental_car.dao.CategoryDao;

public class TestCategory {
    public static void main (String[] args) {
        CategoryDao cd = new CategoryDao();

        Category c = new Category("Automobile");
        cd.saveCategory(c);
    }
}
