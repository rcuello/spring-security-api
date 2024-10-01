package com.tecno.api_sec.configuration.security.util;

import com.tecno.api_sec.persistence.entity.Role;
import com.tecno.api_sec.persistence.entity.RolePermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthorityConverter {

    public static Collection<? extends GrantedAuthority> convertToAuthorities(Role role) {
        if (role == null || role.getPermissions() == null) {
            return new ArrayList<>();
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (RolePermission permission : role.getPermissions()) {
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
