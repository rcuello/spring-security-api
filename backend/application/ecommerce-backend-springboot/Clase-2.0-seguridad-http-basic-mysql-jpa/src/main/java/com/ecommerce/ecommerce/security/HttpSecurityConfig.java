package com.ecommerce.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ecommerce.ecommerce.model.Role;

import static org.springframework.security.config.Customizer.withDefaults;


import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Configura CORS
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // Deshabilita CSRF si no es necesario
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(authReqConfig -> {
                authReqConfig.requestMatchers(HttpMethod.POST,"/public/**").permitAll();

                authReqConfig.requestMatchers(HttpMethod.GET,"/customer/**").hasRole(Role.CUSTOMER.name());
                
                authReqConfig.anyRequest().authenticated(); 
            })
            // Habilita la autenticación básica HTTP;
            .httpBasic(withDefaults()); 

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permitir cualquier origen (o puedes especificar tu dominio)
        configuration.setAllowedOrigins(Arrays.asList("http://127.0.0.1:8080")); // Cambia '*' a tus dominios si es necesario
        
        // Permitir todos los métodos HTTP
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Permitir todas las cabeceras
        configuration.setAllowedHeaders(Arrays.asList("*"));
        

        // Aplica la configuración CORS a todas las rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
