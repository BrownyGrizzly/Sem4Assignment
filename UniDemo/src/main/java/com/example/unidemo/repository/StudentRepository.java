package com.example.unidemo.repository;

import com.example.unidemo.entity.Student;

import java.util.List;

public interface StudentRepository {
    //CRUD operations
    List<Student> findAll();
    void save(Student student);
    Student findById(int id);
    void delete(int id);
    void update(Student student);
    //Advanced operations
    List<Student> findByName(String name);
}
