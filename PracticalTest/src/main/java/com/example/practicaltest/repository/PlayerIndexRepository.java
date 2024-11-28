package com.example.practicaltest.repository;


import com.example.practicaltest.entity.Indexer;
import com.example.practicaltest.entity.Player;
import com.example.practicaltest.entity.PlayerIndex;

import java.util.List;

public interface PlayerIndexRepository {

    List<PlayerIndex> findAll();

    PlayerIndex getPlayerIndexByPlayerAndIndexer(Player player, Indexer indexer);

    void save(PlayerIndex playerIndex);

    PlayerIndex findById(int id);

    void update(PlayerIndex playerIndex);

    void delete(int id);

}
