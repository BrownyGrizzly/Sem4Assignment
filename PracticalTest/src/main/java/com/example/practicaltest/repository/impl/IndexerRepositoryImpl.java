package com.example.practicaltest.repository.impl;

import com.example.practicaltest.entity.Indexer;
import com.example.practicaltest.repository.IndexerRepository;
import com.example.practicaltest.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class IndexerRepositoryImpl implements IndexerRepository {

    @Override
    public List<Indexer> findAll() {
        try (Session session = HibernateUtil.getSession().getFactory().openSession()) {
            return session.createQuery("from Indexer", Indexer.class).list();
        }
    }

    @Override
    public Indexer findById(int id) {
        try (Session session = HibernateUtil.getSession().getFactory().openSession()) {
            return session.get(Indexer.class, id);
        }
    }
}

