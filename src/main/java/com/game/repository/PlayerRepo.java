package com.game.repository;

import com.game.entity.Player;
import com.game.entity.PlayerImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepo extends JpaRepository<PlayerImpl, Long>, JpaSpecificationExecutor<Player> {


}