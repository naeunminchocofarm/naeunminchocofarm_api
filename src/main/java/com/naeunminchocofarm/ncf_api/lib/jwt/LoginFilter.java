//package com.naeunminchocofarm.ncf_api.lib.jwt;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.naeunminchocofarm.ncf_api.member.entity.LoginInfo;
//import com.naeunminchocofarm.ncf_api.member.entity.Member;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.util.StreamUtils;
//
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.Collection;
//import java.util.Iterator;
//
//public class LoginFilter extends UsernamePasswordAuthenticationFilter {
//
//	private final AuthenticationManager authenticationManager;
//	private final JwtHandler jwtHandler;
//
//	//생성자주입을통해 매개변수로 가져오면 우리가 만든 변수에 넣어주는 식으로되어있어서
//	//약속된 프로토콜임
//	public LoginFilter(AuthenticationManager authenticationManager, JwtHandler jwtHandler){
//		this.authenticationManager = authenticationManager;
//		this.jwtHandler = jwtHandler;
//
//		setFilterProcessesUrl("/members/login"); //로그인 요청URL 변경
//		setUsernameParameter("loginID"); //이코드없으면 username으로 전달
//		setPasswordParameter("encryptedLoginPw"); //이코드없으면 password로 전달됨
//
//	}
//
//	//attemptAuthentication() 매서드는 로그인 요청이 발생하는 가장 처음 호출됨
//	//로그인 요청은 기본적으로 '/login' 이다. (post)
//	@Override //얘는 아이디랑 비번이 받음
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//		System.out.println("loginfilter클래스의 attemptAuthenrication()매서드실행");
//
//		//입력한 아이디 및 비번 받기
//		Member member = new Member();
//		try {
//			ObjectMapper objectMapper = new ObjectMapper();
//			ServletInputStream inputStream = request.getInputStream();
//			String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//			member = objectMapper.readValue(messageBody, Member.class);
//		}catch (Exception e){
//			throw new RuntimeException(e);
//		};
//
//		System.out.println("전달받은 아이디:"+member.getLoginId());
//		System.out.println("전달받은 비번:"+member.getEncryptedLoginPw());
//
//		//우리가 입력한 아이디와 비밀번호를 데이터베이스에 저장한 정보와 일치하는지 검증하는 로직은
//		//AuthenticationManager가 담당하기 때문에 전달받은 아이디와 비밀번호를 AuthenticationManager에 전달해줘야 한다.
//		//이때 아이디와 비밀번호를 그냥 전달하는 것이 아니라 UsernamePasswordAuthenticationToken 객체에 실어 보낸다.
//		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(member.getLoginId(), member.getEncryptedLoginPw(), null);
//
//		//아이디와 비번을 담고 있는 authToken객체를 authenticationManager에 전달, authenticationManager는 로그인을 검증하는 기능을 함
//		//로그인을 검증하는 방법 -> UserDetailsService의 loadUserByUsername 메서드를 호출하여 검증
//		//loadUserByUsername() 메서드의 실행 결과로 로그인 유저의 정보를 authentication 객체에 담아 옴
//		Authentication authentication = authenticationManager.authenticate(authToken);
//		System.out.println("DB에서 로그인 가능 여부 확인 완료(UserDetailsService의 loadUserByUsername 메서드 정상 실행 됨). 만약 검증에 실패했다면 본 출력문 실행 안 됨");
//		System.out.println("로그인 중인 유저 : " + authentication.getName());
//
//		//로그인 유저의 정보가 담긴 authentication객체를 리턴하면 authentication객체가 session에 저장됨
//		//세션에 저장하는 이유는 security의 권한 처리를 위해서는 세션에 로그인 정보가 있어야 되기 때문.
//		return authentication;
//	}
//
//	//인증성공시 자동으로 실행되는 메서드
//	@Override
//	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//		System.out.println("로그인 검증 성공! successfulAuthentication 메서드 호출!");
//
//		//토큰 생성을 위한 아이디 정보 추출
//		String loginId = authResult.getName();
//
//		//토큰 생성을 위한 권한 정보 추출
//		Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
//		Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//		GrantedAuthority auth = iterator.next();
//		String role = auth.getAuthority();
//
//		//토큰 생성                              //아이디, 권한, 시간
//		String accessToken = jwtHandler.parseToken(loginId, role, (1000 * 60 * 30)); //30분
//
//		//생성한 토큰을 응답 헤더에 담아 클라이언트에 전달
//		response.setHeader("Access-Control-Expose-Headers", "Authorization");
//// ㄴ 눈으로 보기위한 정보 없어도되긴함
//		response.setHeader("Authorization", "Bearer " + accessToken);
//		response.setStatus(HttpStatus.OK.value()); //클라이언트에 200 응답
//	}
//
//	//인증실패시 자동으로 실행되는 메서드
//	@Override
//	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//		System.out.println("로그인 검증실패");
//		//로그인 실패하면 401 상태 반환 > return못해서 한거임
//		response.setStatus(401);
//	}
//
//}