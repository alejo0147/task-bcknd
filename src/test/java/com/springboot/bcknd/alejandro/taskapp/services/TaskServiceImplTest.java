package com.springboot.bcknd.alejandro.taskapp.services;

import com.springboot.bcknd.alejandro.taskapp.entities.Task;
import com.springboot.bcknd.alejandro.taskapp.models.TaskDTO;
import com.springboot.bcknd.alejandro.taskapp.repositories.ITaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class TaskServiceImplTest {

    @Mock
    private ITaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTasks() {
        Task task = new Task(1L, "Título de prueba", "Descripción de prueba");
        when(taskRepository.findAll()).thenReturn(List.of(task));

        List<TaskDTO> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        assertEquals("Título de prueba", result.get(0).getTitle());
    }

    @Test
    void testGetTaskById() {
        Task task = new Task(1L, "Título prueba", "Descripción prueba");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskDTO result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals("Título prueba", result.getTitle());
    }

    @Test
    void testCreateTask() {
        TaskDTO taskDTO = new TaskDTO(null, "Nuevo título", "Nueva descripción");
        Task savedTask = new Task(1L, "Nuevo título", "Nueva descripción");

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        TaskDTO result = taskService.createTask(taskDTO);

        assertNotNull(result.getId());
        assertEquals("Nuevo título", result.getTitle());
    }

    @Test
    void testDeleteTask() {
        Task task = new Task(1L, "Tarea eliminar", "Descripción");
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).delete(task);
    }

    @Test
    void testDeleteTask_NotFound() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            taskService.deleteTask(99L);
        });

        assertEquals("Task not found", exception.getMessage());
    }
}
