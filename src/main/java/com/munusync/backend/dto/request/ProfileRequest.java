package com.munusync.backend.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProfileRequest {
    private String firstName;
    private String lastName;
    private String bio;
    private LocalDate dateOfBirth;
}