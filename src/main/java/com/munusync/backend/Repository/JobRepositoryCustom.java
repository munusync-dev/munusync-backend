package com.munusync.backend.repository;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import com.munusync.backend.dto.request.Job.JobFeedFilter;
import com.munusync.backend.entity.Job;

@Repository
public interface JobRepositoryCustom {
     Slice<Job> findJobs(UUID cursor, JobFeedFilter filters, Pageable pageable);
}
