package com.tecno.api_sec.services.impl;

import com.tecno.api_sec.controllers.api.dtos.SaveCategoryDTO;
import com.tecno.api_sec.persistence.entity.Category;

public interface ICategoryService {
    Category createOne(SaveCategoryDTO saveCategory);
}