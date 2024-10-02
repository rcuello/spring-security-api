package com.ecommerce.ecommerce.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.ecommerce.ecommerce.model.Role;
import com.ecommerce.ecommerce.model.RolePermission;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Configuration
public class SecurityBeansConfiguration {

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(getUserDetailsServiceConBCryptPasswordEncoder());

        authProvider.setPasswordEncoder( getPasswordEncoder() );

        return authProvider;
    }
   

    @Bean
    public UserDetailsService getUserDetailsServiceConBCryptPasswordEncoder() {
        //NOTA: La cadena $2a$10$CvHD7dzZ/xRiiHJn7cqTceps/q6hTHiuD8cjaULYjpIXJND2/tDa6 es la contraseña clave123
        //Se deben enviar las credenciales clave123 para poder autenticar las rutas

        // Lista de autoridades para el usuario pablo.neruda
        List<String> user1Authorities = Arrays.asList(
            RolePermission.READ_ALL_PRODUCTS.name(),
            RolePermission.READ_MY_PROFILE.name()
        );

        // Lista de autoridades para el usuario admin
        List<String> adminAuthorities = Arrays.asList(
            RolePermission.READ_ALL_PRODUCTS.name(),
            RolePermission.CREATE_ONE_PRODUCT.name(),
            RolePermission.READ_MY_PROFILE.name()
        );

        // Convertir las listas de autoridades a Collection<? extends GrantedAuthority>
        Collection<? extends GrantedAuthority> user1GrantedAuthorities = user1Authorities.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        Collection<? extends GrantedAuthority> adminGrantedAuthorities = adminAuthorities.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

        // Creamos usuarios con sus respectivas contraseñas y roles en memoria        
        UserDetails user1 = User.withUsername("pablo.neruda")
            .password("$2a$10$CvHD7dzZ/xRiiHJn7cqTceps/q6hTHiuD8cjaULYjpIXJND2/tDa6")
            // Configurar Rol
            .roles(Role.CUSTOMER.name()) 
            // Configurar Permisos
            .authorities(user1GrantedAuthorities) 
            .build();

        UserDetails admin = User.withUsername("admin")
            .password("$2a$10$CvHD7dzZ/xRiiHJn7cqTceps/q6hTHiuD8cjaULYjpIXJND2/tDa6")
            // Configurar Rol
            .roles(Role.ADMINISTRATOR.name()) 
             // Configurar Permisos
            .authorities(adminGrantedAuthorities)
            .build();

        return new InMemoryUserDetailsManager(user1,admin);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
