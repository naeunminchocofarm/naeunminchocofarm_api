package com.naeunminchocofarm.ncf_api.config;

import com.naeunminchocofarm.ncf_api.lib.auth.AuthInfo;
import com.naeunminchocofarm.ncf_api.lib.jwt.JwtHandler;
import io.jsonwebtoken.Jwts;
import jakarta.security.auth.message.config.AuthConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private JwtHandler jwtHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf((csrf) -> csrf.disable())
                .formLogin(form->form.disable())
                .httpBasic( basic -> basic.disable())
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/ws/**").permitAll()  // 웹소켓은 인증 없이 사용
                .requestMatchers("/sensors/datas").permitAll()  // 센서데이터 수집 api는 인증 없이 사용
                .requestMatchers("/web/**").permitAll()  // 웹도 인증 없이 사용
                .requestMatchers("/web/**").permitAll()  // 웹도 인증 없이 사용
                .requestMatchers("/user/**").hasAnyRole("FAMMER","ADMIN")  // 파머랑관리자는 user접근가능
                .requestMatchers("/admin/**").hasRole("ADMIN")  // 어드민은 관리자만
                .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable);

        //토큰을 검증
        return http.build();
    }


    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173"); //REACT에서 스프링으로 접근허용하게따!!
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }

    @Bean   //비밀번호 암호화 기능을 제공하는 객체 생성 메서드
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
