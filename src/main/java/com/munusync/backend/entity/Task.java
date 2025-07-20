package com.munusync.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
//write the constructors for you automatically at compile tim
@NoArgsConstructor
@AllArgsConstructor
public class Task {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)//column cant be empty
        private String title;

        private String description;

        private boolean completed = false;



}

