package my.neoviso.test.backend.services;

import my.neoviso.test.backend.constants.ExceptionConstants;
import my.neoviso.test.backend.entities.Task;
import my.neoviso.test.backend.exceptions.EntityNotFoundException;
import my.neoviso.test.backend.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;

    public List<Task> getTasks(String username)
    {
        List<Task> tasks = (List<Task>) taskRepo.findTasks(username);
        return tasks;
    }

    public Task getTaskById(String username, Long id) throws EntityNotFoundException {
        Task task = taskRepo.findById(username, id).get();
        if (task == null)
            throw new EntityNotFoundException(ExceptionConstants.USER_NOT_FOUND);
        return task;
    }

    public void updateTask(String username, Long id, Task newTask) {
        Optional<Task> fetchTask = taskRepo.findById(id);
        //System.out.println("Updating task, fetching returned" + fetchTask.get());
        if (fetchTask.get() != null & fetchTask.get().getOwner().toLowerCase().equals(username.toLowerCase())) {
            //System.out.println(fetchTask.get().getOwner().toLowerCase() + " = " + username.toLowerCase());
            fetchTask.map(task -> {
                task.setText(newTask.getText());
                return taskRepo.save(task);
            })
            .orElseGet(() -> {
                newTask.setId(id);
                return taskRepo.save(newTask);
            });
        }
    }

    public void deleteTask(String username, Long id, Task newTask) {
        Optional<Task> fetchTask = taskRepo.findById(id);
        System.out.println("Deleting task, fetching returned" + fetchTask.get());
        if (fetchTask.get() != null & fetchTask.get().getOwner().toLowerCase().equals(username.toLowerCase())) {
            taskRepo.deleteById(id);
        }
    }

    public void toggleTask(String username, Long id, Task newTask) {
        Optional<Task> fetchTask = taskRepo.findById(id);
        System.out.println("Deleting task, fetching returned" + fetchTask.get());
        if (fetchTask.get() != null & fetchTask.get().getOwner().toLowerCase().equals(username.toLowerCase())) {
            fetchTask.map(task -> {
                task.setIsDone(!newTask.getIsDone());
                return taskRepo.save(task);
            });
        }
    }

    public void createTask(String username, Task newTask) {
        newTask.setOwner(username);
        taskRepo.save(newTask);
    }


}
