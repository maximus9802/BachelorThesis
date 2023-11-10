package com.quyvx.main_server.shared.constants;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public enum RoleEnum {

    COMPANY,

    ADMIN_ADMIN,

    MANAGER,

    USER;

    public String getValue() {
        return this.name();
    }

    public static RoleEnum getRole(String value) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        return null;
    }

    public static List<GrantedAuthority> getAuthorities(List<String> roleTypes) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String roleType : roleTypes) {
            authorities.add(new SimpleGrantedAuthority(roleType));
        }
        return authorities;
    }
}
