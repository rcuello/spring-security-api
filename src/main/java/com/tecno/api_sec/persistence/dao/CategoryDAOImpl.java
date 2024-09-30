package com.tecno.api_sec.persistence.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecno.api_sec.persistence.entity.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class CategoryDAOImpl implements ICategoryDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createCategory(Category category) {
        entityManager.persist(category);
        entityManager.flush();
    }
}
