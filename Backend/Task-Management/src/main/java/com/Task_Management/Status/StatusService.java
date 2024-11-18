package com.Task_Management.Status;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    private StatusRepository statusRepository;
    public StatusService(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    public Status addStatus(Status status) {
        return statusRepository.save(status);
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }
}
