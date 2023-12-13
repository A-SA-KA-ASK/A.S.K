package com.example.askBackend.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtTokenUtil {

    public static String createToken(String id, String key, long expireTimeMs){
        Claims clams = Jwts.claims();
        clams.put("id", id);

        return Jwts.builder()
                .setClaims(clams)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 만든 날짜
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) // 만료 날짜
                .signWith(SignatureAlgorithm.HS256, key)
                .compact()
                ;
    }
}
