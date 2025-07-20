package com.munusync.backend.service.impl;

import com.munusync.backend.dto.request.taskRequest;  // Make sure you have this DTO
import com.munusync.backend.entity.Task;
import com.munusync.backend.Expections.TaskNotFoundException; // Your custom exception
import com.munusync.backend.repository.taskRepository;  // Correct class name and package
import com.munusync.backend.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final taskRepository taskRepository;

    @Override
    public Task createTask(taskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.isCompleted());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Task updateTask(Long id, taskRequest request) {
        Task task = getTaskById(id);
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.isCompleted());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}



