package com.tecno.api_sec.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tecno.api_sec.configuration.security.filters.JwtAuthenticationFilter;
import com.tecno.api_sec.persistence.entity.Role;
import com.tecno.api_sec.persistence.entity.RolePermission;

/**
 * Configuración de seguridad HTTP para la aplicación.
 * 
 * Esta clase configura la seguridad HTTP para la aplicación utilizando Spring Security.
 * Define las reglas de autorización para las solicitudes HTTP, deshabilita CSRF,
 * configura la política de creación de sesiones y agrega un filtro de autenticación JWT.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class HttpSecurityConfig {

    /**
     * Proveedor de autenticación utilizado para autenticar las solicitudes.
     */
    @Autowired
    private AuthenticationProvider daoAuthProvider;

    /**
     * Filtro de autenticación JWT utilizado para validar los tokens JWT en las solicitudes.
     */
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Configura la cadena de filtros de seguridad HTTP.
     * 
     * Este método configura la cadena de filtros de seguridad HTTP para la aplicación.
     * Define las reglas de autorización para las solicitudes HTTP, deshabilita CSRF,
     * configura la política de creación de sesiones y agrega el filtro de autenticación JWT.
     * 
     * @param http Objeto HttpSecurity utilizado para configurar la seguridad HTTP.
     * @return SecurityFilterChain La cadena de filtros de seguridad configurada.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        
        SecurityFilterChain filterChain = http
                // Deshabilita la protección CSRF (Cross-Site Request Forgery)
                .csrf(csrfConfig -> csrfConfig.disable())
                 // Configura la política de creación de sesiones como STATELESS (sin estado)
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configura el proveedor de autenticación
                .authenticationProvider(daoAuthProvider)
                
                // Agrega el filtro de autenticación JWT antes del filtro de autenticación de usuario y contraseña
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                
                // Configura las reglas de autorización para las solicitudes HTTP
                .authorizeHttpRequests(authReqConfig ->{
                                       
                    // Autorizacion por Roles
                    buildRequestMatchersByRole(authReqConfig);

                    // Autorizacion Por Permisos individuales
                    //buildRequestMatchersByPermissions(authReqConfig);
                })
                .build();

        return filterChain;
    }

    private static void buildRequestMatchersByRole(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig){
        // ##############################################################
        // Autorizacion de endpoints de Productos
        // ##############################################################
        authReqConfig.requestMatchers(HttpMethod.GET,"/products").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/products/{productId}").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/products").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/products/{productId}").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/products/{productId}/disabled").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        // ##############################################################
        // Autorizacion de endpoints de Categorias
        // ##############################################################
        authReqConfig.requestMatchers(HttpMethod.GET,"/categories").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/categories/{categoryId}").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/categories").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/categories/{categoryId}").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/categories/{categoryId}/disabled").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name());

        // ##############################################################
        // Autorizacion de endpoints de Perfiles de usuario
        // ##############################################################
        authReqConfig.requestMatchers(HttpMethod.POST, "/profile/my-profile").hasAnyRole(Role.ADMINISTRATOR.name(), Role.ASSISTANT_ADMINISTRATOR.name(),Role.CUSTOMER.name());

        // ##############################################################
        // Endpoints publicos
        // ##############################################################
        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

        // Requiere autenticación para cualquier otra solicitud
        authReqConfig.anyRequest().authenticated();
    }

    private static void buildRequestMatchersByPermissions(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig){
        // ##############################################################
        // Autorizacion de endpoints de Productos
        // ############################################################## 
        authReqConfig.requestMatchers(HttpMethod.GET,"/products").hasAuthority(RolePermission.READ_ALL_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/products/{productId}").hasAuthority(RolePermission.READ_ONE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/products").hasAuthority(RolePermission.CREATE_ONE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/products/{productId}").hasAuthority(RolePermission.UPDATE_ONE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/products/{productId}/disabled").hasAuthority(RolePermission.DISABLE_ONE_PRODUCT.name());

        // ##############################################################
        // Autorizacion de endpoints de Categorias
        // ##############################################################
        authReqConfig.requestMatchers(HttpMethod.GET,"/categories").hasAuthority(RolePermission.READ_ALL_CATEGORIES.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/categories/{categoryId}").hasAuthority(RolePermission.READ_ONE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/categories").hasAuthority(RolePermission.CREATE_ONE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/categories/{categoryId}").hasAuthority(RolePermission.UPDATE_ONE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/categories/{categoryId}/disabled").hasAuthority(RolePermission.DISABLE_ONE_CATEGORY.name());

        // ##############################################################
        // Autorizacion de endpoints de Perfiles de usuario
        // ##############################################################
        authReqConfig.requestMatchers(HttpMethod.POST, "/profile/my-profile").hasAuthority(RolePermission.READ_MY_PROFILE.name());

        // ##############################################################
        // Endpoints publicos
        // ##############################################################
        authReqConfig.requestMatchers(HttpMethod.POST, "/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate-token").permitAll();

        // Requiere autenticación para cualquier otra solicitud
        authReqConfig.anyRequest().authenticated();
    }
}
