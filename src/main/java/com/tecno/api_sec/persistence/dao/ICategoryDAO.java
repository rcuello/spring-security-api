package com.tecno.api_sec.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tecno.api_sec.persistence.entity.Category;

public interface ICategoryDAO {
    void createCategory(Category category);
    Page<Category> findAll(Pageable pageable);
}
