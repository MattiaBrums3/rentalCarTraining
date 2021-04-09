package com.example.rental_car.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "cateogries")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory")
    private int id;

    @Column(name = "typology")
    private String typology;

    @OneToMany(mappedBy="category")
    private List<Vehicle> vehicles;

    public Category() {}

    public Category(String typology) {
        this.typology = typology;
    }

    public Category (int id, String typology) {
        this.id = id;
        this.typology = typology;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public List<Vehicle> getVehicles() { return vehicles; }

    public void setVehicles(List<Vehicle> vehicles) { this.vehicles = vehicles; }

    @Override
    public String toString() {
        return "Category [id=" + id + ", typology=" + typology + "]";
    }
}
