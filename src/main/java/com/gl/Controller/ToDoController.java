package com.gl.Controller;


import com.gl.model.ToDo;
import com.gl.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    @GetMapping("/home")
    public String Hello()
    {
    	return "home";
    }
    
    @GetMapping("/allTask")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", toDoService.getAllTasks());
        return "task-list"; // Returns the name of the HTML template to render
    }
    
    @GetMapping("/getfrom") // Changed to @GetMapping
    public String showCreateForm(Model model) {
        model.addAttribute("todo", new ToDo());
        return "create-task"; // Assuming you have a HTML template named "create-task.html"
    }
    @GetMapping("/edit/{id}")
    public String editTicket(@PathVariable("id") long id , Model model) {
        ToDo task = toDoService.getTaskById(id);
        model.addAttribute("todo", task);
        return "create-task";
    }
    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("todo") ToDo todo) {
        toDoService.createTask(todo);
        return "redirect:/allTask"; // Redirects to the list of tasks
    }
    
    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") long id) {
        toDoService.deleteTask(id);
        return "redirect:/allTask";
    }
   
    @PostMapping("/update")
    public String updateTicket(@PathVariable("id") long id, @ModelAttribute("todo")  ToDo todo) {
        toDoService.updateTask(id,todo);
        return "redirect:/allTask"; // Redirects to the list of tasks
    }
    

    @GetMapping("/markIncomplete/{id}")
    public String markTaskIsIncomplete(@PathVariable("id") long id) {
        ToDo task = toDoService.getTaskById(id);
        if (task != null) {
            task.setComplete(false);
            task.setCompleteStatus("incomplete"); // Set the completeStatus to "incomplete"
            toDoService.updateTask(id, task);
        }
        return "redirect:/allTask";
    }

    @GetMapping("/markComplete/{id}")
    public String markTaskIsComplete(@PathVariable("id") long id) {
        ToDo task = toDoService.getTaskById(id);
        if (task != null) {
            task.setComplete(true);
            task.setCompleteStatus("complete"); // Set the completeStatus to "complete"
            toDoService.updateTask(id, task);
        }
        return "redirect:/allTask";
    }

        
    }
