package com.munusync.backend.repository;

import com.munusync.backend.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
