package com.example.pdpspringsecurity4thlessonhomework.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomePage {

    @GetMapping
    public String HomePage(){

        return "Welcome Home page";
    }
}
