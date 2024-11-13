package com.Task_Management.Tasks;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String description;
    private String status;
    @Column(updatable = false)
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    @PrePersist
    protected void onCreate(){
        this.createDate = LocalDateTime.now();
    }
}
