package com.tecno.api_sec.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    // API pública, accesible sin autenticación
    @GetMapping("/api/public/hello")
    public String publicApi() {
        return "Hello from the public API!";
    }

    // API protegida, requiere autenticación
    @GetMapping("/api/secure/hello")
    public String secureApi() {
        return "Hello from the secure API!";
    }
}
