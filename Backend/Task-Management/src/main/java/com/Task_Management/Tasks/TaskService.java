package com.Task_Management.Tasks;

import com.Task_Management.Category.Category;
import com.Task_Management.Category.CategoryRepository;
import com.Task_Management.Category.CategoryService;
import com.Task_Management.Status.Status;
import com.Task_Management.Status.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private StatusRepository statusRepository;
    private CategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository,
                       StatusRepository statusRepository,
                       CategoryService categoryService){
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
    }



    public Task createTask(Task task) {

        if(task.getDescription() == null || task.getDescription().trim().isEmpty()){
            throw new IllegalArgumentException("Description is required");
        }
        Optional<Status> statusOptional = statusRepository.findById(task.getStatusId());
        if(!statusOptional.isPresent()){
            throw new IllegalArgumentException("Invalid status Id: "+task.getStatusId());
        }
        Status status  = statusOptional.get();
        Optional<Category> categoryOptional = categoryRepository.findById(task.getCategoryId());
        if(!categoryOptional.isPresent()){
            throw new IllegalArgumentException("Invalid category Id: "+task.getCategoryId());
        }
        Category category = categoryOptional.get();
        task.setStatus(status.getName());
        task.setCategory(category.getName());
        return taskRepository.save(task);
    }

    public void deleteTask(long id){
        taskRepository.deleteById(id);
    }

    public ResponseEntity<?> updateTask(long id,Task task) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if(taskOptional.isPresent()){
            Task updated = taskOptional.get();
            Optional<Status> statusOptional = statusRepository.findById(task.getStatusId());
            if(statusOptional.isPresent()){
                Status status = statusOptional.get();
                updated.setStatusId(status.getId());
                updated.setStatus(status.getName());
                updated.setUpdateDate(LocalDateTime.now());
                taskRepository.save(updated);
                return new ResponseEntity<>(updated,HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid Status",HttpStatus.NOT_FOUND);

        }
        else {
            return new ResponseEntity<>("Task not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> task = taskRepository.findAll();

        task.sort(Comparator.comparingLong(Task::getId));

        if(task.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(task,HttpStatus.OK);
    }
}
