package com.bundosRace.order.config.utils;

import com.bundosRace.order.domain.entity.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JwtTokenUtilsTest {
    private final JwtTokenUtils jwtTokenUtils = new JwtTokenUtils(1000*2L, "fjni2kasj24kjvfkjgjk535kjjkgkjb3kknvnknvnsnasd");

    @Test
    void generateToken() {
        // given
        User user = new User(UUID.randomUUID(), "asd", "1234");

        // when
        String token = jwtTokenUtils.generateToken(user);

        // then
        assertNotNull(token);
        assertEquals(3, token.split("\\.").length);
    }

    @Test
    void parseinfo() {
        // given
        User user = new User(UUID.randomUUID(), "asd", "1234");
        String token = jwtTokenUtils.generateToken(user);

        // when
        TokenInfo answer = jwtTokenUtils.parseinfo(token);

        //then
        System.out.println(answer.id()+" "+answer.userName());
        assertNotNull(answer);
        assertEquals(user.getUsername(), answer.userName());
    }
}