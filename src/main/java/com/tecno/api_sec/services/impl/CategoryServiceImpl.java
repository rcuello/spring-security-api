package com.tecno.api_sec.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecno.api_sec.controllers.api.dtos.SaveCategoryDTO;
import com.tecno.api_sec.persistence.dao.ICategoryDAO;
import com.tecno.api_sec.persistence.entity.Category;
import com.tecno.api_sec.persistence.entity.CategoryStatus;

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
}
