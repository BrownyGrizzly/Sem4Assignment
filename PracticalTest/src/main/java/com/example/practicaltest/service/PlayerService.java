package com.example.practicaltest.service;

import com.example.practicaltest.entity.Indexer;
import com.example.practicaltest.entity.Player;
import com.example.practicaltest.entity.PlayerIndex;
import com.example.practicaltest.repository.IndexerRepository;
import com.example.practicaltest.repository.PlayerRepository;
import com.example.practicaltest.repository.impl.IndexerRepositoryImpl;
import com.example.practicaltest.repository.impl.PlayerRepositoryImpl;

import java.util.List;

public class PlayerService {

    private final PlayerRepository playerRepository;
    private final IndexerRepository indexerRepository;

    public PlayerService() {
        this.playerRepository = new PlayerRepositoryImpl();
        this.indexerRepository = new IndexerRepositoryImpl();
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public void addPlayer(Player player, List<PlayerIndex> playerIndexes) {
        // Save player
        playerRepository.save(player);

        // Save player index data (this will create player_index entries)
        for (PlayerIndex playerIndex : playerIndexes) {
            playerIndex.setPlayer(player); // Link to the player
            playerRepository.savePlayerIndex(playerIndex);
        }
    }

    public void deletePlayer(int playerId) {
        playerRepository.delete(playerId);
    }

    public void deletePlayerIndex(int playerId, int indexId) {
        playerRepository.deletePlayerIndex(playerId, indexId);
    }

    public Indexer getIndexerById(int indexId) {
        // Use the IndexerRepository to find an Indexer by its ID
        Indexer indexer = indexerRepository.findById(indexId);
        if (indexer == null) {
            throw new RuntimeException("Indexer with ID " + indexId + " not found!");
        }
        return indexer;
    }

    public List<Indexer> getAllIndexers() {
        // Use the IndexerRepository to retrieve all Indexers
        return indexerRepository.findAll();
    }
}

