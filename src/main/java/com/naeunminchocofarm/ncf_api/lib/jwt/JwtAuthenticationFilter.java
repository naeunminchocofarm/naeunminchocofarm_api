package com.naeunminchocofarm.ncf_api.lib.jwt;

import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
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
		System.out.println("헤더 Authorization: " + header);

		if (header != null && header.startsWith("Bearer ")) {
			String token = header.replace("Bearer ", "");
			System.out.println("JWT 추출됨: " + token);

			String roleName = null;
			try {
				Claims claims = jwtHandler.parseToken(token);

				Long userId = claims.get("id", Long.class);
				roleName = claims.get("roleName", String.class);

				List<SimpleGrantedAuthority> authorities =
								List.of(new SimpleGrantedAuthority(roleName));

				// 💡 로그인 정보 객체 생성
				LoginInfo loginMember = new LoginInfo(
								userId.intValue(),
								claims.get("loginId", String.class),
								claims.get("name", String.class),
								claims.get("email", String.class),
								claims.get("tell", String.class),
								roleName,
								claims.get("roleFlag", Integer.class)
				);

				Authentication auth =
								new UsernamePasswordAuthenticationToken(loginMember, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(auth);

				// ✅ 핵심: requestAttribute 등록
				request.setAttribute("loginMember", loginMember);

			} catch (Exception e) {
				System.out.println("필터오류");
				System.out.println("권한 from JWT: " + roleName);
				System.out.println("SecurityContext 인증 객체: " + SecurityContextHolder.getContext().getAuthentication());
			}
		}

		filterChain.doFilter(request, response); // 다음 필터로 이동
	}
}

