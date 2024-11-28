package com.example.practicaltest.repository.impl;

import com.example.practicaltest.entity.Indexer;
import com.example.practicaltest.entity.Player;
import com.example.practicaltest.entity.PlayerIndex;
import com.example.practicaltest.repository.PlayerRepository;
import com.example.practicaltest.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PlayerRepositoryImpl implements PlayerRepository {

    private EntityManager entityManager;

    public PlayerRepositoryImpl() {
        this.entityManager = HibernateUtil.getSession().getEntityManagerFactory().createEntityManager(); // Get entity manager from HibernateUtil
    }

    @Override
    public List<Player> findAll() {
        return entityManager.createQuery("SELECT p FROM Player p", Player.class).getResultList();
    }

    @Override
    public void save(Player player) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(player);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new RuntimeException("Error saving player", e);
        }
    }

    @Override
    public Player findById(int playerId) {
        return entityManager.find(Player.class, playerId);
    }

    @Override
    public void delete(int playerId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Player player = findById(playerId);
            if (player != null) {
                entityManager.remove(player);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new RuntimeException("Error deleting player", e);
        }
    }

    @Override
    public void savePlayerIndex(PlayerIndex playerIndex) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(playerIndex);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new RuntimeException("Error saving player index", e);
        }
    }

    @Override
    public void deletePlayerIndex(int playerId, int indexId) {
        entityManager.createQuery("DELETE FROM PlayerIndex pi WHERE pi.player.id = :playerId AND pi.indexer.id = :indexId")
                .setParameter("playerId", playerId)
                .setParameter("indexId", indexId)
                .executeUpdate();
    }
}
