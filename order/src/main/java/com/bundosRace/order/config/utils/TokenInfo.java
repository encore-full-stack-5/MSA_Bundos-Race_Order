package com.bundosRace.order.config.utils;

import io.jsonwebtoken.Claims;

import java.util.UUID;

public record TokenInfo(
        UUID id, String userName
) {
    public static TokenInfo fromClaims(Claims claims){
        UUID id = UUID.fromString(claims.get("id", String.class));
        String userName = claims.get("username", String.class);
        return new TokenInfo(id, userName);
    }
}
