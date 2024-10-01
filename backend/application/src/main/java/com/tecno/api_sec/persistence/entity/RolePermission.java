package com.tecno.api_sec.persistence.entity;

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
     * Permiso para leer un producto específico en el sistema.
     */
    READ_ONE_PRODUCT,

    /**
     * Permiso para crear un nuevo producto en el sistema.
     */
    CREATE_ONE_PRODUCT,

    /**
     * Permiso para actualizar un producto existente en el sistema.
     */
    UPDATE_ONE_PRODUCT,

    /**
     * Permiso para deshabilitar un producto en el sistema.
     */
    DISABLE_ONE_PRODUCT,

    /**
     * Permiso para leer todas las categorías disponibles en el sistema.
     */
    READ_ALL_CATEGORIES,

    /**
     * Permiso para leer una categoría específica en el sistema.
     */
    READ_ONE_CATEGORY,

    /**
     * Permiso para crear una nueva categoría en el sistema.
     */
    CREATE_ONE_CATEGORY,

    /**
     * Permiso para actualizar una categoría existente en el sistema.
     */
    UPDATE_ONE_CATEGORY,

    /**
     * Permiso para deshabilitar una categoría en el sistema.
     */
    DISABLE_ONE_CATEGORY,

    /**
     * Permiso para leer el perfil del usuario actual.
     */
    READ_MY_PROFILE;
}