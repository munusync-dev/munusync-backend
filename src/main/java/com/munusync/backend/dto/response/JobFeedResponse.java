package com.munusync.backend.dto.response;

import java.util.UUID;

import com.munusync.backend.entity.Company;
import com.munusync.backend.entity.enums.ExperienceLevel;
import com.munusync.backend.entity.enums.JobStatus;
import com.munusync.backend.entity.enums.JobType;
import com.munusync.backend.entity.enums.WorkType;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Response DTO for job feed information
 */
@Data
@Builder
@ToString
public class JobFeedResponse {
    
    private UUID id;
    private String title;
    private String location;
    private String department;
    private String contactEmail;
    private JobType jobType;
    private WorkType workType;
    private ExperienceLevel experienceLevel;
    private Long salaryMin;
    private Long salaryMax;
    private JobStatus status;
    private String requirements;
    private String benefits;
    private String currency;
    private Boolean isFeatured;
    private Long views;
    private Integer match;
    private Long applicants;
    private String description;
}