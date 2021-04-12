package com.example.rental_car.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRental")
    private int id;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idVehicle")
    private Vehicle vehicle;

    @Column(name = "dateOfStart")
    private Date date_of_start;

    @Column(name = "dateOfEnd")
    private Date date_of_end;

    @Column(name = "approved")
    private Boolean approved;

    public Rental() {}

    public Rental(User user, Vehicle vehicle, Date date_of_start, Date date_of_end, Boolean approved) {
        this.user = user;
        this.vehicle = vehicle;
        this.date_of_start = date_of_start;
        this.date_of_end = date_of_end;
        this.approved = approved;
    }

    public Rental(int id, User user, Vehicle vehicle, Date date_of_start, Date date_of_end, Boolean approved) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.date_of_start = date_of_start;
        this.date_of_end = date_of_end;
        this.approved = approved;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getDateOfStart() {
        return date_of_start;
    }

    public void setDateOfStart(Date date_of_start) {
        this.date_of_start = date_of_start;
    }

    public Date getDateOfEnd() {
        return date_of_end;
    }

    public void setDateOfEnd(Date date_of_end) {
        this.date_of_end = date_of_end;
    }

    public Boolean getApproved() { return approved; }

    public void setApproved(Boolean approved) { this.approved = approved; }

    @Override
    public String toString() {
        return "Rental [id=" + id + ", user=" + user + ", vehicle=" + vehicle + ", dateOfStart=" + date_of_start + ", dateOfEnd=" + date_of_end + "]";
    }
}
