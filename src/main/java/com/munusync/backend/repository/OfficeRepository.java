package com.munusync.backend.repository;

import com.munusync.backend.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OfficeRepository extends JpaRepository<Office, Integer> {

    List<Office> findById(int Id);
    Optional<Office> findByEmail(String email);

}
