package com.examt2303m.dypham.repository;

import com.examt2303m.dypham.entity.Indexer;
import com.examt2303m.dypham.entity.Player;
import com.examt2303m.dypham.entity.PlayerIndex;

import java.util.List;

public interface PlayerIndexRepository {

    List<PlayerIndex> findAll();

    PlayerIndex getPlayerIndexByPlayerAndIndexer(Player player, Indexer indexer);

    void save(PlayerIndex playerIndex);

    PlayerIndex findById(int id);

    void update(PlayerIndex playerIndex);

    void delete(int id);

}
