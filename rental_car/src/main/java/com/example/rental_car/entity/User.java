package com.example.rental_car.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "dateOfBirth")
    private Date date_of_birth;

    @Column(name = "fiscalCode")
    private String fiscal_code;

    @Column(name = "superUser",columnDefinition = "TINYINT")
    private Boolean super_user;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Rental> rentals;

    public User() {}

    public User(String name, String surname, Date date_of_birth, String fiscal_code, Boolean super_user, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.fiscal_code = fiscal_code;
        this.super_user = super_user;
        this.username = username;
        this.password = password;
    }

    public User(int id, String name, String surname, Date date_of_birth, String fiscal_code, Boolean super_user, String username, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.fiscal_code = fiscal_code;
        this.super_user = super_user;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getFiscalCode() {
        return fiscal_code;
    }

    public void setFiscalCode(String fiscal_code) {
        this.fiscal_code = fiscal_code;
    }

    public Boolean getSuperUser() {
        return super_user;
    }

    public void setSuperUser(Boolean super_user) {
        this.super_user = super_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Rental> getRentals() { return rentals; }

    public void setRentals(List<Rental> rentals) { this.rentals = rentals; }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", dateOfBirth=" + date_of_birth + ", fiscalCode=" + fiscal_code + ", superUser=" + super_user + ", username=" + username + ", password=" + password + "]";
    }
}
