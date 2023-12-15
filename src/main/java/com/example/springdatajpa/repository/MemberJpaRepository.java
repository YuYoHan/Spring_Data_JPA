package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public MemberEntity save(MemberEntity member) {
        em.persist(member);
        return member;
    }

    // 하나만 조회
    public MemberEntity find(Long id) {
        return em.find(MemberEntity.class, id);
    }

    public Optional<MemberEntity> findById(Long id) {
        MemberEntity member = em.find(MemberEntity.class, id);
        return Optional.ofNullable(member);
    }

    public long count() {
        return em.createQuery("select count(m) from MemberEntity m", Long.class)
                .getSingleResult();
    }

    // 삭제
    public void delete(MemberEntity member) {
        em.remove(member);
    }

    // 전체 조회
    public List<MemberEntity> findAll() {
        return em.createQuery("select m from MemberEntity m", MemberEntity.class)
                .getResultList();
    }

    public List<MemberEntity> findByUsername(String userName) {
        return em.createNamedQuery("Member.findByUsername", MemberEntity.class)
                .setParameter("username", "회원1")
                .getResultList();
    }

}
