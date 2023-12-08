package com.example.springdatajpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("")
    public String hello() {
        return "어서오세요~";
    }
}
