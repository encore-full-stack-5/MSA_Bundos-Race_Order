package com.bundosRace.order.config.utils;

import com.bundosRace.order.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtils {
    private final Long expiration;
    private final SecretKey key;


    public JwtTokenUtils(
            @Value("${jwt.expiration}") Long expiration,
            @Value("${jwt.secret}") String secret
    ) {
        this.expiration = expiration;
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(User user) {
        String token = Jwts.builder()
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .expiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(key)
                .compact();
        return token;
    }

    public TokenInfo parseInfo(String token) {
        Claims payload = (Claims) Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parse(token)
                .getPayload();
        System.out.println(payload.getId()+payload.getSubject());
        return TokenInfo.fromClaims(payload);
    }

}
