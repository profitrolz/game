package com.game.player;

import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PlayerService {

    public List<Player> getPlayers() {
        return Arrays.asList(new Player(1L,"Vasya","title", Race.ELF, Profession.PALADIN,5000,44,456, Date.valueOf(LocalDate.now()),false));
    }

    public Integer getPlayersCount() {
        return 1;
    }
}
