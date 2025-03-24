package com.naeunminchocofarm.ncf_api.lib.auth;

import java.util.Set;

public interface AuthorizationHeaderParser {
    String getAuthorizationHeaderName();
    Object parse(String authorizationHeader, Set<String> expectedRoleNames, Set<Integer> expectedRoleFlags);
}
