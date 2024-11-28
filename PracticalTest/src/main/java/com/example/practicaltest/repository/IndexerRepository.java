package com.examt2303m.dypham.repository;

import com.examt2303m.dypham.entity.Indexer;

import java.util.List;

public interface IndexerRepository {

    List<Indexer> findAll();

    void save(Indexer indexer);

    Indexer findById(int id);

    void update(Indexer indexer);

    void delete(int id);

}
