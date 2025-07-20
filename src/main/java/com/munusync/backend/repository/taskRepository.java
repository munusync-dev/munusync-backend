package com.munusync.backend.repository;
import com.munusync.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
public interface taskRepository  extends JpaRepository<Task, Long>{

}
