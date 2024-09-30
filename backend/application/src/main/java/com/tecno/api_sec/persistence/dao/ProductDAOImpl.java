package com.tecno.api_sec.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecno.api_sec.persistence.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDAOImpl implements IProductDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createProduct(Product product) {
        entityManager.persist(product);
        entityManager.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Product> findAll(Pageable pageable) {
        // Crear una consulta JPQL para obtener todos los productos
        String jpql = "SELECT p FROM Product p";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);

        // Aplicar paginación
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // Obtener los productos de la página actual
        List<Product> products = query.getResultList();

        // Crear una consulta JPQL para obtener el número total de productos
        String countJpql = "SELECT COUNT(p) FROM Product p";
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);

        // Obtener el número total de productos
        Long total = countQuery.getSingleResult();

        // Devolver la página de productos
        return new PageImpl<>(products, pageable, total);
    }

    @Override
    public Optional<Product> findOneById(Long productId) {
        Product product = entityManager.find(Product.class, productId);

        return Optional.ofNullable(product);
    }

    @Override
    @Transactional
    public void updateProduct(Product product) {
        this.entityManager.merge(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        this.entityManager.remove(product);
    }

}

