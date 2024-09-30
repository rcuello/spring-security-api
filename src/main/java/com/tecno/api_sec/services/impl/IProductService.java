package com.tecno.api_sec.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tecno.api_sec.controllers.api.dtos.SaveProductDTO;
import com.tecno.api_sec.persistence.entity.Product;

public interface IProductService {
    Product createOne(SaveProductDTO saveProduct);
    Page<Product> findAll(Pageable pageable) ;
}
