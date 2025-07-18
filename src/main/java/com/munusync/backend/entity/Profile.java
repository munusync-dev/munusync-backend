package com.munusync.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(length = 500)
    private String bio;

    private LocalDate dateOfBirth;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
