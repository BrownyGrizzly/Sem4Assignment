package com.examt2303m.dypham.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "age", nullable = false)
    private String age;

    @ManyToOne
    @JoinColumn(name = "index_id", referencedColumnName = "index_id", nullable = false)
    private Indexer indexer;


}
