package com.ecommerce.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
    public CommandLineRunner createPasswordsCommand(PasswordEncoder passwordEncoder) {
		
        return args -> {
            System.out.println(passwordEncoder.encode("clave123"));
            
        };
    }

}
