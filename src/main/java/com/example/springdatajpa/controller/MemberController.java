package com.example.springdatajpa.controller;

import com.example.springdatajpa.entity.MemberEntity;
import com.example.springdatajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        MemberEntity member = memberRepository.findById(id).get();
        return member.getUserName();
    }

    // 도메인 클래스 컨버터
    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") MemberEntity member) {
        return member.getUserName();
    }
}
