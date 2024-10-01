package com.tecno.api_sec.services.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tecno.api_sec.controllers.api.dtos.SaveCategoryDTO;
import com.tecno.api_sec.persistence.entity.Category;


import java.util.Optional;

public interface ICategoryService {
    Page<Category> findAll(Pageable pageable);
    Optional<Category> findOneById(Long categoryId);
    
    Category updateOneById(Long categoryId, SaveCategoryDTO saveCategoryDTO);
    Category createOne(SaveCategoryDTO saveCategory);

    Category disableOneById(Long categoryId);
    Category enabledOneById(Long categoryId);
}
