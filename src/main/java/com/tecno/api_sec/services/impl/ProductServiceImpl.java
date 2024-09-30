package com.tecno.api_sec.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tecno.api_sec.controllers.api.dtos.SaveProductDTO;
import com.tecno.api_sec.persistence.dao.IProductDAO;
import com.tecno.api_sec.persistence.entity.Category;
import com.tecno.api_sec.persistence.entity.Product;
import com.tecno.api_sec.persistence.entity.ProductStatus;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductDAO productDAO;

    @Override
    public Product createOne(SaveProductDTO saveProduct) {
        Product product = new Product();
        product.setName(saveProduct.getName());
        product.setPrice(saveProduct.getPrice());
        product.setStatus(ProductStatus.ENABLED);

        Category category = new Category();
        category.setId(saveProduct.getCategoryId());
        product.setCategory(category);

        productDAO.createProduct(product);

        return product;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productDAO.findAll(pageable);
    }
}
