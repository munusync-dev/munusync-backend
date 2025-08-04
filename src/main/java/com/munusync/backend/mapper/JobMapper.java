package com.munusync.backend.mapper;

import com.munusync.backend.dto.response.JobFeedResponse;
import com.munusync.backend.entity.Job;

public class JobMapper {

    public static JobFeedResponse toJobFeedResponseDto(Job job) {
        return JobFeedResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .location(job.getLocation())
                .department(job.getDepartment())
                .contactEmail(job.getContactEmail())
                .jobType(job.getJobType())
                .workType(job.getWorkType())
                .experienceLevel(job.getExperienceLevel())
                .status(job.getStatus())
                .description(job.getDescription())
                .requirements(job.getRequirements())
                .benefits(job.getBenefits())
                .salaryMin(job.getSalaryMin())
                .salaryMax(job.getSalaryMax())
                .currency(job.getCurrency())
                .views(job.getViews())
                .applicants(job.getApplicants())
                .match(job.getMatch())
                .isFeatured(job.getIsFeatured())
                .build();
    }

}
