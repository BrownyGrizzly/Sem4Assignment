package com.example.unidemo.repository.impl;

import com.example.unidemo.entity.Student;
import com.example.unidemo.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private static final String PERSISTENCE_UNIT_NAME = "UniDemoPU";
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public StudentRepositoryImpl() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }

    @Override
    public void save(Student student) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error saving student: " + e.getMessage(), e);
        }
    }

    @Override
    public Student findById(int id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void delete(int id) {
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, id);
            if (student != null) {
                entityManager.remove(student);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error deleting student: " + e.getMessage(), e);
        }
    }

    @Override
    public void update(Student student) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(student);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error updating student: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Student> findByName(String name) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s WHERE s.name LIKE :name", Student.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    // Clean up resources
    public void close() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
        if (entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
