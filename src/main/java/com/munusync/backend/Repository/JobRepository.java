package com.munusync.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.munusync.backend.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID>, JobRepositoryCustom {
}
