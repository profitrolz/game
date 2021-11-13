package com.game.controller;

import com.game.entity.Player;
import com.game.service.PlayerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping(path = "rest/players")
@RestController
public class PlayerControllerV1 {

    private final PlayerService playerService;

    public PlayerControllerV1(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping()
    public List<Player> getPlayers(@RequestParam Optional<Integer> pageNumber, @RequestParam Optional<Integer> pageSize) {
        return playerService.getPlayers(pageNumber, pageSize);
    }

    @GetMapping(path = "count")
    public Long getPlayersCount() {
        return playerService.getPlayersCount();
    }

    @GetMapping(path = "{id}")
    public Player getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }


}
