package com.example.rental_car.dao;

import java.util.List;

import com.example.rental_car.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.rental_car.entity.Rental;
import com.example.rental_car.util.HibernateUtil;

public class RentalDao {
    public void saveRental(Rental rental) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(rental);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateRental(Rental rental) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(rental);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteRental(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Rental rental = session.get(Rental.class, id);
            if (rental != null) {
                session.delete(rental);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Rental getRentalById(int id) {
        Transaction transaction = null;
        Rental rental = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            rental = session.get(Rental.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return rental;
    }

    public List<Rental> getRentalByUserId(int idUser) {
        Transaction transaction = null;
        List<Rental> rentals = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User user = session.get(User.class, idUser);
            rentals = user.getRentals();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return rentals;
    }

    public List<Rental> getAllRentals() {
        Transaction transaction = null;
        List<Rental> listOfRentals = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfRentals = session.createQuery("FROM Rental").getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return listOfRentals;
    }
}
