package com.munusync.backend.Dto.Request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProfileRequest {
    private String firstName;
    private String lastName;
    private String bio;
    private LocalDate dateOfBirth;
}