package com.naeunminchocofarm.ncf_api.lib.security;

import java.util.Set;

public class AuthUser {
    private final Integer id;
    private final Set<String> roleNames;

    public AuthUser(Integer id, Set<String> roleNames) {
        this.id = id;
        this.roleNames = roleNames;
    }

    public Integer getId() {
        return id;
    }

    public Set<String> getRoleNames() {
        return roleNames;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", roleNames=" + roleNames +
                '}';
    }
}
