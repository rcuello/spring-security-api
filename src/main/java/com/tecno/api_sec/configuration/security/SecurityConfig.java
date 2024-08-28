package com.tecno.api_sec.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Configuramos el AuthenticationProvider con el UserDetailsService 
        // y el PasswordEncoder
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(getUserDetailsService());
        authProvider.setPasswordEncoder( getPasswordEncoder() );

        return authProvider;
    }

    @Bean
    public UserDetailsService getUserDetailsService() {
        // Creamos usuarios con sus respectivas contraseñas y roles en memoria        

        UserDetails user1 = User.withUsername("user1")
            .password("password1") 
            .roles("USER") // Configurar Rol
            .authorities("READ") // Configurar Permisos
            .build();

        UserDetails admin = User.withUsername("admin")
            .password("password1")
            .roles("ADMIN") // Configurar Rol
            .authorities("READ","CREATE") // Configurar Permisos
            .build();

        return new InMemoryUserDetailsManager(user1,admin);
    }
    
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        //NOTA: SOLO PARA DESARROLLO , esto no encripta las contraseñas
        return NoOpPasswordEncoder.getInstance();
    }
}
