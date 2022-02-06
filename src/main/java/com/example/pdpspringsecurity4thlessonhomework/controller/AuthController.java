package com.example.pdpspringsecurity4thlessonhomework.controller;

import com.example.pdpspringsecurity4thlessonhomework.payload.UserDto;
import com.example.pdpspringsecurity4thlessonhomework.security.jwt.JwtProvider;
import com.example.pdpspringsecurity4thlessonhomework.service.MyAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtProvider jwtProvider;
         @Autowired
        MyAuthService myAuthService;
    @PostMapping("/login")
    public ResponseEntity<?> loginToSystem(@RequestBody UserDto userDto){

        UserDetails userDetails = myAuthService.loadUserByUsername(userDto.getUsername());
        boolean equals = userDetails.getPassword().equals(userDto.getPassword());
        if (equals){
            String token = jwtProvider.generateToken(userDto.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Parol yoki login xato");


    }
}
