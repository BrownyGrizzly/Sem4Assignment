package com.example.practicaltest.controller;

import com.example.practicaltest.dto.ViewListPlayer;
import com.example.practicaltest.entity.Indexer;
import com.example.practicaltest.entity.Player;
import com.example.practicaltest.entity.PlayerIndex;
import com.example.practicaltest.service.IndexerService;
import com.example.practicaltest.service.PlayerIndexService;
import com.example.practicaltest.service.PlayerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/player")
public class PlayerController extends HttpServlet {

    private PlayerService playerService;
    private IndexerService indexerService;
    private PlayerIndexService playerIndexService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.playerService = new PlayerService();
        this.indexerService = new IndexerService();
        this.playerIndexService = new PlayerIndexService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<Player> listPlayer = playerService.getAll();
            List<Indexer> listIndex = indexerService.getAll();
            List<ViewListPlayer> viewListPlayerList = new ArrayList<>();
            for (Player player : listPlayer) {
                ViewListPlayer viewListPlayer = new ViewListPlayer();
                viewListPlayer.setId(player.getPlayerId());
                viewListPlayer.setName(player.getName());
                viewListPlayer.setAge(player.getAge());
                viewListPlayer.setIndexName(player.getIndexer().getName());
                PlayerIndex playerIndex = playerIndexService.getPlayerIndexByPlayerAndIndexer(player, player.getIndexer());
                viewListPlayer.setValue(playerIndex.getValue());
                viewListPlayerList.add(viewListPlayer);
            }

            req.setAttribute("viewListPlayerList", viewListPlayerList);
            req.setAttribute("indexer", listIndex);
            RequestDispatcher dispatcher = req.getRequestDispatcher("player.jsp");
            dispatcher.forward(req, resp);
        }catch (Exception e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            // Lấy dữ liệu từ form
            String playerName = req.getParameter("name");
            String playerAge = String.valueOf(req.getParameter("age"));
            int indexId = Integer.parseInt(req.getParameter("indexId"));
            float value = Float.parseFloat(req.getParameter("value"));

            // Tạo đối tượng Player
            Indexer indexer = indexerService.getIndexerById(indexId);

            Player player = new Player();
            player.setName(playerName);
            player.setFullName(playerName);
            player.setAge(playerAge);
            player.setIndexer(indexer);

            playerService.savePlayer(player);

            PlayerIndex playerIndex = new PlayerIndex();
            playerIndex.setPlayer(player);
            playerIndex.setIndexer(indexer);
            playerIndex.setValue(value);
            playerIndexService.savePlayerIndex(playerIndex);
            resp.sendRedirect(req.getContextPath() + "/player");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error: " + e.getMessage());
        }
    }
}
