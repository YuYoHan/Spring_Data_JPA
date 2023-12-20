package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.MemberEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<MemberEntity> findMemberCustom() {
        return em.createQuery("select m from MemberEntity m")
                .getResultList();
    }
}
