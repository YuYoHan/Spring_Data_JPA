package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.MemberEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Test
    void basicCRUD() {
        MemberEntity member1 = MemberEntity.builder()
                .userName("member1")
                .build();

        MemberEntity member2 = MemberEntity.builder()
                .userName("member2")
                .build();

        MemberEntity findMember1 = memberJpaRepository.findById(member1.getId()).get();
        MemberEntity findMember2 = memberJpaRepository.findById(member2.getId()).get();

        assertThat(findMember1.getId()).isEqualTo(member1);
        assertThat(findMember2.getId()).isEqualTo(member2);

        // 리스트 조회 검증
        List<MemberEntity> all = memberJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = memberJpaRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberJpaRepository.delete(member1);
        memberJpaRepository.delete(member2);
    }

}