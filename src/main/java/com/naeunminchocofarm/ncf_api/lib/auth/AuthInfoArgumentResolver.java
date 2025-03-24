package com.naeunminchocofarm.ncf_api.lib.auth;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AuthInfoArgumentResolver implements HandlerMethodArgumentResolver {
    private final AuthorizationHeaderParser authorizationHeaderParser;

    public AuthInfoArgumentResolver(AuthorizationHeaderParser authorizationHeaderParser) {
        this.authorizationHeaderParser = authorizationHeaderParser;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestAuth.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        final var authorizationHeader = webRequest.getHeader(this.authorizationHeaderParser.getAuthorizationHeaderName());
        final var authInfoAnnotation = parameter.getParameterAnnotation(RequestAuth.class);
        assert authInfoAnnotation != null;

        final Set<String> expectedRoleNames = Arrays.stream(authInfoAnnotation.expectedRoleNames()).collect(Collectors.toSet());
        final Set<Integer> expectedRoleFlags = Arrays.stream(authInfoAnnotation.expectedRoleFlags()).boxed().collect(Collectors.toSet());

        return this.authorizationHeaderParser.parse(authorizationHeader, expectedRoleNames, expectedRoleFlags);
    }
}