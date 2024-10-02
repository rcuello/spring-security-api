package com.ecommerce.ecommerce.controllers.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/customer")
public class ProfileController {

    @GetMapping("/my-profile")
    public String getMyProfile() {
        return "Mi Perfile";
    }
    
}
