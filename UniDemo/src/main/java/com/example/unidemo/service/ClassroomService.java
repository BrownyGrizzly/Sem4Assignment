package com.example.unidemo.service;

import com.example.unidemo.entity.Classroom;
import com.example.unidemo.repository.ClassroomRepository;
import com.example.unidemo.repository.impl.ClassroomRepositoryImpl;

import java.util.List;

public class ClassroomService {
    private final ClassroomRepository classroomRepository;

    public ClassroomService() {
        this.classroomRepository = new ClassroomRepositoryImpl();
    }

    //CRUD operations
    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }
    public void save(Classroom classroom) {
        if (classroom.getName() == null || classroom.getName().isEmpty()) {
            throw new RuntimeException("Classroom name cannot be empty!");
        }
        if (classroomRepository.findByName(classroom.getName()) != null) {
            throw new RuntimeException("Classroom already exists!");
        }
        classroomRepository.save(classroom);
    }
    public Classroom findById(int id) {
        Classroom classroom = classroomRepository.findById(id);
        if (classroom == null) {
            throw new RuntimeException("Classroom not found!");
        }
        return classroom;
    }
    public void delete(int id) {
        classroomRepository.delete(id);
    }
    public void update(Classroom classroom) {
        if (classroom.getName() == null || classroom.getName().isEmpty()) {
            throw new RuntimeException("Classroom name cannot be empty!");
        }
        if (classroomRepository.findByName(classroom.getName()) == null) {
            throw new RuntimeException("Classroom not found!");
        }
        classroomRepository.update(classroom);
    }
    //Advanced operations
    public List<Classroom> searchClassroom(String name) {
        return classroomRepository.findByName(name);
    }
}
