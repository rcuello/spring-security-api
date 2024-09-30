package com.tecno.api_sec.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecno.api_sec.controllers.api.dtos.SaveUserDTO;
import com.tecno.api_sec.controllers.api.dtos.responses.RegisteredUserDTO;
import com.tecno.api_sec.services.impl.AuthenticationServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AuthenticationServiceImpl authService;
    
    @PostMapping
    public ResponseEntity<RegisteredUserDTO> registerOne(@RequestBody @Valid SaveUserDTO newUser){
        RegisteredUserDTO registeredUser = authService.registerOneCustomer(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }
}
