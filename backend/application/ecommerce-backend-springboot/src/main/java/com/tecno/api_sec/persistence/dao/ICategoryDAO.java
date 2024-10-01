package com.tecno.api_sec.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tecno.api_sec.persistence.entity.Category;

import java.util.Optional;

public interface ICategoryDAO {

    void createCategory(Category category);
    void updateCategory(Category category);
    void deleteCategory(Category category);
    Page<Category> findAll(Pageable pageable);
    Optional<Category> findOneById(Long categoryId);
}
