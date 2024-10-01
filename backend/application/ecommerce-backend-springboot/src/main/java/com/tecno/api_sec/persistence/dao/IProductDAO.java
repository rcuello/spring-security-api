package com.tecno.api_sec.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tecno.api_sec.persistence.entity.Product;

import java.util.Optional;

public interface IProductDAO {
    void createProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findOneById(Long productId);
}
