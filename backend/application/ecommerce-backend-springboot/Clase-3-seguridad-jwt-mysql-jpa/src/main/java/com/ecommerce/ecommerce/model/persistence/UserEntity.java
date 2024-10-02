package com.ecommerce.ecommerce.model.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.ecommerce.model.Role;
import com.ecommerce.ecommerce.model.RolePermission;
import com.ecommerce.ecommerce.model.RolePermissionConfig;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa un usuario en el sistema.
 * Esta clase mapea la tabla 'users' en la base de datos y contiene
 * información sobre los usuarios, incluyendo el nombre de usuario,
 * la contraseña, el nombre, el apellido, el correo electrónico y el rol.
 * Implementa la interfaz {@link UserDetails} de Spring Security para
 * proporcionar detalles de autenticación y autorización.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role==null){
            return new ArrayList<>();
        }

        List<RolePermission> permisos = RolePermissionConfig.getPermissionsForRole(role);

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (RolePermission permission : permisos) {
            String name = permission.name();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(name);
            authorities.add(authority);
        }

        // Se añade el rol como una autoridad
        // Spring Security espera que los roles tengan el prefijo "ROLE_" y se manejan como si fueran Authorities
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        return authorities;
    }
    
    
}
