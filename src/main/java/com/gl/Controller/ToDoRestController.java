package com.gl.Controller;

import com.gl.model.ToDo;
import com.gl.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/tasks")
public class ToDoRestController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/all")
    public ResponseEntity<List<ToDo>> getAllTasks() {
        List<ToDo> tasks = toDoService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addTask(@RequestBody ToDo todo) {
        toDoService.createTask(todo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getTaskById(@PathVariable("id") long id) {
        ToDo task = toDoService.getTaskById(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable("id") long id, @RequestBody ToDo todo) {
        ToDo existingTask = toDoService.getTaskById(id);
        if (existingTask != null) {
            toDoService.updateTask(id, todo);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") long id) {
        ToDo existingTask = toDoService.getTaskById(id);
        if (existingTask != null) {
            toDoService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/markIncomplete/{id}")
    public ResponseEntity<Void> markTaskIsIncomplete(@PathVariable("id") long id) {
        ToDo task = toDoService.getTaskById(id);
        if (task != null) {
            task.setComplete(false);
            task.setCompleteStatus("incomplete");
            toDoService.updateTask(id, task);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/markComplete/{id}")
    public ResponseEntity<Void> markTaskIsComplete(@PathVariable("id") long id) {
        ToDo task = toDoService.getTaskById(id);
        if (task != null) {
            task.setComplete(true);
            task.setCompleteStatus("complete");
            toDoService.updateTask(id, task);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
