package com.Task_Management.Category;


import com.Task_Management.Tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/add")
    public Category addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping(path = "/all")
    public List<Category> allCategory(){
        return categoryService.allCategory();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCategory(@PathVariable long id){
        categoryService.deleteCategory(id);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable long id, @RequestBody Category category){
        return categoryService.updateCategory(id,category);
    }

    @GetMapping(path = "/categories/{id}")
    public ResponseEntity<List<Task>> getById(@PathVariable long id){
        return categoryService.getById(id);
    }

    @GetMapping(path = "/category/status/{categoryId}/{statusId}")
    public ResponseEntity<?> getByCategoryandStatus(@PathVariable long categoryId, @PathVariable long statusId){
        return categoryService.getByCategoryandStatus(categoryId,statusId);
    }

}
