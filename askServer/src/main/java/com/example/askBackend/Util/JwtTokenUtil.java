package com.example.askBackend.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;

public class JwtTokenUtil {

    public static String createToken(String id, String nickname, String auth, String key, long expireTimeMs){
        Claims clams = Jwts.claims();
        clams.put("id", id);
        clams.put("nickname", nickname);
        clams.put("role", auth);

        return Jwts.builder()
                .setClaims(clams)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 만든 날짜
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs)) // 만료 날짜
                .signWith(SignatureAlgorithm.HS256, key)
                .compact()
                ;
    }

    public static boolean isExpired(String token, String secretKey){
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    public static String getNickname(String token, String secretKey){
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().get("nickname", String.class);
    }

    public static SimpleGrantedAuthority getAuthority(String token, String secretkey) {
        String role = Jwts.parserBuilder().setSigningKey(secretkey).build()
                .parseClaimsJws(token).getBody().get("role", String.class);

        return new SimpleGrantedAuthority(role);
    }
}
