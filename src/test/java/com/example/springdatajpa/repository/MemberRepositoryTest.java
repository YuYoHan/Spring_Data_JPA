package com.example.springdatajpa.repository;

import com.example.springdatajpa.entity.MemberEntity;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("Spring data JPA 저장 테스트")
    void testMember() {
        System.out.println("memberRepository : " + memberRepository.getClass());
        MemberEntity member = MemberEntity.builder()
                .userName("memberA")
                .build();

        MemberEntity saveMember = memberRepository.save(member);
        MemberEntity findMember = memberRepository.findById(saveMember.getId())
                .orElseThrow(EntityNotFoundException::new);

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    void basicCRUD() {
        MemberEntity member1 = MemberEntity.builder()
                .userName("member1")
                .build();

        MemberEntity member2 = MemberEntity.builder()
                .userName("member2")
                .build();

        MemberEntity findMember1 = memberRepository.findById(member1.getId()).get();
        MemberEntity findMember2 = memberRepository.findById(member2.getId()).get();

        assertThat(findMember1.getId()).isEqualTo(member1);
        assertThat(findMember2.getId()).isEqualTo(member2);

        // 리스트 조회 검증
        List<MemberEntity> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);
    }

    @Test
    void findHelloBy() {
        List<MemberEntity> helloBy = memberRepository.findTop3HelloBy();
    }



}