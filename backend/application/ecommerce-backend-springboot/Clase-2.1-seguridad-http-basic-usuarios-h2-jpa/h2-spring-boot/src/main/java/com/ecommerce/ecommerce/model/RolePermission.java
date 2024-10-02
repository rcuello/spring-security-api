package com.ecommerce.ecommerce.model;

/**
 * Enumeración que representa los diferentes permisos que pueden ser asignados a los roles de usuario.
 * Cada valor en esta enumeración describe un permiso específico que un rol puede tener, como leer
 * todos los productos, crear una categoría, o leer el perfil del usuario.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
public enum RolePermission {
    /**
     * Permiso para leer todos los productos disponibles en el sistema.
     */
    READ_ALL_PRODUCTS,
    /**
     * Permiso para crear un nuevo producto en el sistema.
     */
    CREATE_ONE_PRODUCT,
    /**
     * Permiso para leer el perfil del usuario actual.
     */
    READ_MY_PROFILE;
}
