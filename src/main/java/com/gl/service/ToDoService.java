package com.gl.service;

import java.util.List;
import com.gl.model.ToDo;

public interface ToDoService {
    void createTask(ToDo todo);
    void updateTask(Long id, ToDo updatedTask);
    void deleteTask(Long id);
    List<ToDo> getAllTasks();
    ToDo getTaskById(Long id);
    
}
