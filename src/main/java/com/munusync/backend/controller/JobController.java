package com.munusync.backend.controller;

import com.munusync.backend.service.JobService;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munusync.backend.dto.request.Job.JobFeedFilter;
import com.munusync.backend.dto.response.JobFeedResponse;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService jobService;

    JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/feed")
    public Slice<JobFeedResponse> getJobFeed(UUID cursor, JobFeedFilter jobFeedFilter,
            @PageableDefault(size = 15, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return jobService.getJobFeed(cursor, jobFeedFilter, pageable);
    }
}
