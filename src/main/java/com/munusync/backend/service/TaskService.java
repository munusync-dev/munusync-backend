package com.munusync.backend.service;
import com.munusync.backend.entity.Task;
import com.munusync.backend.dto.request.taskRequest;
import java.util.List;
public interface TaskService {

    Task createTask(taskRequest request);

    List<Task> getAllTasks();

    Task getTaskById(Long id);

    Task updateTask(Long id, taskRequest request);

    void deleteTask(Long id);
}

