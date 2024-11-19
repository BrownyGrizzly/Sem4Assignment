package com.example.unidemo.repository;

import com.example.unidemo.entity.Classroom;

import java.util.List;

public interface ClassroomRepository {
    //CRUD operations
    List<Classroom> findAll();
    void save(Classroom classroom);
    Classroom findById(int id);
    void delete(int id);
    void update(Classroom classroom);
    //Advanced operations
    List<Classroom> findByName(String name);
}
