package com.naeunminchocofarm.ncf_api.lib.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthInfoArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger log = LogManager.getLogger(AuthInfoArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        var authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        if (authenticationToken == null || !authenticationToken.isAuthenticated()) {
            throw new UnauthenticatedAccessException("로그인이 필요합니다.");
        }

        if ("anonymousUser".equals(authenticationToken.getPrincipal())) {
            throw new UnauthenticatedAccessException("인증되지 않은 요청입니다.");
        }

        return new AuthUser((Integer)authenticationToken.getPrincipal(), authenticationToken.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
    }
}