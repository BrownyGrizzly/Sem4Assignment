package com.example.practicaltest.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerId;

    private String name;
    private String fullName;
    private String age;

    @ManyToOne
    @JoinColumn(name = "index_id")
    private Indexer indexer;

}