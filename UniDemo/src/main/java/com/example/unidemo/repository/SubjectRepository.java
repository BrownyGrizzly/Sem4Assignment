package com.example.unidemo.repository;

import com.example.unidemo.entity.Subject;

import java.util.List;

public interface SubjectRepository {
    //CRUD operations
    List<Subject> findAll();
    void save(Subject subject);
    Subject findById(int id);
    void delete(int id);
    void update(Subject subject);
    //Advanced operations
    List<Subject> findByName(String name);
}
