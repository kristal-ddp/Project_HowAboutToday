package com.phoenix.howabouttoday.config;


import com.phoenix.howabouttoday.member.Service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@RequiredArgsConstructor //CustomUserDetailsService 생성자 주입을 위한 lombok
@Configuration //클래스 안에서 @Bean을 통해 등록하는 방법
@EnableWebSecurity //Configuration에 @EnableWebSecurity를 추가해 Spring Security 설정 클래스임을 알려준다.
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크하기 위해 사용
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final CustomUserDetailsService customUserDetailsService;


    //비밀번호를 암호화 객체

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
    }


	/*
	인증 무시할 경로 설정
	static 하위 폴더(css, js, img)는 무조건 접근이 가능해야함
	 */

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/template/**", "/error/**");
    }

//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}

	/*
	CSRF(Cross Site Request Forgery)
	웹사이트의 취약점 공격을 방지하기 위해 사용하는 기술
	스프링 시큐어리티가 CSRF 토큰 값을 발행해서
	*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //인증되지 않은 모든 요청을
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/user-dashboard-profile").authenticated()
				.antMatchers("/user-dashboard-reviews").authenticated()
				.antMatchers("/user-dashboard-wishlist").authenticated()
				.antMatchers("/user-dashboard-booking").authenticated()
                .antMatchers("/user-dashboard-setting").authenticated()
                .antMatchers("/**" ).permitAll()
                .and()
                .formLogin()
                .loginPage("/home/login")
                .usernameParameter("email")
                .passwordParameter("pwd")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");


    }

}





