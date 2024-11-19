package com.example.unidemo.service;

import com.example.unidemo.entity.Subject;
import com.example.unidemo.repository.SubjectRepository;
import com.example.unidemo.repository.impl.SubjectRepositoryImpl;

import java.util.List;

public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService() {
        this.subjectRepository = new SubjectRepositoryImpl();
    }

    // List all subjects
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    // Save a new subject
    public void save(Subject subject) {
        if (subject.getName() == null || subject.getName().isEmpty()) {
            throw new RuntimeException("Subject name cannot be empty!");
        }
        if (subject.getHours() <= 0) {
            throw new RuntimeException("Subject hours must be greater than 0!");
        }
        List<Subject> existingSubjects = subjectRepository.findByName(subject.getName());
        if (existingSubjects != null && !existingSubjects.isEmpty()) {
            throw new RuntimeException("Subject with the same name already exists!");
        }
        subjectRepository.save(subject);
    }

    // Find a subject by ID
    public Subject findById(int id) {
        Subject subject = subjectRepository.findById(id);
        if (subject == null) {
            throw new RuntimeException("Subject not found!");
        }
        return subject;
    }

    // Delete a subject by ID
    public void delete(int id) {
        Subject subject = subjectRepository.findById(id);
        if (subject == null) {
            throw new RuntimeException("Subject not found!");
        }
        subjectRepository.delete(id);
    }

    // Update an existing subject
    public void update(Subject subject) {
        if (subject.getName() == null || subject.getName().isEmpty()) {
            throw new RuntimeException("Subject name cannot be empty!");
        }
        if (subject.getHours() <= 0) {
            throw new RuntimeException("Subject hours must be greater than 0!");
        }
        Subject existingSubject = subjectRepository.findById(subject.getId());
        if (existingSubject == null) {
            throw new RuntimeException("Subject not found!");
        }
        subjectRepository.update(subject);
    }

    // Search for subjects by name
    public List<Subject> searchSubjects(String name) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Search query cannot be empty!");
        }
        return subjectRepository.findByName(name);
    }
}
