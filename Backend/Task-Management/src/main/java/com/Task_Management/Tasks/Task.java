package com.Task_Management.Tasks;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Column(name = "Description")
    private String description;
    @Column(name = "StatusId")
    private long statusId;
    @Column(name = "Status")
    private String status;
    @Column(name = "CategoryId")
    private long categoryId;
    @Column(name = "Category")
    private String category;
    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime updateDate;


    @PrePersist
    protected void onCreate(){
        this.createDate = LocalDateTime.now();
    }
}
