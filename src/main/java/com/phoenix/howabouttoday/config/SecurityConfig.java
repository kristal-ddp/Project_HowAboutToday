package com.phoenix.howabouttoday.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //CustomUserDetailsService 생성자 주입을 위한 lombok
@Configuration //클래스 안에서 @Bean을 통해 등록하는 방법
@EnableWebSecurity //Configuration에 @EnableWebSecurity를 추가해 Spring Security 설정 클래스임을 알려준다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크하기 위해 사용
public class SecurityConfig {


	private UserDetailsService userDetailsService;

	//비밀번호를 암호화 객체
         
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	/*
	인증 무시할 경로 설정
	static 하위 폴더(css, js, img)는 무조건 접근이 가능해야함
	 */

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> {
			web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
			web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/template/**");
		};
	}

	/*
	CSRF(Cross Site Request Forgery)
	웹사이트의 취약점 공격을 방지하기 위해 사용하는 기술
	스프링 시큐어리티가 CSRF 토큰 값을 발행해서
	*/

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		//인증되지 않은 모든 요청을 허락
		http.authorizeRequests()
				.antMatchers("/user/**").hasRole("MEMBER")
				.antMatchers("/").permitAll();


		http.formLogin()
				.loginPage("/")
				.usernameParameter("email")
				.passwordParameter("pwd");






		return http.build();
	}

}





