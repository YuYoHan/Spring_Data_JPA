package com.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@ToString(of = {"id", "name"})
public class TeamEntity {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    private List<MemberEntity> memberEntityList = new ArrayList<>();

    @Builder
    public TeamEntity(Long id, String name, List<MemberEntity> memberEntityList) {
        this.id = id;
        this.name = name;
        this.memberEntityList = memberEntityList;
    }
}
