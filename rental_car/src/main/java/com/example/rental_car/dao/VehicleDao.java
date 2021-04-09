package com.example.rental_car.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.rental_car.entity.Vehicle;
import com.example.rental_car.util.HibernateUtil;

public class VehicleDao {
    public void saveVehicle(Vehicle vehicle) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(vehicle);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateVehicle(Vehicle vehicle) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(vehicle);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteVehicle(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Vehicle vehicle = session.get(Vehicle.class, id);
            if (vehicle != null) {
                session.delete(vehicle);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Vehicle getVehicleById(int id) {
        Transaction transaction = null;
        Vehicle vehicle = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            vehicle = session.get(Vehicle.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return vehicle;
    }

    public List<Vehicle> getAllVehicles() {
        Transaction transaction = null;
        List<Vehicle> listOfVehicles = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfVehicles = session.createQuery("FROM Vehicle").getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return listOfVehicles;
    }
}
