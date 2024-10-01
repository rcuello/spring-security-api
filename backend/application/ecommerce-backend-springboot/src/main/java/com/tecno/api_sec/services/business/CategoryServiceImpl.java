package com.tecno.api_sec.services.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tecno.api_sec.controllers.api.dtos.SaveCategoryDTO;
import com.tecno.api_sec.exceptions.ObjectNotFoundException;
import com.tecno.api_sec.persistence.dao.ICategoryDAO;
import com.tecno.api_sec.persistence.entity.Category;
import com.tecno.api_sec.persistence.entity.CategoryStatus;
import com.tecno.api_sec.persistence.entity.Product;
import com.tecno.api_sec.persistence.entity.ProductStatus;


@Service
public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryDAO categoryDAO;

    @Override
    public Category createOne(SaveCategoryDTO saveCategory) {

        Category category = new Category();
        category.setName(saveCategory.getName());
        category.setStatus(CategoryStatus.ENABLED);

        categoryDAO.createCategory(category);

        return category;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryDAO.findAll(pageable);
    }

    @Override
    public Optional<Category> findOneById(Long categoryId) {
        
        return categoryDAO.findOneById(categoryId);
    }

    @Override
    public Category updateOneById(Long categoryId, SaveCategoryDTO saveCategoryDTO) {

        Category category = categoryDAO.findOneById(categoryId)
            .orElseThrow( () -> new ObjectNotFoundException("Category not found with id " + categoryId));

        category.setName(saveCategoryDTO.getName());
        categoryDAO.updateCategory(category);

        return category;
    }

    @Override
    public Category disableOneById(Long categoryId) {
        Category category = categoryDAO.findOneById(categoryId)
            .orElseThrow( () -> new ObjectNotFoundException("Category not found with id " + categoryId));
        
        category.setStatus(CategoryStatus.DISABLED);

        categoryDAO.updateCategory(category);
        
        return category;
    }

    @Override
    public Category enabledOneById(Long categoryId) {
        Category category = categoryDAO.findOneById(categoryId)
            .orElseThrow( () -> new ObjectNotFoundException("Category not found with id " + categoryId));
        
        category.setStatus(CategoryStatus.ENABLED);

        categoryDAO.updateCategory(category);
        
        return category;
    }
}
