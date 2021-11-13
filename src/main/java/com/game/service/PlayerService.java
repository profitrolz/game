package com.game.service;

import com.game.entity.Player;
import com.game.repository.PlayerRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<Player> getPlayers(Optional<Integer> pageNumber, Optional<Integer> pageSize) {
        Pageable pageable = PageRequest.of(pageNumber.orElse(0), pageSize.orElse(3));
        return playerRepo.findAll(pageable).toList();
    }

    public Long getPlayersCount() {
        return playerRepo.count();
    }

    public Player getPlayerById(Long id) {
        return playerRepo.findById(id).orElseThrow(() -> new IllegalStateException());
    }
}
