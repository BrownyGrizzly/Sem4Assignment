package com.examt2303m.dypham.repository.impl;

import com.examt2303m.dypham.entity.Indexer;
import com.examt2303m.dypham.entity.Player;
import com.examt2303m.dypham.entity.PlayerIndex;
import com.examt2303m.dypham.repository.PlayerIndexRepository;
import com.examt2303m.dypham.util.HIbernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PlayerIndexRepositoryImpl implements PlayerIndexRepository {
    @Override
    public List<PlayerIndex> findAll() {
        try (Session session = HIbernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from PlayerIndex ", PlayerIndex.class).list();
        }
    }

    @Override
    public PlayerIndex getPlayerIndexByPlayerAndIndexer(Player player, Indexer indexer) {
        try (Session session = HIbernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM PlayerIndex pi WHERE pi.player = :player AND pi.indexer = :indexer";

            Query<PlayerIndex> query = session.createQuery(hql, PlayerIndex.class);
            query.setParameter("player", player);
            query.setParameter("indexer", indexer);

            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching PlayerIndex: " + e.getMessage());
        }
    }

    @Override
    public void save(PlayerIndex playerIndex) {
        Transaction transaction = null;
        try(Session session = HIbernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(playerIndex);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public PlayerIndex findById(int id) {
        try (Session session = HIbernateUtil.getSessionFactory().openSession()){
            return session.get(PlayerIndex.class, id);
        }
    }

    @Override
    public void update(PlayerIndex playerIndex) {
        Transaction transaction = null;
        try (Session session = HIbernateUtil.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            session.update(playerIndex);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HIbernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            PlayerIndex playerIndex = session.get(PlayerIndex.class, id);
            if (playerIndex != null) {
                session.delete(playerIndex);
                transaction.commit();
            }
        }catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
