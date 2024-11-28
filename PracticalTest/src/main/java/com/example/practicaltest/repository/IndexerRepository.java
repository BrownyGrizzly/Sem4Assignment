package com.example.practicaltest.repository;

import com.example.practicaltest.entity.Indexer;

import java.util.List;

public interface IndexerRepository {
    List<Indexer> findAll();
    Indexer findById(int id);
}