package com.tecno.api_sec.persistence.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tecno.api_sec.persistence.entity.Product;

public interface IProductDAO {
    void createProduct(Product product);
    Page<Product> findAll(Pageable pageable);
}
