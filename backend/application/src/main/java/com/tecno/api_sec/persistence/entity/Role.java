package com.tecno.api_sec.persistence.entity;

import java.util.List;

public enum Role {
    
    ADMINISTRATOR,
    ASSISTANT_ADMINISTRATOR,
    CUSTOMER;

    public List<RolePermission> getPermissions() {
        return RolePermissionConfig.getPermissionsForRole(this);
    }

    
    
}
