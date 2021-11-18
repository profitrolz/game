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
import java.util.Objects;
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
        return playerRepo.findById(id).orElseThrow(() -> new NotFoundException("Player with id " + id + " not found"));
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

    public PlayerImpl updatePlayer(Long id, PlayerImpl player) {

        PlayerImpl currentPlayer = playerRepo.findById(id).orElseThrow(() -> new NotFoundException("Player with id " + id + " not found"));

        if(player == null)
            return currentPlayer;

        if(player.getName() != null && player.getName().length() > 0 && !Objects.equals(player.getName(), currentPlayer.getName()))
            currentPlayer.setName(player.getName());

        if(player.getTitle() != null && player.getTitle().length() > 0 && !Objects.equals(player.getTitle(), currentPlayer.getTitle()))
            currentPlayer.setTitle(player.getTitle());

        if(player.getRace() != null && !Objects.equals(player.getRace(), currentPlayer.getRace()))
            currentPlayer.setRace(player.getRace());

        if(player.getProfession() != null && !Objects.equals(player.getProfession(), currentPlayer.getProfession()))
            currentPlayer.setProfession(player.getProfession());

        if(player.getBirthday() != null && !Objects.equals(player.getBirthday(), currentPlayer.getBirthday()))
            currentPlayer.setBirthday(player.getBirthday());

        if(player.getBanned() != null && !Objects.equals(player.getBanned(), currentPlayer.getBanned()))
            currentPlayer.setBanned(player.getBanned());

        if(player.getExperience() != null && !Objects.equals(player.getExperience(), currentPlayer.getExperience()))
            currentPlayer.setExperience(player.getExperience());

        currentPlayer.validate();
        currentPlayer.calculateLevelAndUntilNextLevel();

        playerRepo.save(currentPlayer);
        return currentPlayer;
    }
}
