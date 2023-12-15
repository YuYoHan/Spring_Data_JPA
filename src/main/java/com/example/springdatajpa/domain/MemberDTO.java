package com.example.springdatajpa.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class MemberDTO {
    private Long id;
    private String userName;
    private String teamName;

    @Builder
    public MemberDTO(Long id, String userName, String teamName) {
        this.id = id;
        this.userName = userName;
        this.teamName = teamName;
    }
}
