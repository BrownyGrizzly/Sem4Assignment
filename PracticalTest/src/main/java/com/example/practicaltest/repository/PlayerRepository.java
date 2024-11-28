package com.examt2303m.dypham.repository;

import com.examt2303m.dypham.entity.Player;

import java.util.List;

public interface PlayerRepository {

    List<Player> findAll();

    void save(Player player);

    Player findById(int id);

    void update(Player player);

    void delete(int id);

}
