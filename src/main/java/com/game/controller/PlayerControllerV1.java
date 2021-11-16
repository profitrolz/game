package com.game.controller;

import com.game.entity.*;
import com.game.player.PlayerSearchCriteria;
import com.game.service.PlayerService;
import org.springframework.data.jpa.domain.Specification;
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
    public List<Player> getPlayers(@RequestParam(required = false) String name,
                                       @RequestParam(required = false) String title,
                                       @RequestParam(required = false) Race race,
                                       @RequestParam(required = false) Profession profession,
                                       @RequestParam(required = false) Optional<Integer> pageNumber,
                                       @RequestParam(required = false) Optional<Integer> pageSize) {
        Specification<Player> spec = Specification.where(PlayerSpecification.withname(name))
                .and(PlayerSpecification.withTitle(title))
                .and(PlayerSpecification.withRace(race))
                .and(PlayerSpecification.withProfession(profession));
        return playerService.getPlayers(spec, pageNumber, pageSize);
    }

    @GetMapping(path = "count")
    public Integer getPlayersCount(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String title,
                                @RequestParam(required = false) Race race,
                                @RequestParam(required = false) Profession profession) {
        Specification<Player> spec = Specification.where(PlayerSpecification.withname(name))
                .and(PlayerSpecification.withTitle(title))
                .and(PlayerSpecification.withRace(race))
                .and(PlayerSpecification.withProfession(profession));
        return playerService.getPlayersCount(spec);
    }

    @GetMapping(path = "{id}")
    public PlayerImpl getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping
    PlayerImpl addNewPlayer(@RequestBody PlayerImpl player){
        playerService.addNewPlayer(player);
        return player;
    }

    @DeleteMapping(path = "{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
    }


}
