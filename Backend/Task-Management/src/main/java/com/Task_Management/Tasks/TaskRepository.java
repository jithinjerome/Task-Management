package com.Task_Management.Tasks;

import com.Task_Management.Category.Category;
import com.Task_Management.Status.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task,Long> {


    List<Task> findByCategoryIdAndStatusId(long categoryId, long statusId);

    List<Task> findByCategoryId(long id);
}
