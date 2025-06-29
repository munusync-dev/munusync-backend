package com.munusync.backend.entity;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
