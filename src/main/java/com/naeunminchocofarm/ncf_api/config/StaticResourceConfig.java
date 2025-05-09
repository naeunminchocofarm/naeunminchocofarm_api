package com.naeunminchocofarm.ncf_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // /uploads/** 로 들어오는 요청을 D:/uploads 디렉토리에서 찾아 반환
    registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:///D:/uploads/");
  }
}
