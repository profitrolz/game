package com.game.controller;

import com.game.entity.*;
import com.game.exceptions.BadRequestException;
import com.game.exceptions.NotFoundException;
import com.game.player.PlayerSearchCriteria;
import com.game.service.PlayerService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Table;
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
                                   @RequestParam(required = false) Long after,
                                   @RequestParam(required = false) Long before,
                                   @RequestParam(required = false) Boolean banned,
                                   @RequestParam(required = false) Integer minExperience,
                                   @RequestParam(required = false) Integer maxExperience,
                                   @RequestParam(required = false) Integer minLevel,
                                   @RequestParam(required = false) Integer maxLevel,
                                   @RequestParam(required = false) Optional<Integer> pageNumber,
                                   @RequestParam(required = false) Optional<Integer> pageSize) {
        Specification<Player> spec = PlayerSpecification.getSpecification(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel);
        try {
            return playerService.getPlayers(spec, pageNumber, pageSize);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


    @GetMapping(path = "count")
    public Integer getPlayersCount(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) String title,
                                   @RequestParam(required = false) Race race,
                                   @RequestParam(required = false) Profession profession,
                                   @RequestParam(required = false) Long after,
                                   @RequestParam(required = false) Long before,
                                   @RequestParam(required = false) Boolean banned,
                                   @RequestParam(required = false) Integer minExperience,
                                   @RequestParam(required = false) Integer maxExperience,
                                   @RequestParam(required = false) Integer minLevel,
                                   @RequestParam(required = false) Integer maxLevel) {

        Specification<Player> spec = PlayerSpecification.getSpecification(name, title, race, profession, after, before, banned, minExperience, maxExperience, minLevel, maxLevel);

        try {
            return playerService.getPlayersCount(spec);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping(path = "{id}")
    public PlayerImpl getPlayerById(@PathVariable Long id) {
        if (id < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id must be positive");

        try {
            return playerService.getPlayerById(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping
    PlayerImpl addNewPlayer(@RequestBody PlayerImpl player) {
        try {
            playerService.addNewPlayer(player);
            return player;
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping(path = "{id}")
    PlayerImpl updatePlayer(@PathVariable Long id, @RequestBody PlayerImpl player) {
        if (id < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id must be positive");

        try {
            return playerService.updatePlayer(id, player);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @DeleteMapping(path = "{id}")
    public void deletePlayer(@PathVariable Long id) {
        if (id < 1)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id must be positive");

        try {
            playerService.deletePlayer(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }


}
