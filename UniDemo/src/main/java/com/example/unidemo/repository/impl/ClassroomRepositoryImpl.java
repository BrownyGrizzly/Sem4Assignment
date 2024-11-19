package com.example.unidemo.repository.impl;

import com.example.unidemo.entity.Classroom;
import com.example.unidemo.repository.ClassroomRepository;
import com.example.unidemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClassroomRepositoryImpl implements ClassroomRepository {
    //CRUD operations
    @Override
    public List<Classroom> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Classroom", Classroom.class).list();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(Classroom classroom) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(classroom);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Classroom findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Classroom.class, id);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Classroom classroom = session.get(Classroom.class, id);
            if (classroom != null) {
                session.delete(classroom);
                transaction.commit();
            }
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Classroom classroom) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(classroom);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    //Advanced operations
    @Override
    public List<Classroom> findByName(String name) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Classroom where name like %:name%", Classroom.class)
                    .setParameter("name", name)
                    .list();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
