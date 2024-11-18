package com.Task_Management.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/status")
public class StatusController {


    private StatusService statusService;
    public StatusController(StatusService statusService){
        this.statusService = statusService;
    }

    @GetMapping(path = "/all")
    public List<Status> getAllStatus(){
        return statusService.getAllStatus();
    }

    @PostMapping(path = "/add")
    public Status addStatus(@RequestBody Status status){
        return statusService.addStatus(status);
    }


}
