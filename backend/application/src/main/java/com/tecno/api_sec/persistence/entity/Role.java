package com.tecno.api_sec.persistence.entity;

import java.util.List;

/**
 * Enumeración que representa los diferentes roles de usuario en el sistema.
 * Cada valor en esta enumeración describe un rol específico que un usuario puede tener,
 * como administrador, asistente de administrador o cliente.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
public enum Role {

    /**
     * Rol de administrador. Este rol tiene permisos completos para gestionar el sistema.
     */
    ADMINISTRATOR,

    /**
     * Rol de asistente de administrador. Este rol tiene permisos limitados para ayudar
     * en la gestión del sistema.
     */
    ASSISTANT_ADMINISTRATOR,

    /**
     * Rol de cliente. Este rol tiene permisos básicos para realizar compras y gestionar
     * su cuenta personal.
     */
    CUSTOMER;

    /**
     * Obtiene la lista de permisos asociados al rol.
     *
     * @return Una lista de permisos asociados al rol.
     */
    public List<RolePermission> getPermissions() {
        return RolePermissionConfig.getPermissionsForRole(this);
    }
}