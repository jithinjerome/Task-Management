package com.Task_Management.Tasks;

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


    public Task createTask(Task task) {

        if(task.getDescription() == null || task.getDescription().trim().isEmpty()){
            throw new IllegalArgumentException("Description is required");
        }
        task.setStatus("Pending");
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
