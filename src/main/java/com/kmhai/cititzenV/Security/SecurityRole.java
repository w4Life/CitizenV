package com.kmhai.cititzenV.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import com.kmhai.cititzenV.entity.Role;

public class SecurityRole implements GrantedAuthority {

    @Autowired
    private Role role;

    @Override
    public String getAuthority() {
        return role.getRole();
    }
    
}
