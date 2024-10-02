package com.ecommerce.ecommerce.model;

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
    public static List<RolePermission> getPermissionsForRole(Role role) {
        switch (role) {
            case ADMINISTRATOR:
                return Arrays.asList(
                    RolePermission.READ_ALL_PRODUCTS,
                    RolePermission.CREATE_ONE_PRODUCT,
                    RolePermission.READ_MY_PROFILE
                );
            case ASSISTANT_ADMINISTRATOR:
                return Arrays.asList(
                    RolePermission.READ_ALL_PRODUCTS,
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
