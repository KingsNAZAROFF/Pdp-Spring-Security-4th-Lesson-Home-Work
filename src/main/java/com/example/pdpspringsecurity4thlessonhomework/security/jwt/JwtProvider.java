package com.example.pdpspringsecurity4thlessonhomework.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtProvider {

    String secret = "PdpUzSpringBoot";
    long expireTime = 6000000000L;
    public String generateToken(String username){

        Date expireDate = new Date(System.currentTimeMillis()+expireTime);
        String token  = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
        return token;
    }


    public boolean validateToken(String token){
        try {


            Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJwt(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public String getUsernameFromToken(String token){
        String userName = Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
        return userName;
    }
}
