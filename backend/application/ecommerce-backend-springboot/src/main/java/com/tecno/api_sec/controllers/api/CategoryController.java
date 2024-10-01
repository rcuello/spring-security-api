package com.tecno.api_sec.controllers.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecno.api_sec.controllers.api.dtos.SaveCategoryDTO;
import com.tecno.api_sec.persistence.entity.Category;
import com.tecno.api_sec.services.business.ICategoryService;


@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createOne(@RequestBody @Valid SaveCategoryDTO saveCategory){
        Category category = categoryService.createOne(saveCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable){

        Page<Category> categoriesPage = categoryService.findAll(pageable);

        if(categoriesPage.hasContent()){
            return ResponseEntity.ok(categoriesPage);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateOneById(@PathVariable Long categoryId ,@RequestBody @Valid SaveCategoryDTO saveCategoryDTO){
        Category category = categoryService.updateOneById(categoryId, saveCategoryDTO);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<Category> disableOneById(@PathVariable Long categoryId){
        Category category = categoryService.disableOneById(categoryId);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{categoryId}/enabled")
    public ResponseEntity<Category> enabledOneById(@PathVariable Long categoryId){
        Category category = categoryService.enabledOneById(categoryId);
        return ResponseEntity.ok(category);
    }
}
