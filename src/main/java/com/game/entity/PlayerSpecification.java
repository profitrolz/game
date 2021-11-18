package com.game.entity;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.Date;

public class PlayerSpecification {

    public static Specification<Player> getSpecification(String name, String title, Race race, Profession profession, Long after, Long before, Boolean banned,
                                                                     Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel) {
        Specification<Player> spec = Specification.where(PlayerSpecification.withName(name))
                .and(PlayerSpecification.withTitle(title))
                .and(PlayerSpecification.withRace(race))
                .and(PlayerSpecification.withProfession(profession))
                .and(PlayerSpecification.withAfter(after))
                .and(PlayerSpecification.withBefore(before))
                .and(PlayerSpecification.withBanned(banned))
                .and(PlayerSpecification.withMinExperience(minExperience))
                .and(PlayerSpecification.withMaxExperience(maxExperience))
                .and(PlayerSpecification.withMinLevel(minLevel))
                .and(PlayerSpecification.withMaxLevel(maxLevel));
        return spec;
    }


    private static Specification<Player> withName(String name) {
        return (root, query, cb) -> name == null ? null : cb.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Player> withTitle(String title) {
        return (root, query, cb) -> title == null ? null : cb.like(root.get("title"), "%" + title + "%");
    }

    private static Specification<Player> withRace(Race race) {
        return (root, query, cb) -> race == null ? null : cb.equal(root.get("race"), race);
    }

    private static Specification<Player> withProfession(Profession profession) {
        return (root, query, cb) -> profession == null ? null : cb.equal(root.get("profession"), profession);
    }

    private static Specification<Player> withAfter(Long after) {
        return (root, query, cb) -> after == null ? null : cb.greaterThanOrEqualTo(root.get("birthday"), new Date(after));
    }

    private static Specification<Player> withBefore(Long before) {
        return (root, query, cb) -> before == null ? null : cb.lessThanOrEqualTo(root.get("birthday"), new Date(before));
    }

    private static Specification<Player> withBanned(Boolean banned) {
        return (root, query, cb) -> banned == null ? null : cb.equal(root.get("banned"), banned);
    }

    private static Specification<Player> withMinExperience(Integer minExperience) {
        return (root, query, cb) -> minExperience == null ? null : cb.greaterThanOrEqualTo(root.get("experience"), minExperience);
    }

    private static Specification<Player> withMaxExperience(Integer maxExperience) {
        return (root, query, cb) -> maxExperience == null ? null : cb.lessThanOrEqualTo(root.get("experience"), maxExperience);
    }

    private static Specification<Player> withMinLevel(Integer minLevel) {
        return (root, query, cb) -> minLevel == null ? null : cb.greaterThanOrEqualTo(root.get("level"), minLevel);
    }

    private static Specification<Player> withMaxLevel(Integer maxLevel) {
        return (root, query, cb) -> maxLevel == null ? null : cb.lessThanOrEqualTo(root.get("level"), maxLevel);
    }

}
