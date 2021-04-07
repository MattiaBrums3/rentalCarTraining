package com.example.rental_car.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.rental_car.entity.User;
import com.example.rental_car.util.HibernateUtil;

public class UserDao {
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> listOfUsers = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfUsers = session.createQuery("FROM User").getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return listOfUsers;
    }
}
