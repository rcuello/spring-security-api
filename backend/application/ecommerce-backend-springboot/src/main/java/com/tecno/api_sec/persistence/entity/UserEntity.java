package com.tecno.api_sec.persistence.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tecno.api_sec.configuration.security.util.AuthorityConverter;

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

    /**
     * Identificador único del usuario. Este campo es generado automáticamente
     * por la base de datos y sirve como clave primaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario del usuario. Este campo es único y se utiliza para
     * identificar al usuario en el sistema.
     */
    @Column(name = "username", unique = true)
    private String username;

    /**
     * Contraseña del usuario. Este campo almacena la contraseña encriptada del usuario.
     */
    @Column(name = "password")
    private String password;

    /**
     * Nombre del usuario. Este campo contiene el nombre del usuario.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Apellido del usuario. Este campo contiene el apellido del usuario.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Correo electrónico del usuario. Este campo contiene el correo electrónico del usuario.
     */
    @Column(name = "email")
    private String email;

    /**
     * Rol del usuario. Este campo indica el rol que tiene el usuario en el sistema,
     * como administrador, asistente de administrador o cliente.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param id El nuevo identificador único del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param firstName El nuevo nombre del usuario.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Obtiene el apellido del usuario.
     *
     * @return El apellido del usuario.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param lastName El nuevo apellido del usuario.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El nuevo correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene el nombre de usuario del usuario.
     *
     * @return El nombre de usuario del usuario.
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * Establece el nombre de usuario del usuario.
     *
     * @param username El nuevo nombre de usuario del usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtiene el rol del usuario.
     *
     * @return El rol del usuario.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Establece el rol del usuario.
     *
     * @param role El nuevo rol del usuario.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Obtiene la colección de autoridades (permisos) asociadas al usuario.
     *
     * @return La colección de autoridades asociadas al usuario.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityConverter.convertToAuthorities(this.role);
    }
}