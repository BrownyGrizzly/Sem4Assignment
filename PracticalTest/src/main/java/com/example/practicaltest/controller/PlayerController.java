package com.example.practicaltest.controller;

import com.example.practicaltest.entity.Indexer;
import com.example.practicaltest.entity.Player;
import com.example.practicaltest.entity.PlayerIndex;
import com.example.practicaltest.service.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/players")
public class PlayerController extends HttpServlet {

    private final PlayerService playerService = new PlayerService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
        // Retrieve all players and indexers for the form and table
        List<Player> players = playerService.getAllPlayers();
        List<Indexer> indexers = playerService.getAllIndexers();

        // Set attributes for the JSP
        request.setAttribute("players", players);
        request.setAttribute("indexers", indexers);

        // Forward to the JSP (index.jsp)
        request.getRequestDispatcher("/players.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String playerName = request.getParameter("playerName");
        String playerAge = request.getParameter("playerAge");

        String[] indexIds = request.getParameterValues("indexId");
        String[] values = request.getParameterValues("value");

        Player player = new Player();
        player.setName(playerName);
        player.setFullName(playerName);
        player.setAge(playerAge);

        List<PlayerIndex> playerIndexes = new ArrayList<>();

        for (int i = 0; i < indexIds.length; i++) {
            int indexId = Integer.parseInt(indexIds[i]);
            float value = Float.parseFloat(values[i]);

            // Fetch the Indexer from the database
            Indexer indexer = playerService.getIndexerById(indexId);
            if (indexer == null) {
                throw new RuntimeException("Indexer with ID " + indexId + " not found!");
            }

            PlayerIndex playerIndex = new PlayerIndex();
            playerIndex.setIndexer(indexer);
            playerIndex.setValue(value);
            playerIndex.setPlayer(player);

            playerIndexes.add(playerIndex);
        }

        // Save player and associated indexes
        playerService.addPlayer(player, playerIndexes);

        response.sendRedirect(request.getContextPath() + "/players");
    }
}
