package com.munusync.backend.entity;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.NumberFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "job_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {
    @NotNull
    private String applicantName;
    @NotNull
    private String jobTitle;
    @NotNull
    private LocalDate applicationDate;
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @NotNull
    private String applicantPhoneNumber;
    @NotNull
    @Id
    private UUID id;
    @Email @NotNull
    private String applicantEmail;
    @NotNull @URL
    private String resumeLink;
    @NotNull @URL
    private String coverLetterLink;
    @NotNull
    private String jobDescription;
    @NotNull
    private String jobLocation;
    @NotNull
    private String companyName;
    @NotNull
    private JobStatus jobApplicationStatus;
    public enum JobStatus {
        APPLIED,
        INTERVIEW_SCHEDULED,
        OFFERED,
        REJECTED
    }

    
}
