package com.naeunminchocofarm.ncf_api.lib.auth;

import java.util.HashSet;
import java.util.Set;

public class AuthInfo {
    private final Long id;
    private final Set<String> rollNames;
    private final Set<Integer> rollFlags;

    public AuthInfo(Long id, Set<String> rollNames, Set<Integer> roleFlags) {
        this.id = id;
        this.rollNames = rollNames;
        this.rollFlags = roleFlags;
    }

    public Long getId() {
        return id;
    }

    public Set<String> getRollNames() {
        return rollNames;
    }

    public Set<Integer> getRollFlags() {
        return rollFlags;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", rollNames=" + rollNames +
                ", rollFlags=" + rollFlags +
                '}';
    }

    public boolean containAnyRoleFlags(Set<Integer> expectedRoleFlags) {
        var temp = new HashSet<>(this.getRollFlags());
        temp.retainAll(expectedRoleFlags);
        return !temp.isEmpty();
    }

    public boolean containAnyRoleNames(Set<String> expectedRoleNames) {
        var temp = new HashSet<>(this.getRollNames());
        temp.retainAll(expectedRoleNames);
        return !temp.isEmpty();
    }
}