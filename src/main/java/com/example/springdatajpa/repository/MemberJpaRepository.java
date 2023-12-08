package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public MemberEntity save(MemberEntity member) {
        em.persist(member);
        return member;
    }

    public MemberEntity find(Long id) {
        return em.find(MemberEntity.class, id);
    }
}
