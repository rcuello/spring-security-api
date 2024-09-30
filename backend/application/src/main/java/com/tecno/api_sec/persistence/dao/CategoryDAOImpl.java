package com.tecno.api_sec.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecno.api_sec.persistence.entity.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional(readOnly = true)
    public Page<Category> findAll(Pageable pageable) {
        // Crear una consulta JPQL para obtener todos los productos
        String jpql = "SELECT c FROM Category c";
        TypedQuery<Category> query = entityManager.createQuery(jpql, Category.class);

        // Aplicar paginación
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // Obtener los productos de la página actual
        List<Category> products = query.getResultList();

        // Crear una consulta JPQL para obtener el número total de productos
        String countJpql = "SELECT COUNT(c) FROM Category c";
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);

        // Obtener el número total de productos
        Long total = countQuery.getSingleResult();

        // Devolver la página de productos
        return new PageImpl<>(products, pageable, total);
    }

    @Override
    @Transactional
    public void updateCategory(Category category) {
        this.entityManager.merge(category);
    }

    @Override
    @Transactional
    public void deleteCategory(Category category) {
        this.entityManager.remove(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Category> findOneById(Long categoryId) {
        Category category = entityManager.find(Category.class, categoryId);

        return Optional.ofNullable(category);
    }
}
