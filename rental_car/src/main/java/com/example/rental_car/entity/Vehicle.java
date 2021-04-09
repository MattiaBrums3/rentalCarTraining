package com.example.rental_car.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVehicle")
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "licensePlate")
    private String license_plate;

    @Column(name = "yearOfRegistration")
    private int year_of_registration;

    @ManyToOne
    @JoinColumn(name="idCategory", nullable=false)
    private Category category;

    public Vehicle() {}

    public Vehicle(String model, String manufacturer, String license_plate, int year_of_registration, Category category) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.license_plate = license_plate;
        this.year_of_registration = year_of_registration;
        this.category = category;
    }

    public Vehicle(int id, String model, String manufacturer, String license_plate, int year_of_registration) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.license_plate = license_plate;
        this.year_of_registration = year_of_registration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLicensePlate() {
        return license_plate;
    }

    public void setLicensePlate(String license_plate) {
        this.license_plate = license_plate;
    }

    public int getYearOfRegistration() {
        return year_of_registration;
    }

    public void setYearOfRegistration(int year_of_registration) {
        this.year_of_registration = year_of_registration;
    }

    public Category getCategory() { return category; }

    public void setCategory(Category category) { this.category = category; }

    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", model=" + model + ", manufacturer=" + manufacturer + ", licensePlate=" + license_plate + ", yearOfRegistration=" + year_of_registration + "]";
    }
}
