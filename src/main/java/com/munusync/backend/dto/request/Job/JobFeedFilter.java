package com.munusync.backend.dto.request.Job;

import com.munusync.backend.entity.Company;
import com.munusync.backend.entity.enums.ExperienceLevel;
import com.munusync.backend.entity.enums.JobType;
import com.munusync.backend.entity.enums.WorkType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobFeedFilter {
    private String title;
    private String contactEmail;
    private String status;
    private String description;
    private String requirements;
    private String benefits;
    private String currency;
    private Long views;
    private Long applicants;
    private Integer match;
    private JobType jobType;
    private WorkType workType;
    private String location;
    private String department;
    private ExperienceLevel experienceLevel;
    private Long minSalary;
    private Long maxSalary;
    private Boolean isFeatured;
}
