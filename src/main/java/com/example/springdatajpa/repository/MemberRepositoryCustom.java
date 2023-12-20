package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.MemberEntity;

import java.util.List;

public interface MemberRepositoryCustom {
    // Spring Data JPA말고 직접 구현한 것을 사용하고 싶을 때
    List<MemberEntity> findMemberCustom();
}
