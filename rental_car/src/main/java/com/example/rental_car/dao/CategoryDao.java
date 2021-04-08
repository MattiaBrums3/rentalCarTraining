package com.example.rental_car.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.rental_car.entity.Category;
import com.example.rental_car.util.HibernateUtil;

public class CategoryDao {
    public void saveCategory(Category category) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(category);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateCategory(Category category) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(category);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCategory(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Category category = session.get(Category.class, id);
            if (category != null) {
                session.delete(category);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Category getCategoryById(int id) {
        Transaction transaction = null;
        Category category = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            category = session.get(Category.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return category;
    }

    public List<Category> getAllCategories() {
        Transaction transaction = null;
        List<Category> listOfCategories = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfCategories = session.createQuery("FROM Category").getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return listOfCategories;
    }
}
