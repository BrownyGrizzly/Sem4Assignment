package com.example.practicaltest.service;

import com.example.practicaltest.entity.Indexer;
import com.example.practicaltest.repository.IndexerRepository;
import com.example.practicaltest.repository.impl.IndexerRepositoryImpl;

import java.util.List;


public class IndexerService {

    private final IndexerRepository indexerRepository;

    public IndexerService() {
        this.indexerRepository = new IndexerRepositoryImpl();
    }

    public List<Indexer> getAll() {
        return indexerRepository.findAll();
    }

    public Indexer getIndexerById(int id) throws Exception {
        Indexer indexer = indexerRepository.findById(id);
        if(indexer == null){
            throw new Exception("Indexer not found!");
        }
        return indexer;
    }

    public void saveIndexer(Indexer indexer){
        if(indexer.getName().isEmpty() || indexer.getName().length()<2){
            throw new IllegalArgumentException("Please input name");
        }
        indexerRepository.save(indexer);
    }

    public void deleteIndexer(int id){
        indexerRepository.delete(id);
    }
}
