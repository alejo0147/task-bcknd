package com.springboot.bcknd.alejandro.taskapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.bcknd.alejandro.taskapp.entities.Task;

public interface ITaskRepository extends JpaRepository<Task, Long> {

}
