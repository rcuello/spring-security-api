package com.ecommerce.ecommerce.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {

    @GetMapping("/sayHi")
    public String sayHi(@RequestParam String name) {
        return String.format("Hi , %s!", name);
    }

    @GetMapping("/sayGoodbye")
    public String sayGoodbye(@RequestParam String name) {
        return String.format("GoodBye , %s!", name);
    }
    
}
