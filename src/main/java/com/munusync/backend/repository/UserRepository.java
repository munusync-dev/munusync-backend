package com.munusync.backend.repository;

import com.munusync.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

public interface UserRepository extends JpaRepository<User, Long> {
=======
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUsersById(long id);
>>>>>>> 85852505ceee28e61442811cd688f849a908978b
}
