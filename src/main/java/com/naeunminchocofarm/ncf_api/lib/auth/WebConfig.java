package com.naeunminchocofarm.ncf_api.lib.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthInfoArgumentResolver authInfoArgumentResolver;

    public WebConfig(AuthInfoArgumentResolver authInfoArgumentResolver) {
        this.authInfoArgumentResolver = authInfoArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(this.authInfoArgumentResolver);
    }
}