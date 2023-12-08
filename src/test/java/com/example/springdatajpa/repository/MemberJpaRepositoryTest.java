package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.MemberEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
// JPA는 트랜잭션 안에서 돌아야하기 때문에 추가
@Transactional
class MemberJpaRepositoryTest {
    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("DB 등록 테스트")
    void testMember() {
        MemberEntity member = MemberEntity.builder()
                .userName("memberA")
                .build();

        MemberEntity saveMember = memberJpaRepository.save(member);

        MemberEntity findMember = memberJpaRepository.find(saveMember.getId());

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
    }

}