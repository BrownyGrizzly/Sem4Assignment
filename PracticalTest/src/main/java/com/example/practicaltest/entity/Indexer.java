package com.example.practicaltest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Indexer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int indexId;

    private String name;
    private float valueMin;
    private float valueMax;
}
