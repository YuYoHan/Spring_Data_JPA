package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.MemberDTO;
import com.example.springdatajpa.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    List<MemberEntity> findTop3HelloBy();

    @Query(name = "Member.findByUserName")
    List<MemberEntity> findByUserName(@Param("userName") String userName);

    // JPQL의 장점은 오타가나면 애플리케이션 로딩시점에 오류가 발생한다.
    @Query("select m from MemberEntity m where m.userName = :userName and m.age = :age")
    List<MemberEntity> findUser(@Param("userName") String userName,
                                @Param("age") int age);


    @Query("select m.userName from MemberEntity m")
    List<String> findUserNameList();

    // DTO로 조회
    @Query("select new com.example.springdatajpa.domain.MemberDTO(m.id, m.userName, t.name)" +
            "from MemberEntity m join m.team t")
    List<MemberDTO> findMemberDTO();

    @Query("select m from MemberEntity m where m.userName in :names")
    List<MemberEntity> findByUserNames(@Param("names") List<String> names);

}
