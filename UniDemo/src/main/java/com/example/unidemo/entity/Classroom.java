package com.example.unidemo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "classroom")
    private List<Student> students;
    @ManyToMany(mappedBy = "classrooms")
    private List<Subject> subjects;
}
