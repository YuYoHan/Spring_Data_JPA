package com.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"id", "userName", "age"})
public class MemberEntity extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String userName;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TeamEntity team;

    @Builder
    public MemberEntity(Long id, String userName, int age, TeamEntity team) {
        this.id = id;
        this.userName = userName;
        this.age = age;
        if(team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(TeamEntity team) {
        this.team = team;
        team.getMemberEntityList().add(this);
    }
}
