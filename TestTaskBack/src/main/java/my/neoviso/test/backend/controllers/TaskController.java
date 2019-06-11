package my.neoviso.test.backend.controllers;

import my.neoviso.test.backend.entities.Task;
import my.neoviso.test.backend.exceptions.EntityNotFoundException;
import my.neoviso.test.backend.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/api/tasks")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<Task> getTasks(Authentication authentication) {
        String username = authentication.getName();
        return taskService.getTasks(username);
    }

    @GetMapping("/api/tasks/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Task getTask(@PathVariable(name = "id") Long id, Authentication authentication) {
        String username = authentication.getName();
        try {
            System.out.println(taskService.getTaskById(username, id));
            return taskService.getTaskById(username, id);
        }
        catch (EntityNotFoundException e) {
            System.out.print(e.getMessage());
            return null;
        }
    }

    @PutMapping("/api/tasks/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void updateTask(@PathVariable(name = "id") Long id, @RequestHeader("Operation") String operationHeader,
        @RequestBody Task newTask, Authentication authentication) {
        String username = authentication.getName();
        System.out.println("User with name " + username +
            " called operation " + operationHeader + " on Task with id = " + id);
        if (operationHeader.equals("Delete")) {
            taskService.deleteTask(username, id, newTask);
            return;
        }
        if (operationHeader.equals("Update")) {
            taskService.updateTask(username, id, newTask);
            return;
        }
        if (operationHeader.equals("Toggle")) {
            taskService.toggleTask(username, id, newTask);
            return;
        }
    }

    @PostMapping("/api/tasks")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createTask(@RequestBody Task newTask, Authentication authentication) {
        String username = authentication.getName();
        Task task = new Task(newTask.getText(), newTask.getOwner(), newTask.getIsDone());
        taskService.createTask(username, task);
    }
}
