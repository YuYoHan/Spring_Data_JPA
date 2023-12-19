package com.example.springdatajpa.repository;

import com.example.springdatajpa.domain.MemberDTO;
import com.example.springdatajpa.entity.MemberEntity;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    Page<MemberEntity> findByAge(int age, Pageable pageable);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query("update MemberEntity m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);


    // findAll할 때 멤버도 조회하면서 팀까지 조회하고 싶을 때
    // 기본 적으로 findAll 을 제공하기 때문에 Override 하여 재정의 후 사용
    @Override
    // DataJpa 에서 fetch 조인을 하기 위한 설정
    @EntityGraph(attributePaths = {"team"})
    List<MemberEntity> findAll();

    // JPQL과 같이 사용하는 방법
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from MemberEntity m")
    List<MemberEntity> findMemberEntityGraph();

    @QueryHints(value =  @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    MemberEntity findReadOnly(String userName);

    // select for update
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<MemberEntity> findLockByUserName(String userName);

}
