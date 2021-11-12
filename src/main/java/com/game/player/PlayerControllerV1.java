package com.game.player;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(path = "rest/players")
@RestController
public class PlayerControllerV1 {

private final PlayerService playerService;

    public PlayerControllerV1(PlayerService playerService) {
        this.playerService = playerService;
    }
    @GetMapping()
    public List<Player> getPlayers() {
        return playerService.getPlayers();
    }

    @GetMapping(path = "count")
    public Integer getPlayersCount() {
        return playerService.getPlayersCount();
    }


}
