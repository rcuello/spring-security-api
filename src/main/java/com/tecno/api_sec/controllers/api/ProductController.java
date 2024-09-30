package com.tecno.api_sec.controllers.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tecno.api_sec.controllers.api.dtos.SaveProductDTO;
import com.tecno.api_sec.persistence.entity.Product;
import com.tecno.api_sec.services.impl.IProductService;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping
    public ResponseEntity<Product> createOne(@RequestBody @Valid SaveProductDTO saveProduct){
        Product product = productService.createOne(saveProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable){

        Page<Product> productsPage = productService.findAll(pageable);

        if(productsPage.hasContent()){
            return ResponseEntity.ok(productsPage);
        }

        return ResponseEntity.notFound().build();
    }
}
