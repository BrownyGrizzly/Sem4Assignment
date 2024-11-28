package com.example.practicaltest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "player_index")
public class PlayerIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "index_id", referencedColumnName = "index_id", nullable = false)
    private Indexer indexer;

    @Column(name = "value", nullable = false)
    private Float value;

}
