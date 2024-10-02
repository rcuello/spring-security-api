package com.ecommerce.ecommerce.controllers.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.controllers.dto.CustomerCreateNewDTO;
import com.ecommerce.ecommerce.controllers.dto.CustomerCreatedDTO;
import com.ecommerce.ecommerce.services.RegisterCustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/public/register-customer")
public class RegisterCustomerController {

    @Autowired
    private RegisterCustomerService registerCustomerService;

    @PostMapping
    public ResponseEntity<CustomerCreatedDTO> registerOne(@RequestBody @Valid CustomerCreateNewDTO newCustomer){
        CustomerCreatedDTO registeredUser = registerCustomerService.registerOneCustomer(newCustomer);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
