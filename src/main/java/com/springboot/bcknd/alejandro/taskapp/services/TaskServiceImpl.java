package com.springboot.bcknd.alejandro.taskapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.bcknd.alejandro.taskapp.entities.Task;
import com.springboot.bcknd.alejandro.taskapp.models.TaskDTO;
import com.springboot.bcknd.alejandro.taskapp.repositories.ITaskRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor

@Service
public class TaskServiceImpl implements ItaskService {

    private final ITaskRepository _taskRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> getAllTasks() {
        return _taskRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO getTaskById(Long id) {
        Optional<Task> taskOptional = _taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            return convertToDTO(task);
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    @Override
    @Transactional
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = convertToEntity(taskDTO);
        Task savedTask = _taskRepository.save(task);
        return convertToDTO(savedTask);
    }

    @Override
    @Transactional
    public void deleteTask(Long id) {
        Optional<Task> taskOptional = _taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            _taskRepository.delete(taskOptional.get());
        } else {
            throw new RuntimeException("Task not found");
        }
    }


    private TaskDTO convertToDTO(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription()
        );
    }

    private Task convertToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        return task;
    }

}