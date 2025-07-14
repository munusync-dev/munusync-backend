package com.munusync.backend;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true)
    private String email; ;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    private LocalDateTime createdAt;

}
