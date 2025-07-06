package com.munusync.backend.repository;

//we import user coz its in other folder not same pakage
import com.munusync.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;//inheritance
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //we don't write methods in interfaces
   //  gives all basic DB methods: save, findAll, findById, deleteById.
}
