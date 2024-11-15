package com.Task_Management.Tasks;

import com.Task_Management.Category.Category;
import com.Task_Management.Category.CategoryRepository;
import com.Task_Management.Status.Status;
import com.Task_Management.Status.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CategoryRepository categoryRepository;


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
            updated.setStatus(task.getStatus());
            updated.setUpdateDate(LocalDateTime.now());
            taskRepository.save(updated);
            return new ResponseEntity<>(updated,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Task not found",HttpStatus.NOT_FOUND);
        }
    }
}
