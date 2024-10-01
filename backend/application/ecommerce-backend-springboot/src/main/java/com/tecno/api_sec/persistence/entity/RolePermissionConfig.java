package com.tecno.api_sec.persistence.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

/**
 * Clase de configuración que define los permisos asociados a cada rol de usuario.
 * Esta clase proporciona un método estático para obtener la lista de permisos
 * correspondientes a un rol específico.
 * 
 * @author Ronald Cuello
 * @version 1.0
 * @since 2024-10-01
 */
public class RolePermissionConfig {

    /**
     * Obtiene la lista de permisos asociados a un rol específico.
     *
     * @param role El rol para el cual se desean obtener los permisos.
     * @return Una lista de permisos asociados al rol especificado. Si el rol no tiene
     *         permisos definidos, se retorna una lista vacía.
     */
    public static List<RolePermission> getPermissionsForRole(Role role) {
        switch (role) {
            case ADMINISTRATOR:
                return Arrays.asList(
                    RolePermission.READ_ALL_PRODUCTS,
                    RolePermission.READ_ONE_PRODUCT,
                    RolePermission.CREATE_ONE_PRODUCT,
                    RolePermission.UPDATE_ONE_PRODUCT,
                    RolePermission.DISABLE_ONE_PRODUCT,
                    RolePermission.READ_ALL_CATEGORIES,
                    RolePermission.READ_ONE_CATEGORY,
                    RolePermission.CREATE_ONE_CATEGORY,
                    RolePermission.UPDATE_ONE_CATEGORY,
                    RolePermission.DISABLE_ONE_CATEGORY,
                    RolePermission.READ_MY_PROFILE
                );
            case ASSISTANT_ADMINISTRATOR:
                return Arrays.asList(
                    RolePermission.READ_ALL_PRODUCTS,
                    RolePermission.READ_ONE_PRODUCT,
                    RolePermission.UPDATE_ONE_PRODUCT,
                    RolePermission.READ_ALL_CATEGORIES,
                    RolePermission.READ_ONE_CATEGORY,
                    RolePermission.UPDATE_ONE_CATEGORY,
                    RolePermission.READ_MY_PROFILE
                );
            case CUSTOMER:
                return Arrays.asList(
                    RolePermission.READ_MY_PROFILE
                );
            default:
                // Retorna un arreglo vacío si el rol no tiene permisos definidos
                return Collections.emptyList();
        }
    }
}