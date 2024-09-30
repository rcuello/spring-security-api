package com.tecno.api_sec.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tecno.api_sec.controllers.api.dtos.auth.AuthenticationRequestDTO;
import com.tecno.api_sec.controllers.api.dtos.auth.AuthenticationResponseDTO;
import com.tecno.api_sec.services.impl.AuthenticationServiceImpl;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody @Valid AuthenticationRequestDTO entity) {
        
        AuthenticationResponseDTO response = authenticationService.login(entity);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestBody String jwt){
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }
    
}
