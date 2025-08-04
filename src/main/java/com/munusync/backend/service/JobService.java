package com.munusync.backend.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.munusync.backend.dto.request.Job.JobFeedFilter;
import com.munusync.backend.dto.response.JobFeedResponse;
import com.munusync.backend.mapper.JobMapper;
import com.munusync.backend.repository.JobRepository;


@Service
public class JobService {

    private final JobRepository jobRespository;

    @Autowired
    public JobService(JobRepository jobRespository) {
        this.jobRespository = jobRespository;
    }

    @Transactional(readOnly = true)
    public Slice<JobFeedResponse> getJobFeed(UUID cursor, JobFeedFilter filters, Pageable pageable) {
        return jobRespository.findJobs(cursor, filters, pageable).map(JobMapper::toJobFeedResponseDto);
    }

}