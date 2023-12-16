package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private String name;

    @Builder
    public User(String nickname, String email, String password, String name) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
