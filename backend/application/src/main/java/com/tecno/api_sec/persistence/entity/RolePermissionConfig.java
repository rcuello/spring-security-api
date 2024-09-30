package com.tecno.api_sec.persistence.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class RolePermissionConfig {

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