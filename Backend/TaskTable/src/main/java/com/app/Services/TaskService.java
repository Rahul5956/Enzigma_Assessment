package com.app.Services;

import com.app.entity.Task;
import com.app.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Optional<Task> existingTaskOpt = taskRepository.findById(id);
        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();
            // Update fields
            existingTask.setUser(task.getUser());
            existingTask.setStatus(task.getStatus());
            existingTask.setDueDate(task.getDueDate());
            existingTask.setPriority(task.getPriority());
            existingTask.setComments(task.getComments());
            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task not found with id " + id); // Handle the not found case
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
