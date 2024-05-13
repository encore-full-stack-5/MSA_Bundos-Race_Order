package com.bundosRace.order.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter @AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    private UUID id;

    @Column(name = "USER_EMAIL")
    private String username;

    @Column(name = "USER_PASSWORD")
    private String password;
}
