package com.tecno.api_sec.controllers.api.dtos;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

import com.tecno.api_sec.persistence.entity.Role;

/**
 * DTO (Data Transfer Object) que representa el perfil de un usuario.
 * 
 * Este DTO se utiliza para transferir información relevante del perfil del usuario
 * desde la capa de persistencia a la capa de presentación.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
public class UserProfileDTO {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Collection<? extends GrantedAuthority> authorities;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
