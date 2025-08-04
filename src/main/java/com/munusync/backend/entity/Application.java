package com.munusync.backend.entity;

import com.munusync.backend.entity.enums.ApplicationStatus;
import com.munusync.backend.entity.enums.JobType;
import jakarta.persistence.*;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applications")
public class Application {
    
  @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type", nullable = false)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "resume_url")
    private String resumeUrl;

    @Column(name = "contact_email", nullable = false)
    private String contactEmail;

    @Lob
    private String summary;

    @Lob
    private String benefits;

    private String phone;

    @Column(name = "expected_min_salary")
    private Long expectedMinSalary;

    @Column(name = "expected_max_salary")
    private Long expectedMaxSalary;

    @Column(length = 3)
    private String currency;


}
