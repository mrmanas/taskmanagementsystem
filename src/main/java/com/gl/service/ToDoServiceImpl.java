package com.gl.service;

import java.util.List;
import com.gl.model.ToDo;
import com.gl.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    @Override
    
    public void createTask(ToDo todo) {
        if (todo == null || todo.getTask() == null || todo.getTodoDescription() == null) {
            throw new IllegalArgumentException("Task and description must not be null");
        }
        toDoRepository.save(todo);
    }

    @Override
    public void updateTask(Long id, ToDo updatedTask) {
        if (updatedTask == null || updatedTask.getTask() == null || updatedTask.getTodoDescription() == null) {
            throw new IllegalArgumentException("Task and description must not be null");
        }
        ToDo existingTask = getTaskById(id);
        if (existingTask != null) {
            existingTask.setTask(updatedTask.getTask());
            existingTask.setTodoDescription(updatedTask.getTodoDescription());
            toDoRepository.save(existingTask);
        }
    }

    @Override
    
    public void deleteTask(Long id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public List<ToDo> getAllTasks() {
        return toDoRepository.findAll();
    }

    @Override
    public ToDo getTaskById(Long id) {
        return toDoRepository.findById(id).orElse(null);
    }

    

}
