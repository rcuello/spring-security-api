package com.tecno.api_sec.persistence.entity;

/**
 * Enumeración que representa los diferentes estados posibles de una categoría de productos.
 * Cada valor en esta enumeración describe un estado específico que una categoría puede tener,
 * como habilitada o deshabilitada.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
public enum CategoryStatus {

    /**
     * Estado habilitado. La categoría está activa y puede ser utilizada en el sistema.
     */
    ENABLED,

    /**
     * Estado deshabilitado. La categoría está inactiva y no puede ser utilizada en el sistema.
     */
    DISABLED;
}