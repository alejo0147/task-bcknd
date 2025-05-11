package com.springboot.bcknd.alejandro.taskapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bcknd.alejandro.taskapp.models.TaskDTO;
import com.springboot.bcknd.alejandro.taskapp.services.ItaskService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:4200")


@RequiredArgsConstructor

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final ItaskService _taskService;

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return _taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable Long id) {
        return _taskService.getTaskById(id);
    }

    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO task) {
        return _taskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        _taskService.deleteTask(id);
    }

}
