package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.MemberEntity;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Spring data JPA 저장 테스트")
    void testMember() {
        MemberEntity member = MemberEntity.builder()
                .userName("memberA")
                .build();

        MemberEntity saveMember = memberRepository.save(member);
        MemberEntity findMember = memberRepository.findById(saveMember.getId())
                .orElseThrow(EntityNotFoundException::new);

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());

    }
}