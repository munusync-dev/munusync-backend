package com.munusync.backend.entity;

import com.munusync.backend.enums.CompanyStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String location;

    private String website;

    @ElementCollection
    private List<String> tags;

    @Enumerated(EnumType.STRING)
    private CompanyStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
