package com.game.player;

import com.game.entity.Profession;
import com.game.entity.Race;

public class PlayerSearchCriteria {

    private final String name;
    private final String title;
    private final Race race;
    private final Profession profession;

    public PlayerSearchCriteria(String name, String title, Race race, Profession profession) {
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Race getRace() {
        return race;
    }

    public Profession getProfession() {
        return profession;
    }
}
