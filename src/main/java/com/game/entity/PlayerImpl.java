package com.game.entity;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Component
@Table(name = "player")
public class PlayerImpl implements Player{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    @Enumerated(EnumType.STRING)
    private Race race;
    @Enumerated(EnumType.STRING)
    private Profession profession;
    private Integer experience;
    private Integer level;
    private Integer untilNextLevel;
    private Date birthday;
    private Boolean banned;
    private static final Date MAX_BDAY;
    private static final Date MIN_BDAY;

    static {
        Date MIN_BDAY1;
        Date MAX_BDAY1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            MAX_BDAY1 = simpleDateFormat.parse("3000-12-31");
        } catch (ParseException e) {
            MAX_BDAY1 = new Date(Long.MAX_VALUE);
        }

        MAX_BDAY = MAX_BDAY1;

        try {
            MIN_BDAY1 = simpleDateFormat.parse("2000-01-01");
        } catch (ParseException e) {
            MIN_BDAY1 = new Date(0);
        }

        MIN_BDAY = MIN_BDAY1;
    }

    public PlayerImpl() {
    }

    public PlayerImpl(Long id, String name, String title, Race race, Profession profession, Integer experience, Integer level, Integer untilNextLevel, Date birthday, Boolean banned) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.race = race;
        this.profession = profession;
        this.experience = experience;
        this.level = level;
        this.untilNextLevel = untilNextLevel;
        this.birthday = birthday;
        this.banned = banned;

    }

    @Override
    public void validate() {
        if(name == null || title == null || race == null || profession == null || experience == null || birthday == null)
            throw new BadRequestException("Not validated player");

        if(name.equals(""))
            throw new BadRequestException("Not validated player");

        if(name.length() > 12)
            throw new BadRequestException("Name length must be <= 12");

        if(title.length() > 30)
            throw new BadRequestException("Title length must be <= 30");

        if(experience < 0 || experience > 10000000)
            throw new BadRequestException("Bad experience");

        if(birthday.getTime() < MIN_BDAY.getTime() || birthday.getTime() >  MAX_BDAY.getTime())
            throw new BadRequestException("Bad birthday");
    }

    public void calculateLevelAndUntilNextLevel(){
        level = calculateLLevel();
        untilNextLevel = calculateUntilNextLevel();
    }

    private Integer calculateLLevel() {
        return (int) (Math.sqrt(2500 + 200 * experience) - 50) / 100;
    }

    private Integer calculateUntilNextLevel() {
        return 50 * (level + 1) * (level + 2) - experience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return "PlayerImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", race=" + race +
                ", profession=" + profession +
                ", experience=" + experience +
                ", level=" + level +
                ", untilNextLevel=" + untilNextLevel +
                ", birthday=" + birthday +
                ", banned=" + banned +
                '}';
    }
}
