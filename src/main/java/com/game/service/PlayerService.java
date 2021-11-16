package com.game.service;

import com.game.entity.Player;
import com.game.entity.PlayerImpl;
import com.game.exceptions.NotFoundException;
import com.game.repository.PlayerRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepo playerRepo;

    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }
    //

    public List<Player> getPlayers(Specification<Player> playerSpecification, Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        Pageable pageable = PageRequest.of(pageNumber.orElse(0), pageSize.orElse(3));
        return playerRepo.findAll(playerSpecification,pageable).toList();
    }

    public Integer getPlayersCount(Specification<Player> specification) {
        return playerRepo.findAll(specification).size();
    }

    public PlayerImpl getPlayerById(Long id) {
        return playerRepo.findById(id).orElseThrow(() -> new IllegalStateException());
    }

    public void addNewPlayer(PlayerImpl player) {
        player.validate();
        player.calculateLevelAndUntilNextLevel();
        playerRepo.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepo.findById(id).orElseThrow(() -> new NotFoundException("Player with id " + id + " not found"));
        playerRepo.deleteById(id);
    }
}
