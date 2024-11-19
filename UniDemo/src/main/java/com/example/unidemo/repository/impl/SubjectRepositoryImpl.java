package com.example.unidemo.repository.impl;

import com.example.unidemo.entity.Subject;
import com.example.unidemo.repository.SubjectRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectRepositoryImpl implements SubjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Subject> findAll() {
        return entityManager.createQuery("SELECT s FROM Subject s", Subject.class).getResultList();
    }

    @Override
    public void save(Subject subject) {
        if (subject.getId() == 0) {
            entityManager.persist(subject);
        } else {
            entityManager.merge(subject);
        }
    }

    @Override
    public Subject findById(int id) {
        return entityManager.find(Subject.class, id);
    }

    @Override
    public void delete(int id) {
        Subject subject = findById(id);
        if (subject != null) {
            entityManager.remove(subject);
        }
    }

    @Override
    public void update(Subject subject) {
        entityManager.merge(subject);
    }

    @Override
    public List<Subject> findByName(String name) {
        return entityManager.createQuery("SELECT s FROM Subject s WHERE s.name = :name", Subject.class)
                .setParameter("name", name)
                .getResultList();
    }
}
