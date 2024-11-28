package com.example.practicaltest.repository;



import com.example.practicaltest.entity.Indexer;

import java.util.List;

public interface IndexerRepository {

    List<Indexer> findAll();

    void save(Indexer indexer);

    Indexer findById(int id);

    void update(Indexer indexer);

    void delete(int id);

}
