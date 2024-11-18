package com.Task_Management.Tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(path = "/create")
    public Task createTask(@RequestBody Task task){

        return taskService.createTask(task);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateTask(@PathVariable long id,@RequestBody Task task){
        return taskService.updateTask(id,task);
    }


    @DeleteMapping(path = "/{id}")
    public void deleteTask(@PathVariable long id){
        taskService.deleteTask(id);
    }
}
