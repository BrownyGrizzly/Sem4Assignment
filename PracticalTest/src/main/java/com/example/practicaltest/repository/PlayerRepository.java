package com.example.practicaltest.repository;

import com.example.practicaltest.entity.Indexer;
import com.example.practicaltest.entity.Player;
import com.example.practicaltest.entity.PlayerIndex;

import java.util.List;

public interface PlayerRepository {
    // CRUD operations for Player
    List<Player> findAll();
    void save(Player player);
    Player findById(int playerId);
    void delete(int playerId);

    // CRUD operations for PlayerIndex
    void savePlayerIndex(PlayerIndex playerIndex);
    void deletePlayerIndex(int playerId, int indexId);
}

