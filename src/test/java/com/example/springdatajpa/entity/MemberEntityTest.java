package com.example.springdatajpa.entity;

import com.example.springdatajpa.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberEntityTest {
    @PersistenceContext
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void testEntity() {
        TeamEntity team1 = TeamEntity.builder()
                .name("teamA")
                .build();

        TeamEntity team2 = TeamEntity.builder()
                .name("teamB")
                .build();

        em.persist(team1);
        em.persist(team2);

        MemberEntity member1 = MemberEntity.builder()
                .userName("member1")
                .age(10)
                .team(team1)
                .build();

        MemberEntity member2 = MemberEntity.builder()
                .userName("member2")
                .age(20)
                .team(team1)
                .build();

        MemberEntity member3 = MemberEntity.builder()
                .userName("member3")
                .age(30)
                .team(team2)
                .build();

        MemberEntity member4 = MemberEntity.builder()
                .userName("member4")
                .age(40)
                .team(team2)
                .build();

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        // 초기화
        em.flush();
        em.clear();

        // 확인
        List<MemberEntity> members =
                em.createQuery("select m from MemberEntity m", MemberEntity.class)
                .getResultList();

        for (MemberEntity member : members) {
            System.out.println("member = " + member);
            System.out.println("-> member.team = " + member.getTeam());
        }
    }

    @Test
    void JpaEventVaseEntity() throws Exception {
        // given
        MemberEntity member = MemberEntity.builder()
                .userName("member1")
                .age(10)
                .build();
        memberRepository.save(member);

        Thread.sleep(100);
    }
}