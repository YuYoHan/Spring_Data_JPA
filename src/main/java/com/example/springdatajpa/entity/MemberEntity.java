package com.example.springdatajpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MemberEntity {
    @Id @GeneratedValue
    private Long id;

    private String userName;

    @Builder
    public MemberEntity(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
