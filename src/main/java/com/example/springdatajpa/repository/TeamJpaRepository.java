package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.TeamEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeamJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public TeamEntity save(TeamEntity team) {
        em.persist(team);
        return team;
    }

    public void delete(TeamEntity team) {
        em.remove(team);
    }

    public List<TeamEntity> findAll() {
        return em.createQuery("select t from TeamEntity t", TeamEntity.class)
                .getResultList();
    }

    public Optional<TeamEntity> findById(Long id) {
        TeamEntity team = em.find(TeamEntity.class, id);
        return Optional.ofNullable(team);
    }

    public long count() {
        return em.createQuery("select count(t) from TeamEntity t", Long.class)
                .getSingleResult();
    }
}
