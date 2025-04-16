package com.naeunminchocofarm.ncf_api.lib.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtHandler jwtHandler;

	public JwtAuthenticationFilter(JwtHandler jwtHandler) {
		this.jwtHandler = jwtHandler;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
																	HttpServletResponse response,
																	FilterChain filterChain)
					throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7);

			String roleName = null;
			try {
				Claims claims = jwtHandler.parseToken(token);

				Long userId = claims.get("id", Long.class);
				roleName = claims.get("roleName", String.class);

				List<SimpleGrantedAuthority> authorities =
								List.of(new SimpleGrantedAuthority(roleName));

				Authentication auth =
								new UsernamePasswordAuthenticationToken(userId, null, authorities);

				SecurityContextHolder.getContext().setAuthentication(auth);

			} catch (Exception e) {
//				logger.warn("JWT 필터 오류: {}", e.getMessage());
				System.out.println("필터오류");
				System.out.println("권한 from JWT: " + roleName);
				System.out.println("SecurityContext 인증 객체: " + SecurityContextHolder.getContext().getAuthentication());
			}
		}

		filterChain.doFilter(request, response); // 다음 필터로 이동
	}
}

