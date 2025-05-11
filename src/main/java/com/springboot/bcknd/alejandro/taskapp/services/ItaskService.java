package com.springboot.bcknd.alejandro.taskapp.services;

import java.util.List;

import com.springboot.bcknd.alejandro.taskapp.models.TaskDTO;

public interface ItaskService {

    List<TaskDTO> getAllTasks();
    TaskDTO getTaskById(Long id);   
    TaskDTO createTask(TaskDTO taskDTO);
    void deleteTask(Long id);

}
