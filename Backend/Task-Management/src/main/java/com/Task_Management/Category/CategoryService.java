package com.Task_Management.Category;

import com.Task_Management.Status.Status;
import com.Task_Management.Status.StatusRepository;
import com.Task_Management.Tasks.Task;
import com.Task_Management.Tasks.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private StatusRepository statusRepository;
    private TaskRepository taskRepository;

    public CategoryService(CategoryRepository categoryRepository,
                           StatusRepository statusRepository,
                           TaskRepository taskRepository){
        this.categoryRepository = categoryRepository;
        this.statusRepository = statusRepository;
        this.taskRepository = taskRepository;
    }

    public Category addCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new IllegalArgumentException("Category already exist "+category.getName());
        }
        return categoryRepository.save(category);
    }

    public List<Category> allCategory() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    public ResponseEntity<?> updateCategory(long id,Category category) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            Category updated = categoryOptional.get();
            updated.setName(category.getName());
            categoryRepository.save(updated);
            return new ResponseEntity<>("Category Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Category not found with ID: "+category.getId(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Task>> getById(long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            List<Task> task = taskRepository.findByCategoryId(id);

            return new ResponseEntity<>(task,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getByCategoryandStatus(long categoryId, long statusId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Optional<Status> statusOptional = statusRepository.findById(statusId);

        if(categoryOptional.isPresent() && statusOptional.isPresent()){
            List<Task> tasks = taskRepository.findByCategoryIdAndStatusId(categoryId,statusId);
            return new ResponseEntity<>(tasks,HttpStatus.OK);
        }
        return new ResponseEntity<>("Category or Status not found !",HttpStatus.NOT_FOUND);
    }
}
