package com.example.practicaltest.service;

import com.example.practicaltest.entity.Player;
import com.example.practicaltest.repository.PlayerRepository;
import com.example.practicaltest.repository.impl.PlayerRepositoryImpl;

import java.util.List;

public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService() {
        this.playerRepository = new PlayerRepositoryImpl();
    }

    public List<Player> getAll() throws Exception {
        return playerRepository.findAll();
    }

    public Player getPlayerById(int id) throws Exception {
        Player player = playerRepository.findById(id);
        if(player == null){
            throw new Exception("Indexer not found!");
        }
        return player;
    }

    public void savePlayer(Player player){
        if(player.getName().isEmpty() || player.getName().length()<2){
            throw new IllegalArgumentException("Please input name");
        }
        playerRepository.save(player);
    }

    public void deletePlayer(int id){
        playerRepository.delete(id);
    }
}
