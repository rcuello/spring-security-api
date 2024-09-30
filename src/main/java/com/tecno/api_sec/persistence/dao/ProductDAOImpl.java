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
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);
        cq.select(product);

        TypedQuery<Product> query = entityManager.createQuery(cq);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Product> products = query.getResultList();

        // Obtener el número total de productos
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Product> countProduct = countQuery.from(Product.class);
        countQuery.select(cb.count(countProduct));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(products, pageable, total);
    }
}

