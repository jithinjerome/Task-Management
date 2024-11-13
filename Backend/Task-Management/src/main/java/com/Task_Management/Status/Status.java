package com.Task_Management.Status;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private String name;
}
