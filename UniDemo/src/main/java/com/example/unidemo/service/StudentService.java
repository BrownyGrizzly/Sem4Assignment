package com.example.unidemo.service;

import com.example.unidemo.entity.Student;
import com.example.unidemo.repository.StudentRepository;
import com.example.unidemo.repository.impl.StudentRepositoryImpl;

import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService() {
        this.studentRepository = new StudentRepositoryImpl();
    }

    // Retrieve all students
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    // Save a new student
    public void save(Student student) {
        validateStudent(student);
        if (studentRepository.findByName(student.getName()).stream()
                .anyMatch(existing -> existing.getName().equalsIgnoreCase(student.getName()) &&
                        existing.getEmail().equalsIgnoreCase(student.getEmail()))) {
            throw new RuntimeException("Student with the same name and email already exists!");
        }
        studentRepository.save(student);
    }

    // Find a student by ID
    public Student findById(int id) {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        return student;
    }

    // Delete a student by ID
    public void delete(int id) {
        Student student = findById(id); // Ensures the student exists before deleting
        studentRepository.delete(id);
    }

    // Update an existing student
    public void update(Student student) {
        validateStudent(student);
        Student existingStudent = findById(student.getId());
        if (existingStudent == null) {
            throw new RuntimeException("Cannot update. Student not found with ID: " + student.getId());
        }
        studentRepository.update(student);
    }

    // Find students by name
    public List<Student> searchByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new RuntimeException("Search name cannot be null or empty!");
        }
        return studentRepository.findByName(name);
    }

    // Validate student data
    private void validateStudent(Student student) {
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new RuntimeException("Student name cannot be empty!");
        }
        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Student email cannot be empty!");
        }
        if (student.getPhone() == null || student.getPhone().trim().isEmpty()) {
            throw new RuntimeException("Student phone number cannot be empty!");
        }
    }
}
