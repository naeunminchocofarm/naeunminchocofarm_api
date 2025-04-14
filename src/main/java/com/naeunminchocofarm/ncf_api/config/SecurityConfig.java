package com.naeunminchocofarm.ncf_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean   //비밀번호 암호화 기능을 제공하는 객체 생성 메서드
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/ws/**").permitAll()  // 웹소켓은 인증 없이 사용
                        .requestMatchers("/sensors/datas").permitAll()  // 센서데이터 수집 api는 인증 없이 사용
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
